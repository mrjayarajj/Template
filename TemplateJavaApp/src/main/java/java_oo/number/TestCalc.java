package java_oo.number;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestCalc {

	public static void main(String args[]) {
		String input1 = "2*5+7(6*2)-6*7";
		String input2 = "2*5+7+6*2-6*7";
		String input3 = "2*5+7(6*2*5)-6*7";
		
		String a[] = "(4+5)-3*6".split("\\s*[a-zA-Z]+\\s*");
		
		System.out.println(Arrays.toString(a));
		
		/*
		for(){
			
		}*/
		
	}

}

enum Operation {
	MUL, ADD, SUB, DIV;
}

class NumSet {

	private List<Integer> numbers = new ArrayList<Integer>();

	private Operation o;

	private NumSet numSet;

	public NumSet(NumSet numSet, Operation o) {
		this.numSet = numSet;
		this.o = o;
	}

	public NumSet(Operation o) {
		this.o = o;
	}

	public void add(int num) {
		this.numbers.add(num);
	}

}
