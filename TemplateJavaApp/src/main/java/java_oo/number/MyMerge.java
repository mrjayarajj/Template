package java_oo.number;

import java.util.ArrayList;
import java.util.List;

public class MyMerge {

	/*
	 * private int[] array={};     private int[] tempMergArr;     private int
	 * length;
	 */

	private int[] array;
	private int[] tempMergArr;
	private int length;

	public MyMerge(int inputArr[]) {
		this.array = inputArr;
	}

	public List<Integer> sort() {

		this.length = array.length;
		this.tempMergArr = new int[length];
		doMergeSort(0, length - 1);
		List<Integer> ll = new ArrayList<Integer>();
		for (int jj : array) {
			ll.add(jj);
		}
		return ll;
	}

	private void doMergeSort(int lowerIndex, int higherIndex) {

		if (lowerIndex < higherIndex) {

			int middle = lowerIndex + (higherIndex - lowerIndex) / 2;

			displayArray(lowerIndex, middle);

			// Below step sorts the left side of the array
			doMergeSort(lowerIndex, middle);
			// Below step sorts the right side of the array
			doMergeSort(middle + 1, higherIndex);
			// Now merge both sides
			mergeParts(lowerIndex, middle, higherIndex);
		}
	}

	private void mergeParts(int lowerIndex, int middle, int higherIndex) {

		for (int i = lowerIndex; i <= higherIndex; i++) {
			tempMergArr[i] = array[i];
		}
		int i = lowerIndex;
		int j = middle + 1;
		int k = lowerIndex;
		while (i <= middle && j <= higherIndex) {
			if (tempMergArr[i] >= tempMergArr[j]) {
				array[k] = tempMergArr[i];
				i++;
			} else {
				array[k] = tempMergArr[j];
				j++;
			}
			k++;
		}
		while (i <= middle) {
			array[k] = tempMergArr[i];
			k++;
			i++;
		}

	}

	public static void main(String[] args) {

		int[] inputArr = { 45, 23, 11, 89, 77, 98, 4, 28, 65, 43 };
		MyMerge sor = new MyMerge(inputArr);
		sor.displayArray(0, inputArr.length);
		sor.sort();
		for (int i : inputArr) {
			System.out.print(i);
			System.out.print(" ");
		}

	}

	void displayArray(int start, int end) {

		for (int i = start; i < end; i++) {
			System.out.print(i);
			System.out.print("\t");
		}
		System.out.println();
		for (int i = start; i < end; i++) {
			System.out.print(array[i]);
			System.out.print("\t");
		}
		System.out.println();
	}

}
