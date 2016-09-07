package java_oo.number;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

class RandomUtil {
	static int random(int min, int max) {
		return new Random().nextInt((max - min) + 1) + min;
	}
}

public class MaxMinArranger {

	
	public static void main(String[] args) {
		List<Number> inputs = new ArrayList<Number>();
		inputs.add(new Number());
		inputs.add(new Number());
		System.out.println(inputs);
		//inputs.subList(0,1).clear();
		inputs.remove(0);
		System.out.println(inputs);
		
	}
	
	
	
	public static void main_(String[] args) {
		List<Number> inputs = getRandomInput();
		List<Number> outputs = new ArrayList<Number>();
		System.out.println(inputs);
		sortByMaxMin(inputs, outputs);
		System.out.println(outputs);
	}

	private static List<Number> getRandomInput() {

		List<Number> inputs = new LinkedList<Number>();

		IntStream.iterate(0, i -> i + 1).limit(RandomUtil.random(0, 100)).forEach(i -> inputs.add(new Number()));

		return inputs;
	}

	private static void sortByMaxMin(List<Number> inputs, List<Number> outputs) {

		Number max = null;
		Number min = null;

		for (Number input : inputs) {

			Number current = input;

			if (current.isGreater(max)) {
				max = current;
			}
			if (current.isLesser(min)) {
				min = current;
			}
			if (current.isEqual(min)) {
				min = current;
			}
			if (current.isEqual(max)) {
				max = current;
			}
		}

		inputs.remove(max);
		inputs.remove(min);
		outputs.add(max);
		outputs.add(min);
		
		if (inputs.size() != 0) {
			
			sortByMaxMin(inputs, outputs);
		}

	}
}

class Number implements Comparable<Number> {

	int CURRENT_IS_GREATER = 1;
	int CURRENT_IS_LESS = -1;
	int CURRENT_IS_EQUAL = 0;

	private int value;

	Number(int value) {
		this.value = value;
	}

	public Number() {
		this.value = RandomUtil.random(0, 100);
	}

	public int getValue() {
		return value;
	}

	public boolean isGreater(Number o) {
		return this.compareTo(o) == CURRENT_IS_GREATER;
	}

	public boolean isLesser(Number o) {
		return this.compareTo(o) == CURRENT_IS_LESS;
	}

	public boolean isEqual(Number o) {
		return this.compareTo(o) == CURRENT_IS_EQUAL;
	}

	public int compareTo(Number o) {

		if (o == null) {
			return 0;
		}

		if (this.value > o.getValue()) {
			return 1;
		} else if (this.value < o.getValue()) {
			return -1;
		} else {
			return 0;
		}
	}

	public String toString() {
		return "" + this.value;
	}
}
