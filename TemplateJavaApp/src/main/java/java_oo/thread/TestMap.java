package java_oo.thread;

import java.awt.Color;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;

import java_oo.swing.Console;
import java_oo.swing.ConsoleThreadLocal;

class Node {
	

}

public class TestMap {

	public static void main(String args[]) {

		System.out.println(new ConcurrentHashMap().equals(new ConcurrentHashMap()));

		//Map<String, Integer> m = new MyMap<String, Integer>();
		//new Sender(m);
		//new Receiver(m);
	}

}

class MyMap<K, V> extends ConcurrentHashMap<K, V> {

	public static final String ALPHA[] = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j" };

	public V put(K key, V value) {
		ConsoleThreadLocal.get().log("put [" + key + "=" + value + "]");
		for (int i = 0; i < 3; i++) {
			try {
				Thread.sleep(1000 * 0);
				ConsoleThreadLocal.get().log("");
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		ConsoleThreadLocal.get().logln("");
		return super.put(key, value);
	}

	public V get(Object key) {
		V v = super.get(key);
		ConsoleThreadLocal.get().log("get [" + key + "=" + v + "]");
		for (int i = 0; i < 3; i++) {
			try {
				Thread.sleep(1000 * 0);
				ConsoleThreadLocal.get().log(".");
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		ConsoleThreadLocal.get().logln("");
		return v;
	}
}

class Sender implements Runnable {

	private Map<String, Integer> m;
	private int threadId;

	public Sender(Map m, int threadId) {
		this.m = m;
		this.threadId = threadId;
	}

	public Sender(Map m) {
		this.m = m;
		for (int i = 0; i < 1; i++) {
			new Thread(new Sender(m, i)).start();
		}
	}

	public void run() {
		ConsoleThreadLocal.set(new Console());
		while (true) {
			try {
				Thread.sleep(100 * 1);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			String msg = MyMap.ALPHA[(int) (Math.random() * MyMap.ALPHA.length)];
			Integer temp = null;
			if ((temp = m.get(msg)) == null) {
				temp = new Integer(0);
			}
			temp++;
			m.put(msg, temp);
			// pause();
		}
	}

	public void pause() {

		System.out.println("enter any key to proceed put");
		while (!new Scanner(System.in).nextLine().equals("p")) {
			// wait
		}

	}
}

class Receiver implements Runnable {

	private Map m;
	private int threadId;

	public Receiver(Map m, int threadId) {
		this.m = m;
		this.threadId = threadId;
	}

	public Receiver(Map m) {
		this.m = m;
		for (int i = 0; i < 5; i++) {
			new Thread(new Receiver(m, i)).start();
		}
	}

	public void run() {
		ConsoleThreadLocal.set(new Console());
		while (true) {
			try {
				Thread.sleep(100 * 1);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			String msg = MyMap.ALPHA[(int) (Math.random() * MyMap.ALPHA.length)];

			Iterator<String> keyItr = m.keySet().iterator();

			while (keyItr.hasNext()) {
				String key = null;
				try {
					key = keyItr.next();
				} catch (ConcurrentModificationException e) {
					ConsoleThreadLocal.get().logln(e.toString(), Color.red);
					ConsoleThreadLocal.get().logln("when i am reading the data, someone is updating it, oh bad!!",
							Color.red);
					throw e;
				}
				if (key != null) {
					m.get(key);
				}
			}
			ConsoleThreadLocal.get().logln("_______");
			ConsoleThreadLocal.get().logln("");
			ConsoleThreadLocal.get().logln("");
			ConsoleThreadLocal.get().logln("_______");

		}
	}
}