package java_oo.thread.oll;

public class TestObjectLevelMain {

	public static void main(String[] args) {
		// Two objects
		PrintName name1 = new PrintName();
		//PrintName name2 = new PrintName();

		// Two Threads
		Thread thread1 = new Thread(new PrintNameThread(name1, "t1"));
		Thread thread2 = new Thread(new PrintNameThread(name1, "t2"));

		thread2.start();
		thread1.start();

	}
	
	public static void main_usethis(String[] args) {
		// Two objects
		PrintName name1 = new PrintName();
		PrintName name2 = new PrintName();

		// Two Threads
		Thread thread1 = new Thread(new PrintNameThread(name1, "t1"));
		Thread thread2 = new Thread(new PrintNameThread(name2, "t2"));

		thread2.start();
		thread1.start();

	}

}

class PrintNameThread implements Runnable {

	private PrintName printName;
	private String threadName;

	public PrintNameThread(PrintName printName, String threadName) {
		this.printName = printName;
		this.threadName = threadName;
	}

	@Override
	public void run() {
		printName.print(threadName);
	}

}

class PrintName {

	public void print(String name) {

		synchronized (this) {
			System.out.println(" Class Level Name is : " + name);
			for (int j = 0; j < 3; j++) {
				try {
					Thread.currentThread().sleep(1000 * 1);
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
			}
			System.out.println(" Class Level END Name is : " + name);
		}
	}	

}
