package java_oo.thread;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

import java_oo.swing.Console;
import java_oo.swing.ConsoleThreadLocal;

class TestCountDownLatch {

	public static void main(String args[]) throws InterruptedException, ExecutionException {

		int N = 10;

		CountDownLatch startSignal = new CountDownLatch(1);
		CountDownLatch doneSignal = new CountDownLatch(N);

		Set<Future<Integer>> set = new HashSet<Future<Integer>>();

		for (int i = 0; i < N; ++i) {// create and start threads
			FutureTask ft = new FutureTask<>(new RandomNumberGenerator(startSignal, doneSignal));
			new Thread(ft).start();
			set.add(ft);
		}

		System.out.println("All thread are waiting, Hit enter to start the program");
		new Scanner(System.in).nextLine();
		startSignal.countDown(); // let all threads proceed

		System.out.println("waiting.... for all thread to finish the work");

		long s = System.currentTimeMillis();
		doneSignal.await(); // wait for all to finish
		long e = System.currentTimeMillis();
		System.out.println(((e - s) / 1000) + " sec");

		int sum = 0;
		for (Future<Integer> future : set) {
			sum += future.get();
		}
		
		System.out.println("Sum :" + sum);

	}

}

class RandomNumberGenerator implements Callable {

	private final CountDownLatch startSignal;
	private final CountDownLatch doneSignal;

	private final Console LOG = new Console();

	RandomNumberGenerator(CountDownLatch startSignal, CountDownLatch doneSignal) {
		this.startSignal = startSignal;
		this.doneSignal = doneSignal;
	}

	public Integer call() {
		int randomNumber = 0;
		try {
			startSignal.await();
			randomNumber = generateRandomNumber();
			doneSignal.countDown();
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}

		return randomNumber;
	}

	private void sleep() {
		try {
			// wait random between 1 to 5 sec
			Thread.currentThread().sleep(1000 * RandomUtil.random(1, 10));
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	private Integer generateRandomNumber() {
		LOG.logln("Generating random number...");
		sleep();
		int ran = RandomUtil.random(1, 100);
		LOG.logln("Generated random number : " + ran);
		return ran;
	}
}
