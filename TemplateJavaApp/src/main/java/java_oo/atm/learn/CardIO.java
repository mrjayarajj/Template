package java_oo.atm.learn;

public class CardIO extends IO {

	public CardIO() {
		execute();
	}

	private int cardNumber;

	public int getCardNumber() {
		return cardNumber;
	}

	public String getDisplayMessage() {
		StringBuffer sbf = new StringBuffer();
		sbf.append("------------------------");
		sbf.append("\n");
		sbf.append("Enter your debit card number");
		sbf.append("\n");
		sbf.append("------------------------");
		return sbf.toString();
	}

	public String getErrorMessage() {
		StringBuffer sbf = new StringBuffer();
		sbf.append("------------------------");
		sbf.append("\n");
		sbf.append("Enter only Numberic");
		sbf.append("\n");
		sbf.append("------------------------");
		return sbf.toString();
	}

	public boolean validate(String command) {
		try {

			this.cardNumber = Integer.parseInt(command);
			return isStripReadable(this.cardNumber);
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public boolean isStripReadable(int cardNumber) {
		// Returns the number of digits in stripNumber
		int numberOfDigitsInStrip = (int) (Math.log10(cardNumber) + 1);

		if (numberOfDigitsInStrip == 10) {
			return true;
		} else {
			return false;
		}
	}

}
