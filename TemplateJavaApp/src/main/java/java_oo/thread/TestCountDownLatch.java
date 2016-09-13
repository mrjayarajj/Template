package java_oo.thread;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;

import java_oo.swing.Console;

class TestCountDownLatch {

	public static void main(String args[]) throws InterruptedException, ExecutionException {

		int N = 10;

		CountDownLatch startSignal = new CountDownLatch(1);
		CountDownLatch doneSignal = new CountDownLatch(N);

		Set<RandomNumberGenerator> set = new HashSet<RandomNumberGenerator>();

		for (int i = 0; i < N; ++i) {// create and start threads
			RandomNumberGenerator rng = new RandomNumberGenerator(startSignal, doneSignal);
			set.add(rng);
			new Thread(rng).start();
		}

		System.out.println("All thread are waiting, Hit enter to start the program");
		new Scanner(System.in).nextLine();
		startSignal.countDown(); // let all threads proceed

		System.out.println("waiting.... for all thread to finish the work");

		long s = System.currentTimeMillis();
		doneSignal.await(); // wait for all to finish
		long e = System.currentTimeMillis();
		System.out.println("waited for : " + ((e - s) / 1000) + " sec");

		int sum = 0;
		for (RandomNumberGenerator rng : set) {
			sum += rng.getRandomNumber();
		}

		System.out.println("Sum :" + sum);

	}

}

class RandomNumberGenerator implements Runnable {

	private final CountDownLatch startSignal;
	private final CountDownLatch doneSignal;

	private final Console LOG = new Console();

	private int ran = 0;

	public int getRandomNumber() {
		return this.ran;
	}

	RandomNumberGenerator(CountDownLatch startSignal, CountDownLatch doneSignal) {
		this.startSignal = startSignal;
		this.doneSignal = doneSignal;
	}

	public void run() {
		try {
			startSignal.await();
			ran = generateRandomNumber();
			doneSignal.countDown();
			for (int i = 0; i < 20; i++) {
				sleep();
				LOG.logln("......");
			}

		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
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
