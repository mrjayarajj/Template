package java_oo.thread;

public class TestDaemon {

	public static void main(String[] args) throws InterruptedException {

		Thread t = new Thread(new Task());
		/**
		 * when true , when main thread finish and JVM dies, since the JVM die the task is killed
		 * when false, when main tread waits for all the threads to complete the tasks.
		 */
		t.setDaemon(true);
		t.start();
		
		
		Thread.currentThread().join();

		System.out.println("main");

	}

}

class Task implements Runnable {

	public void run() {
		System.out.println("task started..");
		try {
			Thread.sleep(1000 * 5);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		System.out.println("task ended..");
	}
}