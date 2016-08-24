package java_oo.http;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.NoRouteToHostException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

interface Performance {
	public void startPerformanceTest() throws Exception;
}

class HttpPerformanceExecutor implements Performance {

	private HttpConnector c;

	HttpPerformanceExecutor(HttpConnector c) {
		this.c = c;
	}

	private HttpPerformanceStrategy httpPerformanceStrategy;

	public void setPerformanceStrategy(HttpPerformanceStrategy httpPerformanceStrategy) {
		this.httpPerformanceStrategy = httpPerformanceStrategy;
	}

	public void startPerformanceTest() throws Exception {
		httpPerformanceStrategy.openAllLogger();
		httpPerformanceStrategy.executePerformanceStrategy(c);
		httpPerformanceStrategy.closeAllLogger();
	}

}

class HttpConnector {

	private URL url;
	private HttpURLConnection connection = null;

	public HttpURLConnection getConnection() {
		return connection;
	}

	HttpConnector(String urlStr) throws MalformedURLException {
		url = getUrl(urlStr);
	}

	private URL getUrl(String urlStr) throws MalformedURLException {
		try {
			return new URL(urlStr);
		} catch (MalformedURLException e) {
			throw e;
		}
	}

	protected void connect() throws IOException {

		try {
			connection = (HttpURLConnection) url.openConnection();
		} catch (IOException e) {
			throw e;
		}

		connection.setRequestMethod("GET");

		try {
			connection.connect();
		} catch (NoRouteToHostException e) {
			// No route to host: connect
			throw e;
		} catch (UnknownHostException e) {
			// read timeout
			throw e;
		} catch (ConnectException e) {
			// read timeout
			throw e;
		} catch (SocketException e) {
			// Software caused connection abort: connect
			throw e;
		} catch (SocketTimeoutException e) {
			// Read timed out
			throw e;
		}
	}

	protected void disconnect() {
		connection.disconnect();
	}

}

abstract class HttpPerformanceStrategy {

	public abstract void executePerformanceStrategy(HttpConnector c) throws Exception;

	public void openAllLogger() throws Exception {
		for (Logger l : loggers) {
			l.open();
		}
	}

	public abstract HttpConnectorInfo getHttpConnectorInfo();

	private List<Logger> loggers = new ArrayList<Logger>();

	public void addLogger(Logger logger) {
		loggers.add(logger);
	}

	public void logEachPerformanceHit() throws Exception {

		for (Logger l : loggers) {

			l.log(getHttpConnectorInfo());
		}
	}

	public void closeAllLogger() throws Exception {

		for (Logger l : loggers) {
			l.close();
		}
	}

	protected void execute(HttpPerformanceStrategy performanceStrategy, HttpConnector c, Consumer<HttpConnector> httpConnectorBlock) throws Exception {
		try {
			c.connect();
			httpConnectorBlock.accept(c);
			performanceStrategy.logEachPerformanceHit();
		} finally {
			c.disconnect();
		}
	}

}

class HitBasedStrategy extends HttpPerformanceStrategy {

	private int hitCount;

	private HttpConnectorInfo httpConnectorInfo;

	HitBasedStrategy(int hitCount) {
		this.hitCount = hitCount;
	}

	public HttpConnectorInfo getHttpConnectorInfo() {
		return httpConnectorInfo;
	}

	public void executePerformanceStrategy(HttpConnector c) throws Exception {

		while (hitCount != 0) {

			long st = System.currentTimeMillis();

			execute(this, c, conn -> {
				long ed = System.currentTimeMillis();
				httpConnectorInfo = new HttpConnectorInfo(c);
				httpConnectorInfo.setCurrentHitI(hitCount);
				httpConnectorInfo.setDuration(ed - st);
			});

			hitCount--;
		}

	}

}

class TimeBasedStrategy extends HttpPerformanceStrategy {

	private Date endDateTime = null;

	private HttpConnectorInfo httpConnectorInfo;

	public HttpConnectorInfo getHttpConnectorInfo() {
		return httpConnectorInfo;
	}

	TimeBasedStrategy(String endDateTimeStr) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy hh:mm");
		endDateTime = sdf.parse(endDateTimeStr);
	}

	public void executePerformanceStrategy(HttpConnector c) throws Exception {

		while (endDateTime.compareTo(new Date()) > 0) {

			long st = System.currentTimeMillis();

			execute(this, c, conn -> {
				long ed = System.currentTimeMillis();
				httpConnectorInfo = new HttpConnectorInfo(c);
				httpConnectorInfo.setDuration(ed - st);
			});
		}
	}

}

interface Logger {

	public void open() throws Exception;

	public void log(HttpConnectorInfo info) throws Exception;

	public void close() throws Exception;

}

class HttpConnectorInfo {

	private int currentHitId;

	public void setCurrentHitI(int currentHitId) {
		this.currentHitId = currentHitId;
	}

	private long duration;

	public void setDuration(long duration) {
		this.duration = duration;
	}

	public long getDuration() {
		return duration;
	}

	public int getCurrentHitId() {
		return currentHitId;
	}

	private String stTime;

	private HttpConnector httpConnector;

	public HttpConnectorInfo(HttpConnector httpConnector) {
		this.httpConnector = httpConnector;
		stTime = " Time : " + new Date() + " ";
	}

	public String getStTime() {
		return stTime;
	}

	public HttpConnector getHttpConnector() {
		return httpConnector;
	}
}

class HttpConnectorInfoFileLogger implements Logger {

	static final String COOKIES_HEADER = "Set-Cookie";

	private void logCookie(HttpURLConnection connection, BufferedWriter br) throws IOException {
		Map<String, List<String>> headerFields = connection.getHeaderFields();
		List<String> cookiesHeader = headerFields.get(COOKIES_HEADER);

		if (cookiesHeader != null) {
			for (String cookie : cookiesHeader) {
				System.out.println("Cookie : " + cookie);
				br.write("Cookie : " + cookie);

			}
		}
	}

	private String fileStr = null;
	private FileWriter fw = null;
	private BufferedWriter bw = null;

	public void log(HttpConnectorInfo info) throws Exception {
		String code_time = "Status Code:" + info.getHttpConnector().getConnection().getResponseCode() + info.getStTime();

		if (info.getHttpConnector().getConnection().getResponseCode() == 200) {
			bw.write(code_time);
			flushAndAddNewLine();
		}
	}

	void flushAndAddNewLine() throws IOException {
		bw.flush();
		bw.newLine();
	}

	HttpConnectorInfoFileLogger(String fileStr) throws IOException {
		this.fileStr = fileStr;
	}

	public void open() throws Exception {

		File file = new File(fileStr);

		try {
			fw = new FileWriter(file);
		} catch (FileNotFoundException e) {
			File parent = file.getParentFile();
			if (!parent.exists() && !parent.mkdirs()) {
				throw new IllegalStateException("Couldn't create dir: " + parent);
			}
			file.createNewFile();
			fw = new FileWriter(file);
		}

		bw = new BufferedWriter(fw);

	}

	public void close() {

		if (bw != null) {
			try {
				bw.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		if (fw != null) {
			try {
				fw.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

	}

}

class HttpConnectorInfoConsoleLogger implements Logger {

	private int hitCount;
	private long responseTimeAccumulator = 0;

	public void open() throws Exception {
	}

	public void log(HttpConnectorInfo info) throws Exception {

		System.out.println(info.getCurrentHitId() + " " + info.getDuration());
		// logcontent(info.getHttpConnector().getConnection());
		responseTimeAccumulator = responseTimeAccumulator + info.getDuration();
		hitCount++;
	}

	private void logcontent(HttpURLConnection connection) throws IOException {
		BufferedReader in = null;

		try {
			in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String inputLine = "";
			while ((inputLine = in.readLine()) != null) {
				System.out.println(" " + inputLine);
			}
		} finally {
			if (in != null) {
				in.close();
			}
		}
	}

	public void close() throws Exception {
		System.out.println(responseTimeAccumulator / hitCount + "ms");
	}

}

public class MainHttpSpider {

	public static void main(String[] args) throws Exception {
		new LoadTest();
	}

	public static Performance main_(String[] args) throws Exception {

		Logger fileLogger = new HttpConnectorInfoFileLogger("C:\\u01\\prd_status.log");
		Logger consoleLogger = new HttpConnectorInfoConsoleLogger();

		HttpConnector c = new HttpConnector("http://localhost:8080/jsp/base/security/login.jsp");

		HttpPerformanceExecutor pe = new HttpPerformanceExecutor(c);

		TimeBasedStrategy timeBasedStrategy = new TimeBasedStrategy("07-02-2016 23:59");
		timeBasedStrategy.addLogger(fileLogger);
		timeBasedStrategy.addLogger(consoleLogger);
		// pe.setPerformanceStrategy(timeBasedStrategy);

		HttpPerformanceStrategy hitBasedStrategy = new HitBasedStrategy(1000);
		hitBasedStrategy.addLogger(fileLogger);
		hitBasedStrategy.addLogger(consoleLogger);
		pe.setPerformanceStrategy(hitBasedStrategy);

		// pe.startPerformanceTest();

		return pe;
	}
}

class LoadTest implements Runnable {

	LoadTest() throws Exception {

		new Thread(new LoadTest()).start();

	}

	public void run() {
		try {
			Performance p = MainHttpSpider.main_(null);
			p.startPerformanceTest();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}