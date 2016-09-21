package java_oo.thread;

import java.lang.Thread.State;
import java.util.Scanner;

import java_oo.swing.Console;
import java_oo.swing.ConsoleThreadLocal;

public class TestThreadLifeCycle {

	public static void main(String args[]) throws InterruptedException {

		Object o = new Object();

		MyThread t1 = new WorkerThread(o, "T1");
		t1.setPriority(Thread.MAX_PRIORITY);
		t1.start();

		MyThread t2 = new WorkerThread(o, "T2");
		t2.setPriority(Thread.MIN_PRIORITY);
		t2.start();

		while (true) {

			System.out.println("----------------------------");
			System.out.println("T1 STATUS " + t1.getState());
			System.out.println("T2 STATUS " + t2.getState());
			System.out.println("----------------------------");
			System.out.println("Enter command for the printer");
			System.out.println("P {1-9}");
			System.out.println("F {1-9}");
			System.out.println("Enter command with prefix thread name, eg T1 W");
			System.out.println("S to sleep");
			System.out.println("W to wait");
			System.out.println("W NA , to wait enabling notify all option");
			System.out.println("N for notify");
			System.out.println("P {1-9} to change priority");
			System.out.println("Enter command without prefix thread name, just NA");
			System.out.println("NA for notify all");

			String command = new Scanner(System.in).nextLine();

			MyThread.command = command;

			if (command.equals("T1 N")) {
				synchronized (t1) {
					t1.notify();
				}
			}

			if (command.equals("T2 N")) {
				synchronized (t2) {
					t2.notify();
				}
			}

			if (command.equals("NA")) {
				synchronized (o) {
					o.notifyAll();
				}
			}

			if (command.contains("P") || command.contains("F")) {
				WorkerThread.task = command;

				if (t2.getState().equals(State.WAITING)) {
					synchronized (t2) {
						t2.notify();
					}
				} else if (t1.getState().equals(State.WAITING)) {
					synchronized (t1) {
						t1.notify();
					}
				}

			}

		}

	}

	static class MyThread extends Thread {

		protected Object o;
		protected Console LOG;

		private MyThread(Object o, String name) {
			this.o = o;
			setName(name);
			LOG = new Console(name);
		}

		static String command;

		public void run() {

			try {
				
				sleep(1000);

				if (command == null) {
					return;
				}
				if (command.equals(getName() + " S")) {
					try {
						sleep(1000 * 60);
					} catch (InterruptedException e) {
						throw new RuntimeException(e);
					}
				} else if (command.equals(getName() + " W")) {
					synchronized (this) {
						wait();
					}
				} else if (command.equals(getName() + " W NA")) {
					synchronized (o) {
						o.wait();
					}
				} else if (command.contains(getName() + " P")) {
					String commandArr[] = command.split(" ");
					setPriority(new Integer(commandArr[commandArr.length - 1]));
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

	static class AllInOnePrinter {

		private int noOfPage;

		private static Object lockPrint = new Object();
		private static Object lockFax = new Object();

		AllInOnePrinter(String noOfPage) {
			this.noOfPage = new Integer(noOfPage);
		}

		public void print() {
			synchronized (lockPrint) {
				long st = System.currentTimeMillis();
				for (int i = 0; i < new Integer(noOfPage); i++) {
					ConsoleThreadLocal.get().logln(i + " printing..");
					try {
						Thread.currentThread().sleep(1000 * 1);
					} catch (InterruptedException e) {
						throw new RuntimeException(e);
					}
				}
				long en = System.currentTimeMillis();
				ConsoleThreadLocal.get().logln(" tt " + ((en - st) / 1000) + " sec");
			}
		}

		public void fax() {
			synchronized (lockFax) {
				long st = System.currentTimeMillis();
				for (int i = 0; i < new Integer(noOfPage); i++) {
					ConsoleThreadLocal.get().logln(i + " faxing..");
					try {
						Thread.currentThread().sleep(1000 * 1);
					} catch (InterruptedException e) {
						throw new RuntimeException(e);
					}

				}
				long en = System.currentTimeMillis();
				ConsoleThreadLocal.get().logln(" tt " + ((en - st) / 1000) + " sec");
			}
		}
	}

	static class WorkerThread extends MyThread {

		static String task;

		public WorkerThread(Object o, String name) {
			super(o, name);
		}

		public void run() {

			ConsoleThreadLocal.set(LOG);

			while (true) {
				try {
					super.run();
					ConsoleThreadLocal.get().logln(" working ");
					// pinterSystem();
					// run();
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			}

		}

		public void pinterSystem() throws InterruptedException {

			synchronized (this) {
				wait();
			}

			String taskArr[] = task.split(" ");

			if (taskArr[0].equals("P")) {
				new AllInOnePrinter(taskArr[1]).print();
			} else if (taskArr[0].equals("F")) {
				new AllInOnePrinter(taskArr[1]).fax();
			}
		}
	}

}
