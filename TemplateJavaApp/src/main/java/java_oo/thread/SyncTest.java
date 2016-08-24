package java_oo.thread;

import java.util.Random;
import java.util.Scanner;

public class SyncTest {

	public static void main(String[] args) throws InterruptedException {

		Printer p = new Printer(1453);

		/*
		 * synchronized (this) or synchronized or synchronized(any non static
		 * object ref)
		 */
		Runnable objectLevelLockTask = () -> {
			new PrinterPrintThread(p);
			//new PrinterDisplayThread(p);
		};

		/*
		 * synchronized (Printer.class) or synchronized static or synchronized(
		 * any static object )
		 */
		Runnable classLevelLockTask = () -> {
			new PrinterPrintThread();
			new PrinterDisplayThread();
		};

		while (true) {
			Thread thread = new Thread(objectLevelLockTask);
			thread.start();

			new Scanner(System.in).nextLine();
		}

	}

}

class PrinterDisplayThread implements Runnable {

	private Printer p;
	private int userId;

	PrinterDisplayThread() {
		for (int userId = 10; userId <= 20; userId++) {
			new Thread(new PrinterDisplayThread(new Printer(RandomUtil.random(1, 1453)), userId)).start();
		}
	}

	PrinterDisplayThread(Printer p) {
		for (int userId = 10; userId <= 20; userId++) {
			new Thread(new PrinterDisplayThread(p, userId)).start();
		}
	}

	PrinterDisplayThread(Printer p, int userId) {
		this.p = p;
		this.userId = userId;
	}

	public void run() {
		try {
			p.getPrinterInfo(userId);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

}

class PrinterPrintThread implements Runnable {

	private Printer p;

	private int userId;

	PrinterPrintThread() {
		for (int userId = 1; userId <= 10; userId++) {
			/* any printer range from 1 to 1453 in an organization */
			new Thread(new PrinterPrintThread(new Printer(RandomUtil.random(1, 1453)), userId)).start();
		}
	}

	PrinterPrintThread(Printer p) {
		for (int userId = 1; userId <= 10; userId++) {
			new Thread(new PrinterPrintThread(p, userId)).start();
		}
	}

	PrinterPrintThread(Printer p, int userId) {
		this.p = p;
		this.userId = userId;
	}

	public void run() {
		try {
			p.print(userId);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

}

class Printer {

	private int printerId;

	Printer(int printerId) {
		this.printerId = printerId;
	}

	public synchronized void print(int userId) throws InterruptedException {
		/*
		 * Class level locking prevents multiple threads to enter in
		 * synchronized block in <b>any of all available instances</b> on
		 * runtime. This means if in runtime there are 100 instances of
		 * DemoClass, then only one thread will be able to execute demoMethod()
		 * in any one of instance at a time, and all other instances will be
		 * locked for other threads
		 * 
		 * synchronized (Printer.class)
		 */

		for (int i = 0; i < 5; i++) {
			System.out.println("Printing for user " + userId + " page " + i);
			Thread.currentThread().sleep(1000 * RandomUtil.random(1, 3));
		}

	}

	public synchronized int getPrinterInfo(int userId) throws InterruptedException {

		for (int i = 0; i < 5; i++) {
			Thread.currentThread().sleep(1000 * RandomUtil.random(1, 3));
			System.out.println("Printer is little busy while sending printer into to user " + userId);
		}
		System.out.println("returned printer info to " + userId);

		return printerId;
	}
}

class RandomUtil {
	static int random(int min, int max) {
		return new Random().nextInt((max - min) + 1) + min;
	}
}