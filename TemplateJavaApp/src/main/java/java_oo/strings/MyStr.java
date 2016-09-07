package java_oo.strings;

import java.util.LinkedHashMap;
import java.util.Map;

public class MyStr {

	public static void testMap() {

		Map<String, String> m = new LinkedHashMap<String, String>();

		for (int i = 0; i < 10; i++) {
			String key = Math.random() + ">" + i;

			System.out.println("index = " + (key.hashCode() & (16 - 1)));

			m.put(key, i + "");
		}

		m.forEach((k, v) ->{
		
			
			System.out.println(k + "   =    " + v);	
		
		
		});

		//System.out.println(m);
	}

	public static void reverseAStringUsingStringBuffer() {

		String myName = "Jayaraj Jaganathan";
		char myNameCharArr[] = myName != null ? myName.toCharArray() : null;

		int lengthOfMyName = myNameCharArr != null ? myNameCharArr.length : 0;

		StringBuffer myNameSbf = new StringBuffer();

		while (lengthOfMyName != 0) {

			char current = myNameCharArr[lengthOfMyName - 1];
			myNameSbf.append(current);

			lengthOfMyName--;
		}

		System.out.println(myNameSbf.toString());
	}

	public static void reverseAStringUsingCharArray() {
		String input = "Be in present";
		char[] temparray = input.toCharArray();
		int left, right = 0;
		right = temparray.length - 1;
		for (left = 0; left < right; left++, right--) {
			// Swap values of left and right
			char temp = temparray[left];
			temparray[left] = temparray[right];
			temparray[right] = temp;
		}
		for (char c : temparray)
			System.out.print(c);
		System.out.println();

	}

	public static void main(String args[]) {
		// reverseAStringUsingCharArray();
		testMap();
	}

}
