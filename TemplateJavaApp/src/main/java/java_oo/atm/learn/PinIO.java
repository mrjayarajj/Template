package java_oo.atm.learn;

public class PinIO extends IO {

	private static String INVALID_PIN = "your pin is invalid";
	private static String INVALID_PIN_FORMAT = "Enter only 4 Numberic";

	private String errorMsg;
	private String pinNumber;
	private ATM atm;

	public PinIO(ATM atm) {
		this.atm = atm;
		execute();
	}

	public String getPinNumber() {
		return pinNumber;
	}

	public String getDisplayMessage() {
		StringBuffer sbf = new StringBuffer();
		sbf.append("------------------------");
		sbf.append("\n");
		sbf.append("Enter your pin number");
		sbf.append("\n");
		sbf.append("------------------------");
		return sbf.toString();
	}

	public String getErrorMessage() {
		StringBuffer sbf = new StringBuffer();
		sbf.append("------------------------");
		sbf.append("\n");
		sbf.append(this.errorMsg);
		sbf.append("\n");
		sbf.append("------------------------");
		return sbf.toString();
	}

	public boolean validate(String command) {
		try {
			Double.parseDouble(command);

			if (command.length() < 4) {
				this.errorMsg = INVALID_PIN_FORMAT;
				return false;
			}

			this.pinNumber = command;

			return true;
		} catch (NumberFormatException e) {
			this.errorMsg = INVALID_PIN_FORMAT;
			return false;
		}
	}

}
