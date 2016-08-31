package java_oo.number;

import java.util.ArrayList;
import java.util.List;

public class FindMaxPossiblility {

	public static void main(String[] args) {

		int inputArray[] = new int[] { 1, 2, 3, 1, 1, 1, 1, 11 };

		int k = 7;

		MyArray myArr = new MyArray(inputArray, k);

		int possiblilityLength = myArr.getCurrentPossibilityLenth();

		while (possiblilityLength > 0) {

			myArr.getAllPossibleSubArrayForLength(possiblilityLength);

			// System.out.println(possiblilityLength);

			possiblilityLength = myArr.getCurrentPossibilityLenth();
		}

		System.out.println(myArr.getMaxPossibilityLength());

	}

}

class MyArray {

	private int inputArray[] = null;
	private int k = 0;

	public MyArray(int[] inputArray, int k) {
		this.inputArray = inputArray;
		this.k = k;
	}

	private int currentPossibilityLenth = -1;

	/**
	 * for very first time return the length of the input array, after that it
	 * will return decrementing by 1
	 * 
	 * @return
	 */
	int getCurrentPossibilityLenth() {

		if (currentPossibilityLenth == -1) {
			currentPossibilityLenth = inputArray.length;
			return currentPossibilityLenth--;
		} else {
			return currentPossibilityLenth--;
		}
	}

	/**
	 * retrun all possible sub array for the given input array for max length
	 * @param subArrayMaxLength
	 * @return
	 */
	public Object[] getAllPossibleSubArrayForLength(int subArrayMaxLength) {

		List<Object[]> possibleSubArray = new ArrayList<Object[]>();

		int startIndex = 0;

		while ((startIndex + subArrayMaxLength) <= inputArray.length) {

			// System.out.println(startIndex + " " + subArrayMaxLength);

			possibleSubArray.add(getArrayBetween(startIndex, subArrayMaxLength));

			startIndex++;
		}

		return possibleSubArray.toArray();

	}

	private int maxPossibilityLength = 0;

	public int getMaxPossibilityLength() {
		return maxPossibilityLength;
	}

	public Object[] getArrayBetween(int startIndex, int length) {

		List<Integer> subList = new ArrayList<Integer>();

		int subArrayCount = 0;

		while (length > 0) {

			subList.add(inputArray[startIndex]);

			subArrayCount = subArrayCount + inputArray[startIndex];

			startIndex++;
			length--;
		}

		if (subArrayCount == k) {
			if (maxPossibilityLength < subList.size()) {
				maxPossibilityLength = subList.size();
			}
		}

		System.out.println(subList);
		System.out.println("-------");

		return subList.toArray();

	}

}
