package java_oo.thread;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataFeed {

	public static void main(String[] args) throws Exception {

		DataFeed df = new DataFeed();

		df.addWriter(new XMLWriter());
		df.addWriter(new CVSWriter());

		DB db = new DB();

		for (int i = 0; i < db.getCount(); i++) {
			df.process(db.getData());
		}

		df.close();

	}

	private void close() throws Exception {
		writers.parallelStream().forEach(w -> w.close());
	}

	private List<DataWriter> writers = new ArrayList<DataWriter>();

	void addWriter(DataWriter w) {
		writers.add(w);
	}

	public void process(Map<String, String> data) throws Exception {
		writers.parallelStream().forEach(w -> w.write(data));
	}

}

class DB {
	
	private int i;

	Map<String, String> getData() throws InterruptedException {
		Thread.sleep(1000 * 1);
		Map<String, String> l = new HashMap<String, String>();
		l.put("id", "" + i);
		l.put("desc", "desc" + i);
		i++;
		return l;
	}

	public int getCount() {
		return 10;
	}
}

class Folder {
	private List<Product> products;
	
	public List<Product> getProducts() {
		return products;
	}
}

class Product {
	private Integer id;
	private String desc;

	Product(int id, String desc) {
		this.id = id;
		this.desc = desc;
	}
	
	public Integer getId() {
		return id;
	}
	
	public String getDesc() {
		return desc;
	}
}
