package java_oo.thread;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class CVSWriter implements DataWriter {

	private FileWriter out;

	public CVSWriter() throws IOException {
		out = new FileWriter(new File("yourCSV.csv"));
	}

	public void write(Map<String, String> m) {

		try {
			out.write(m.get("id"));
			out.write(",");
			out.write(m.get("desc"));
			out.write("\n");
			out.flush();			
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}

	public void close() {
		try {
			out.close();
			System.out.println("Done");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
