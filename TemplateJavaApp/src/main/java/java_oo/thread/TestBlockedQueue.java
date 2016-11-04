package java_oo.thread;

import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import java_oo.swing.Console;

public class TestBlockedQueue {

	public static void main(String args[]) {

		BlockingQueue bq = new ArrayBlockingQueue(100);// 1 million

		ExecutorService pool = Executors.newFixedThreadPool(11);

		pool.submit(new Producer(bq));

		System.out.println("Hit to start cunsumer's ..");
		new Scanner(System.in).nextLine();

		for (int i = 0; i < 10; i++) {
			pool.submit(new Consumer(bq));
		}

		pool.shutdown();

	}

}

class Producer implements Runnable {

	private BlockingQueue queue = null;
	private Console LOG = new Console();

	Producer(BlockingQueue queue) {
		this.queue = queue;
	}

	public int getProducerSize() {
		return queue.size();
	}

	public void run() {

		while (true) {
			try {
				queue.put("{" + (queue.size() + 1) + "}");
				LOG.logln("put message, count is now " + queue.size());
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}

			try {
				Thread.currentThread().sleep(100 * 1);

			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}

		}

	}

}

class Consumer implements Runnable {

	private BlockingQueue<String> queue;
	private Console LOG = new Console();

	public Consumer(BlockingQueue queue) {
		this.queue = queue;
	}

	public void run() {
		try {

			while (true) {

				for (int i = 0; i < 10; i++) {
					String message = queue.take();
					LOG.logln("Got message [" + queue.size() + "]: " + message);
				}
				Thread.currentThread().sleep(1000 * 3);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}