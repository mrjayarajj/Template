package java_oo.thread;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableExample {

	public static class WordLengthCallable implements Callable {
		private String word;

		public WordLengthCallable(String word) {
			this.word = word;
		}

		public Integer call() {
			
			
			for(int i=0;i<word.length();i++){
				try {
					Thread.sleep(1000*1);
					System.out.println("("+word+")"+i);
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
			}
			
			return Integer.valueOf(word.length());
		}
	}

	public static void main(String args[]) throws Exception {
		ExecutorService pool = Executors.newFixedThreadPool(args.length);
		Set<Future<Integer>> set = new HashSet<Future<Integer>>();
		for (String word : args) {
			Callable<Integer> callable = new WordLengthCallable(word);
			Future<Integer> future = pool.submit(callable);
			set.add(future);
		}
		int sum = 0;
		for (Future<Integer> future : set) {
			long s = System.currentTimeMillis();
			sum += future.get();
			long e = System.currentTimeMillis();
			System.out.println(((e-s)/1000)+" sec");
		}
		System.out.printf("The sum of lengths is %s%n", sum);
		System.exit(sum);
	}
}