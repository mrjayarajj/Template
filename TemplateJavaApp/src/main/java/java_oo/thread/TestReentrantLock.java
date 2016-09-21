package java_oo.thread;

import java.awt.Color;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

import java_oo.swing.Console;
import java_oo.swing.ConsoleThreadLocal;

public class TestReentrantLock {

	public static void main(String[] args) {

		MultiPurposePrinter p = new MultiPurposePrinter();
		for (int i = 0; i < 10; i++) {
			new Thread(new PrintThread(p)).start();
			new Thread(new FaxThread(p)).start();
		}

	}

	static class PrintThread implements Runnable {

		private MultiPurposePrinter p;

		public PrintThread(MultiPurposePrinter p) {
			this.p = p;
		}

		public void run() {
			try {
				ConsoleThreadLocal.set(new Console());
				this.p.print();
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}
	}

	static class FaxThread implements Runnable {

		private MultiPurposePrinter p;

		public FaxThread(MultiPurposePrinter p) {
			this.p = p;
		}

		public void run() {
			try {
				ConsoleThreadLocal.set(new Console());
				this.p.fax();
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}
	}

	static class MultiPurposePrinter {

		ReentrantLock lockPrint = new ReentrantLock();
		ReentrantLock lockFax = new ReentrantLock();

		public void print() throws InterruptedException {

			if (lockPrint.tryLock(1, TimeUnit.SECONDS)) {
				if (lockFax.tryLock(1, TimeUnit.SECONDS)) {

					for (int i = 0; i < 5; i++) {
						ConsoleThreadLocal.get().logln("Printing for user page " + i, Color.red);
						//Thread.currentThread().sleep(1000 * 1);
					}

					lockFax.unlock();
				} else {
					System.out.println("P: Timeout while acquiring the lock for fax");
					lockPrint.unlock();
				}

				lockPrint.unlock();
			} else {
				System.out.println("P: Timeout while acquiring the lock for print");
			}
		}

		public void fax() throws InterruptedException {
			
			if (lockFax.tryLock(1, TimeUnit.SECONDS)) {
				if (lockPrint.tryLock(1, TimeUnit.SECONDS)) {
					for (int i = 0; i < 5; i++) {
						ConsoleThreadLocal.get().logln("Faxing for user page " + i);
						//Thread.currentThread().sleep(1000 * 1);
					}
					lockPrint.unlock();
				} else {
					System.out.println("F: Timeout while acquiring the lock for print");
					lockFax.unlock();
				}
				lockFax.unlock();
			} else {
				System.out.println("F: Timeout while acquiring the lock for fax");
			}
		}
	}
}