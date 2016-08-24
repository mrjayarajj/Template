package java_oo.thread;

import java.util.Map;

public interface DataWriter {
	public void write(Map<String, String> m);
	public void close() ;
}
