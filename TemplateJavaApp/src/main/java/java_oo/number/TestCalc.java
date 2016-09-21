package java_oo.number;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestCalc {

	public static void main(String args[]) {
		String input1 = "2*5+7(6*2)-6*7";
		String input2 = "2*5+7+6*2-6*7";
		String input3 = "2*5+7(6*2*5)-6*7";
		
		//new NumSet(Operation.MUL,new Integer[]{2,5})
		//.add(new Nu(7,new NumSet(Operation.MUL,new Integer[]{6,2,5}))
		//.sub(new NumSet(Operation.MUL,new Integer[]{6,7}));

		String a[] = "(4+5)-3*6".split("\\s*[a-zA-Z]+\\s*");

		System.out.println(Arrays.toString(a));

		/*
		 * for(){
		 * 
		 * }
		 */

	}

}

enum Operation {
	MUL, ADD, SUB, DIV;
}

interface Num {
	public int getResult();

	public Num put(int num);
}

class NumSet implements Num {

	private List<Integer> numbers = new ArrayList<Integer>();

	private Num num;
	private Operation o;

	public NumSet(Num num) {
		this.num = num;
	}

	public Object add(int i, NumSet numSet) {
		return null;
	}

	public NumSet(Integer num[]) {
	}

	public NumSet(Operation o, Integer num[]) {
		this.o = o;
	}

	public Num put(int num) {
		this.numbers.add(num);
		return this;
	}

	public int getResult() {

		int v = num.getResult();

		for (Integer i : numbers) {
			if (o.equals(Operation.ADD)) {
				v = v + i;
			}
			if (o.equals(Operation.SUB)) {
				v = v - i;
			}
			if (o.equals(Operation.MUL)) {
				v = v * i;
			}
			if (o.equals(Operation.DIV)) {
				v = v / i;
			}
		}
		return v;
	}

}
