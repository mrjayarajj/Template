package java_oo.design_pattern.singleton;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class PropertyFileReader {

	private volatile PropertyFileReader propertyFileReader = null;

	private String fileName = null;

	private PropertyFileReader() {
		throw new RuntimeException("use getInstance method");
	}

	protected PropertyFileReader(String fileName) {
		if (propertyFileReader != null) {
			throw new RuntimeException("Instance already created, use get**Instance method");
		}
		this.fileName = fileName;

		Properties p = new Properties();
		try {
			p.load(new FileInputStream(new File(fileName)));
		} catch (FileNotFoundException e) {
			System.err.println("File Not Found : " + e.getMessage());
		} catch (IOException e) {
			System.err.println("Error while doing input/output operation : " + e.getMessage());
		}

	}

	public PropertyFileReader getBfxPropertyFileInstance() {
		if (propertyFileReader == null) {
			synchronized (PropertyFileReader.class) {
				if (propertyFileReader == null) {
					propertyFileReader = new PropertyFileReader("BFX.properties");
				}
			}
		}
		return propertyFileReader;
	}
	
	public static void main(String[] args) {
		List<Integer> l = Arrays.asList(1,2,3,4,5,6);
		l.forEach(values-> System.out.println(values));
	}
}