
package java_oo.thread;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

import java_oo.swing.Console;

public class TestAsync {

	public static class WordLengthCallable implements Callable {

		private String word;

		private Console LOG = new Console();

		public WordLengthCallable(String word) {
			this.word = word;
		}

		public Integer call() {

			for (int i = 0; i < word.length(); i++) {
				try {
					Thread.sleep(1000 * 1);
					LOG.logln("(" + word + ")" + i);
					// System.out.println("("+word+")"+i);

					if (i == 3) {
						throw new RuntimeException(
								"ohh stop all the ||le processing because there was an invalid input");
					}

				} catch (InterruptedException e) {

					throw new RuntimeException(e);
				}
			}

			return Integer.valueOf(word.length());
		}
	}

	static void usingExecutorFramework(String sentance[]) throws InterruptedException, ExecutionException {
		ExecutorService pool = Executors.newFixedThreadPool(sentance.length);
		Set<Future<Integer>> set = new HashSet<Future<Integer>>();
		for (String word : sentance) {
			Callable<Integer> callable = new WordLengthCallable(word);
			Future<Integer> future = pool.submit(callable);
			set.add(future);
		}
		int sum = 0;
		for (Future<Integer> future : set) {
			long s = System.currentTimeMillis();
			sum += future.get();
			long e = System.currentTimeMillis();
			System.out.println(((e - s) / 1000) + " sec");
		}
		System.out.printf("The sum of lengths is %s%n", sum);
		System.exit(0);
	}

	static void useTraditionalFramework(String sentance[]) throws InterruptedException, ExecutionException {

		Set<Future<Integer>> set = new HashSet<Future<Integer>>();

		for (String word : sentance) {
			FutureTask<Integer> futureTask = new FutureTask<>(new WordLengthCallable(word));
			Thread t = new Thread(futureTask);
			t.start();
			set.add(futureTask);
		}

		int sum = 0;

		for (Future<Integer> future : set) {
			long s = System.currentTimeMillis();
			sum += future.get();
			long e = System.currentTimeMillis();
			System.out.println(((e - s) / 1000) + " sec");
		}

		System.out.printf("The sum of lengths is %s%n", sum);
		System.exit(0);
	}

	public static void main(String sentance[]) throws Exception {

		try {
			// usingExecutorFramework(sentance);
			useTraditionalFramework(sentance);
		} catch (Exception e) {
			System.exit(0);
		}

	}
}