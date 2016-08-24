package java_oo.strings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;

public class StringsEx {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		int K = sc.nextInt();
		int L = sc.nextInt();
		int M = sc.nextInt();

		String inputString = sc.next();

		if (inputString.length() < N) {
			return;
		}

		Map<MySubString, Integer> subStrMap = new HashMap<MySubString, Integer>();

		for (int i = 0; i < inputString.length(); i++) {
			String possibility = inputString.substring(i, inputString.length());
			MyString str = new MyString(possibility, K, L, M);
			List<MySubString> legalString = str.getLeagalSubString();

			Iterator<MySubString> itrSubStr = legalString.iterator();

			while (itrSubStr.hasNext()) {

				MySubString key = itrSubStr.next();

				Integer subStrCount = subStrMap.get(key);

				if (subStrCount == null) {
					subStrMap.put(key, new Integer(1));
				} else {
					subStrMap.put(key, subStrCount + new Integer(1));
				}
			}
		}
		
		System.out.println(subStrMap);

		Set<Entry<MySubString, Integer>> entrySet = subStrMap.entrySet();
		Iterator<Entry<MySubString, Integer>> itr = entrySet.iterator();

		int max = 0;

		while (itr.hasNext()) {
			Entry<MySubString, Integer> entry = itr.next();

			if (entry.getValue() > max) {
				max = entry.getValue();
			}
		}

		System.out.println(max);
	}

}

class MyString {

	private String str = null;

	private int K = -1;

	private int L = -1;

	private int M = -1;

	public MyString(String str, int K, int L, int M) {
		this.str = str;
		this.K = K;
		this.L = L;
		this.M = M;
	}

	public List<MySubString> getLeagalSubString() {

		List<MySubString> legalSubString = new ArrayList<MySubString>();

		if (str.length() < K) {
			return new ArrayList<MySubString>();
		}

		int maxSubStrID = str.length() < L ? str.length() : L;

		for (int i = K; i <= maxSubStrID; i++) {

			MySubString subStr = new MySubString(str.substring(0, i));

			if (subStr.isNumberOfDistinctCharWithRange(M)) {
				legalSubString.add(subStr);
			}
		}

		return legalSubString;

	}

}

class MySubString {

	private String subStr = null;

	public MySubString(String subStr) {
		this.subStr = subStr;
	}

	public boolean isNumberOfDistinctCharWithRange(int M) {

		char charArr[] = subStr.toCharArray();

		Map<Character, Integer> distCharmap = new HashMap<Character, Integer>();

		for (int i = 0; i < charArr.length; i++) {

			Character charObj = new Character(charArr[i]);

			Integer val = distCharmap.get(charObj);

			if (val == null) {
				distCharmap.put(charObj, new Integer(1));
			} else {
				int calMValue = new Integer(val.intValue() + 1);

				if (calMValue > M) {
					return false;
				}

				distCharmap.put(charObj, calMValue);
			}

		}

		return true;

	}

	public int hashCode() {
		return this.subStr.hashCode();
	}

	public boolean equals(Object obj) {

		if (obj == null) {
			return false;
		}

		if (obj instanceof MySubString == false) {
			return false;
		}

		if (((MySubString) obj).getSubStr().equals(this.subStr)) {
			return true;
		} else {
			return false;
		}
	}

	public String getSubStr() {
		return subStr;
	}

	public String toString() {
		return this.subStr;
	}

}
