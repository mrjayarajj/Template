package java_oo.thread;

import java.awt.Color;
import java.util.Random;
import java.util.Scanner;

import java_oo.swing.Console;
import java_oo.swing.ConsoleThreadLocal;

public class TestSync {

	public static void main(String[] args) throws InterruptedException {

		AllInOnePrinter p = new AllInOnePrinter("HP-143");

		/*
		 * synchronized (this) or synchronized or synchronized(any non static
		 * object ref)
		 */
		Runnable objectLevelLockTask = () -> {
			new PrinterPrintThread(p);
			new PrinterFaxThread(p);
		};

		/*
		 * synchronized (Printer.class) or synchronized static or synchronized(
		 * any static object )
		 */
		Runnable classLevelLockTask = () -> {
			new PrinterPrintThread();
			new PrinterFaxThread();
		};

		Thread thread = new Thread(classLevelLockTask);
		thread.start();

		new Scanner(System.in).nextLine();
		System.exit(0);

	}

}

class PrinterFaxThread implements Runnable {

	private AllInOnePrinter p;
	private int userId;

	PrinterFaxThread() {
		for (int userId = 1; userId <= 5; userId++) {
			Thread t = new Thread(new PrinterFaxThread(new AllInOnePrinter("HP-143"), userId));
			t.start();
		}
	}

	PrinterFaxThread(AllInOnePrinter p) {
		for (int userId = 1; userId <= 5; userId++) {
			Thread t = new Thread(new PrinterFaxThread(p, userId));
			t.start();
		}
	}

	PrinterFaxThread(AllInOnePrinter p, int userId) {
		this.p = p;
		this.userId = userId;

	}

	public void run() {
		try {
			ConsoleThreadLocal.set(new Console());
			p.fax(userId);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

}

class PrinterPrintThread implements Runnable {

	private AllInOnePrinter p;

	private int userId;

	PrinterPrintThread() {
		for (int userId = 1; userId <= 5; userId++) {
			Thread t = new Thread(new PrinterPrintThread(new AllInOnePrinter("HP-143"), userId));
			t.start();
		}
	}

	PrinterPrintThread(AllInOnePrinter p) {
		for (int userId = 1; userId <= 5; userId++) {
			Thread t = new Thread(new PrinterPrintThread(p, userId));
			t.start();
		}
	}

	PrinterPrintThread(AllInOnePrinter p, int userId) {
		this.p = p;
		this.userId = userId;

	}

	public void run() {
		try {
			ConsoleThreadLocal.set(new Console());
			p.print(userId);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

}

class AllInOnePrinter {

	private String printerName;

	AllInOnePrinter(String printerName) {
		this.printerName = printerName;
	}

	static Object lockPrint = new Object();
	static Object lockFax = new Object();

	public void print(int userId) throws InterruptedException {
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

		synchronized (lockPrint) {

			for (int i = 0; i < 5; i++) {
				ConsoleThreadLocal.get().logln("Printing for user " + userId + " page " + i, Color.red);
				Thread.currentThread().sleep(1000 * RandomUtil.random(1, 3));
			}
		}

	}

	public void fax(int userId) throws InterruptedException {

		synchronized (lockFax) {

			for (int i = 0; i < 5; i++) {
				ConsoleThreadLocal.get().logln("Faxing for user " + userId + " page " + i);
				Thread.currentThread().sleep(1000 * RandomUtil.random(1, 3));
			}
		}
	}
}

class RandomUtil {
	static int random(int min, int max) {
		return new Random().nextInt((max - min) + 1) + min;
	}
}