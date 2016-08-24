package java_oo.atm.learn;

enum AccountType {
	CHECKING, SAVING;
}

public class AccountTypeIO extends IO {

	private AccountType accountType;

	public AccountTypeIO(){
		execute();
	}
	
	public AccountType getAccountType() {
		return accountType;
	}

	public String getDisplayMessage() {
		StringBuffer sbf = new StringBuffer();
		sbf.append("------------------------");
		sbf.append("\n");
		sbf.append("Enter your account type");
		sbf.append("\n");
		sbf.append("Enter (C) for checking account");
		sbf.append("\n");
		sbf.append("Enter (S) for saving account");
		sbf.append("\n");
		sbf.append("------------------------");
		return sbf.toString();
	}

	public String getErrorMessage() {
		StringBuffer sbf = new StringBuffer();
		sbf.append("------------------------");
		sbf.append("\n");
		sbf.append("enter either (C) or (S)");
		sbf.append("\n");
		sbf.append("------------------------");
		return sbf.toString();
	}

	public boolean validate(String command) {
		if (command.length() == 1) {
			if (command.equals("S")) {
				this.accountType = AccountType.SAVING;
				return true;
			} else if (command.equals("C")) {
				this.accountType = AccountType.CHECKING;
				return true;
			}
		}

		return false;
	}
}
