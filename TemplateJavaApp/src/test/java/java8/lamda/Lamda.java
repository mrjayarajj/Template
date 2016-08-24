package java8.lamda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import static java.util.stream.Collectors.toList;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class USIncomeTaxUtil {

	public static USIncomeTax getIncomeTaxForState(String state) {
		try {
			System.out.println("Hitting for state " + state + "...");
			Thread.sleep(1000 * 5);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		if (state.equals("CA")) {
			return new USIncomeTax(state, 10.9);
		}
		if (state.equals("MA")) {
			return new USIncomeTax(state, 5.3);
		}
		if (state.equals("TX")) {
			return new USIncomeTax(state, 1.4);
		}
		return new USIncomeTax(state, 0.0);
	}

	public static Predicate<USIncomeTax> getHighestIncomeTaxState(USIncomeTax carryUSIncomeTax) {
		return incomeTax -> incomeTax.getTax() > carryUSIncomeTax.getTax();
	}
}

class USIncomeTax {
	private String state;
	private double tax;

	public USIncomeTax(String state, double tax) {
		this.state = state;
		this.tax = tax;
	}

	public String getState() {
		return state;
	}

	public double getTax() {
		return tax;
	}

	public String toString() {
		return "State " + this.state + " and tax " + this.tax;
	}
}

public class Lamda {

	public static void findHighestIncomeTaxState() throws Exception {
		List<String> states = Arrays.asList("CA", "MA", "TX");

		USIncomeTax higStateTax = new USIncomeTax("??", 0.0);
		System.out.println(states.stream().map(USIncomeTaxUtil::getIncomeTaxForState).reduce(higStateTax, (c, e) -> c.getTax() < e.getTax() ? e : c));
	}

	public static boolean isPrime(Integer givenNumber) {

		boolean isPrime = true;

		for (int i = 2; i < givenNumber; i++) {
			if (givenNumber > 1 && givenNumber % i == 0) {
				isPrime = false;
				break;
			}
		}

		// System.out.println( "Old fashion of calculating prime for number " +
		// givenNumber + " is " + isPrime);

		boolean l = IntStream.range(2, givenNumber).noneMatch(i -> givenNumber % i == 0);

		boolean returnIsPrime = givenNumber > 1 && l;
		System.out.println("new fashion of calculating prime for number " + givenNumber + " is " + isPrime);
		return returnIsPrime;
	}

	public static void showSquareOfPrimesTill(int range) {

		List<Double> sqrtPrimes = new ArrayList<Double>();

		for (int index = 1; sqrtPrimes.size() < 100; index++) {

			if (isPrime(index)) {
				sqrtPrimes.add(Math.sqrt(index));
			}
		}

		//System.out.println(sqrtPrimes);

		System.out.println(
		
		Stream.iterate(1, i -> i + 1)
		       .filter(Lamda::isPrime)
			   .map(Math::sqrt)
			   .limit(100)
			   .collect(toList())
			   
			   );



	}

	public static void filterNumbersLessThan(Integer predictNumber) {

		List<Integer> numbers = Arrays.asList(1, 3, 6, 7, 8, 9, 2, 3, 4);

		List<Integer> sortedNumbers = numbers.stream().filter(e -> e < predictNumber).collect(toList());
		;

		System.out.println(sortedNumbers);
	}

	public static void findHighestNumber() {

		List<Integer> numbers = Arrays.asList(1, 3, 6, 7, 8, 9, 2, 3, 4, 100);

		Integer sortedNumbers = numbers.stream().reduce(0, (c, e) -> c < e ? e : c);
		;

		System.out.println(sortedNumbers);
	}

	public static void main(String[] args) throws Exception {
		// findHighestIncomeTaxState();
		// findHighestNumber();
		// filterNumbersLessThan(5);
		// isPrime(3);
		showSquareOfPrimesTill(100);
	}

}
