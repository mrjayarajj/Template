package java_oo.thread;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

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
	
	void usingExecutorFramework(String sentance[]) throws InterruptedException, ExecutionException{
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
			System.out.println(((e-s)/1000)+" sec");
		}
		System.out.printf("The sum of lengths is %s%n", sum);
		System.exit(sum);
	}

	public static void main(String sentance[]) throws Exception {
		
		Set<Future<Integer>> set = new HashSet<Future<Integer>>();
		for(String word : sentance){
			
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
			System.out.println(((e-s)/1000)+" sec");
		}
		System.out.printf("The sum of lengths is %s%n", sum);
		System.exit(sum);
		
	}
}