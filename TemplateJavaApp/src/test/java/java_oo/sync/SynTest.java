package java_oo.sync;

import java.util.Date;

public class SynTest {

	public static void main(String[] args) {
		Printer p = new Printer();
		Task ta = new Task(p,"print(Date,String)");
		Task tb = new Task(p,"print(Date,String)");
		
		//Task ta = new Task(p,"print(String)");
		//Task tb = new Task(p,"print(String)");
		ta.start();
		tb.start();
	}
}

class Printer {
	
	static synchronized void print(Date date , String name) {
		//synchronized (this) {
			timer("Printing Date "+date+"..."+name);
		//}
	}
	
	synchronized void print(String name) {
		//synchronized (this) {
			timer("Print for : "+name);
		//}
	}
	
	public static void timer(String name){
		
		System.out.println(name);
		
		for (int i = 0; i < 10; i++) {
			if((i+1)<10)
				System.out.print(".");
			else
				System.out.println(".");
			
			try {
				Thread.sleep(1000 * 1);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
			
		}
	}
}

class Task extends Thread {

	private Printer p;
	private String methodName;

	public Task(Printer p,String methodName) {
		this.p = p;
		this.methodName=methodName;
	}

	public void run() {
		if(methodName.equals("print(Date,String)"))
			p.print(new Date(),Thread.currentThread().getName());
		else if(methodName.equals("print(String)"))
			p.print(Thread.currentThread().getName());
	}
}
