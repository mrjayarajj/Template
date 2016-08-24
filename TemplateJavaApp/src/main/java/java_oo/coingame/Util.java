package java_oo.coingame;

import java.util.Random;

public class Util {

	public static int predit(int number) {
		Random r = new Random();
		int ran = r.nextInt(number);
		return ran;
	}
}
