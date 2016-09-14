package java_oo.thread;

import java.util.Scanner;
import java.util.Vector;

import java_oo.swing.Console;

public class TestWaitNotify {

	public static void main(String args[]) {
		Producer producer = new Producer();
		producer.start();
		new Consumer(producer).start();
	}

}

class Producer extends Thread {

	static final int MAXQUEUE = 10;
	private Vector messages = new Vector();
	private Console LOG = new Console();

	public int getProducerSize(){
		return messages.size();
	}

	public void run() {
		try {
			while (true) {
				putMessage();
				sleep(1000*1);

			}
		} catch (InterruptedException e) {
		}
	}

	private synchronized void putMessage() throws InterruptedException {
		while (messages.size() == MAXQUEUE) {
			wait();
		}
		messages.addElement("{"+(messages.size()+1)+"}");
		LOG.logln("put message, count is now " + messages.size());
		notify();
		// Later, when the necessary event happens, the thread that is running
		// it calls notify() from a block synchronized on the same object.
	}

	// Called by Consumer
	public synchronized String getMessage() throws InterruptedException {
		notify();
		while (messages.size() == 0) {
			wait();// By executing wait() from a synchronized block, a thread
					// gives up its hold on the lock and goes to sleep.
		}
		String message = (String) messages.firstElement();
		messages.removeElement(message);
		return message;
	}
}

class Consumer extends Thread {

	private Producer producer;
	private Console LOG = new Console();

	public Consumer(Producer p) {
		producer = p;
	}

	@Override
	public void run() {
		try {
			int consumeCount = 0;

			while (true) {
				String message = producer.getMessage();
				LOG.logln("Got message [" + producer.getProducerSize() + "]: " + message);
				sleep(200);

				if (consumeCount == 0) {
					System.out.println("Enter consume count ..");
					consumeCount = new Scanner(System.in).nextInt();
				}
				consumeCount--;
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}