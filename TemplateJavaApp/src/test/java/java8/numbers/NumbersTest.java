package java8.numbers;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

public class NumbersTest {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		int T = scanner.nextInt();

		List<Integer> input = new ArrayList<Integer>();

		if (T >= 1 && T <= 15) {
			
			IntStream.rangeClosed(1, T).forEach(i -> input.add(scanner.nextInt()));

			input.forEach((in) -> {
				System.out.println(new DigitAnalyser(in).getEvenlyDivisibleCount());
			});
		}

	}

}

class DigitAnalyser {

	private Integer N = null;

	public DigitAnalyser(Integer N) {
		this.N = N;
		validate();
	}

	private void validate() {
		if (N > 0 && N > Math.pow(10, 9)) {
			throw new RuntimeException("N must be 0<=N<=10pow9");
		}
	}

	public Integer getN() {
		return N;
	}

	public long getEvenlyDivisibleCount() {

		return getN().toString().chars().mapToObj(c -> (char) c).map(c -> Character.toString(c)).map(c -> Integer.parseInt(c)).filter(i -> i > 0).filter(i -> getN().intValue() % i == 0).count();
	}

}
