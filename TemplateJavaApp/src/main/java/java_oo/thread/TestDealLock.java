package java_oo.thread;

import java.awt.Color;
import java.util.Scanner;

import java_oo.swing.Console;

public class TestDealLock {

	public static void main(String[] args) {
		MultiPurposePrinter p = new MultiPurposePrinter();
		while (true) {
			new Thread(new PrintThread(p)).start();
			new Thread(new FaxThread(p)).start();
			
			new Thread(new PrintThread(p)).start();
			new Thread(new FaxThread(p)).start();
			
			new Thread(new PrintThread(p)).start();
			new Thread(new FaxThread(p)).start();
			
			new Thread(new PrintThread(p)).start();
			new Thread(new FaxThread(p)).start();
			
			new Thread(new PrintThread(p)).start();
			new Thread(new FaxThread(p)).start();
			
			new Thread(new PrintThread(p)).start();
			new Thread(new FaxThread(p)).start();
			
			new Thread(new PrintThread(p)).start();
			new Thread(new FaxThread(p)).start();
			
			new Thread(new PrintThread(p)).start();
			new Thread(new FaxThread(p)).start();
			
			new Thread(new PrintThread(p)).start();
			new Thread(new FaxThread(p)).start();
			
			new Thread(new PrintThread(p)).start();
			new Thread(new FaxThread(p)).start();
			
			new Thread(new PrintThread(p)).start();
			new Thread(new FaxThread(p)).start();
			
			new Thread(new PrintThread(p)).start();
			new Thread(new FaxThread(p)).start();

			new Scanner(System.in).nextLine();
		}
	}
}

class PrintThread implements Runnable {

	private MultiPurposePrinter p;

	public PrintThread(MultiPurposePrinter p) {
		this.p = p;
	}

	public void run() {
		try {
			this.p.print();
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}
}

class FaxThread implements Runnable {

	private MultiPurposePrinter p;

	public FaxThread(MultiPurposePrinter p) {
		this.p = p;
	}

	public void run() {
		try {
			this.p.fax();
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}
}

class MultiPurposePrinter {

	Object lockPrint = new Object();
	Object lockFax = new Object();
	private Console LOG = new Console();

	public void print() throws InterruptedException {
		synchronized (lockPrint) {

			synchronized (lockFax) {
				for (int i = 0; i < 5; i++) {
					LOG.logln("Printing for user page " + i, Color.red);
					// Thread.currentThread().sleep(1000 * 1);
				}
			}
		}

	}

	public void fax() throws InterruptedException {

		synchronized (lockFax) {
			synchronized (lockPrint) {

				for (int i = 0; i < 5; i++) {
					LOG.logln("Faxing for user  page " + i);
					// Thread.currentThread().sleep(1000 * 1);
				}
			}
		}
	}
}
