package java_oo.node;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import java_oo.swing.Console;

public class QueueProcessor {

	public static void main(String args[]) {

		ExecutorService e = Executors.newFixedThreadPool(2);

		Queue q = new Queue();
		e.submit(new Sender(q));
		e.submit(new Receiver(q));

		// e.shutdown();
	}

}

class Sender implements Runnable {

	private Console log = new Console();

	private Queue q;

	Sender(Queue q) {
		this.q = q;
	}

	public void run() {

		while (true) {

			try {
				Thread.sleep(1000 * 0);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}

			log.logln("Putting message..");
			q.putMessage(new Message("" + new Date()));
		}

	}
}

class Receiver implements Runnable {

	private Console log = new Console();

	private Queue q;

	Receiver(Queue q) {
		this.q = q;
	}

	public void run() {
		while (true) {

			try {
				Thread.sleep(1000 * 0);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}

			log.logln("Retriving messgae.. " + q.getMessages(1));
		}
	}
}

class Queue {

	private LinkedList<Message> messages = null;

	public void putMessage(Message message) {

		synchronized (Queue.class) {
			if (this.messages == null) {
				this.messages = new LinkedList<Message>();
			}

			this.messages.addLast(message);
		}
	}

	public List<Message> getMessages(int noOfMsg) {

		synchronized (Queue.class) {

			List<Message> removedMessages = null;

			if (this.messages == null || this.messages.size() < noOfMsg) {
				return null;
			}

			removedMessages = new ArrayList<Message>();

			for (int i = 0; i < noOfMsg; i++) {
				removedMessages.add(this.messages.removeFirst());
			}

			return removedMessages;
		}
	}

}

class Message {

	private String value;

	Message(String value) {
		this.value = value;
	}

	public String toString() {
		return this.value;
	}

}
