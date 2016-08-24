package java_oo.atm.learn;

public class Main {
	public static void main(String[] args) {
		CardIO io = new CardIO();
		io.execute();
		System.out.println(io.getCardNumber());
	}
}
