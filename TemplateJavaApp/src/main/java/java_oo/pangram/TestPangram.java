package java_oo.pangram;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class TestPangram {
	
	public String sam = "test jj";
	
	private static void sample() throws InterruptedException{
		Thread.sleep(1000*60*5);
	}

	public static void main(String[] args) throws InterruptedException {
		// String n1 = "we promptly judged antique ivory buckles for the prize";
		// System.out.println(new HackersCodePangram(new
		// Pangram(n1)).isPangram());

	}
}

class HackersCodePangram {

	private Pangram pangram;

	public HackersCodePangram(Pangram pangram) {
		this.pangram = pangram;
	}

	public boolean isPangram() {
		String inputString = this.pangram.getInputString();

		boolean isInRange = Optional.ofNullable(inputString).map(e -> e.length()).filter(e -> e > 1)
				.filter(e -> e < (int) Math.pow(10, 3)).isPresent();

		if (isInRange) {
			return this.pangram.isPangram();
		} else {
			return false;
		}

	}
}

class Pangram {

	private String inputString = "";

	public Pangram(String inputString) {
		this.inputString = inputString;
	}

	public String getInputString() {
		return inputString;
	}

	public boolean isPangram() {

		char[] ca = inputString.toCharArray();

		Set<String> alpha = new TreeSet<>();

		for (Character c : ca) {

			if (c.toString().trim().length() > 0)
				alpha.add(c.toString().toLowerCase());

			if (alpha.size() == 26) {
				return true;
			}
		}

		return false;
	}

}

enum EnglishAlphabet {
	A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, W, X, Y, Z;
}
