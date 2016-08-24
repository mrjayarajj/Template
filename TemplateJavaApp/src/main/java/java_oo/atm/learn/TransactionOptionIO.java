package java_oo.atm.learn;

enum TransactionOption {
	REMOVE_FUND, DEPOSITE_FUND;
}

public class TransactionOptionIO extends IO {

	private TransactionOption transactionOption;

	public TransactionOption getTransactionOption() {
		return transactionOption;
	}
	
	public TransactionOptionIO() {
		execute();
	}

	public String getDisplayMessage() {
		StringBuffer sbf = new StringBuffer();
		sbf.append("------------------------");
		sbf.append("\n");
		sbf.append("Do you want to remove fund or deposite fund");
		sbf.append("\n");
		sbf.append("To remove fund press (R) and hit enter");
		sbf.append("\n");
		sbf.append("To deposite fund press (D) and hit enter");
		sbf.append("\n");
		sbf.append("------------------------");
		return sbf.toString();
	}

	public String getErrorMessage() {
		StringBuffer sbf = new StringBuffer();
		sbf.append("------------------------");
		sbf.append("\n");
		sbf.append("enter either (R) or (D)");
		sbf.append("\n");
		sbf.append("------------------------");
		return sbf.toString();
	}

	public boolean validate(String command) {

		if (command.length() == 1) {
			if (command.equals("R")) {
				this.transactionOption = TransactionOption.REMOVE_FUND;
				return true;
			} else if (command.equals("D")) {
				this.transactionOption = TransactionOption.DEPOSITE_FUND;
				return true;
			}
		}

		return false;
	}

}
