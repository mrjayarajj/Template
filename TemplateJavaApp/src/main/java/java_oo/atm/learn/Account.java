package java_oo.atm.learn;

public class Account {

	private BankComputer bankComputer;
	private int acctNumber;
	private String customerName;
	private String acctType;
	private double acctBalance;
	private Card card;
	
	public Card getCard() {
		return card;
	}

	static int accountNumberIncrementor = 100000;

	public Account(int bankID,String bankName, String customerName, String acctType, double acctBalance, int pin) {

		this.bankComputer = new BankComputer(bankID,bankName);

		this.customerName = customerName;
		this.acctType = acctType;
		this.acctBalance = acctBalance;

		int stripNumber = generateStripNumber(bankID);

		this.card = new Card(stripNumber, pin);

		this.acctNumber = generateAccountNumber(stripNumber, acctType);

	}

	public BankComputer getBankComputer() {
		return bankComputer;
	}

	public int getAcctNumber() {
		return acctNumber;
	}

	public String getCustomerName() {
		return customerName;
	}

	public String getAcctType() {
		return acctType;
	}

	public double getAcctBalance() {
		return acctBalance;
	}

	public void setAcctBalance(double newAcctBalance) {

		this.acctBalance = newAcctBalance;

	}

	// Generates strip numbers by adding the bankID to the front
	// of the automatically generated middle part of each account
	// number taken from accountNumberIncrementor

	public int generateStripNumber(int bankID) {

		accountNumberIncrementor++;

		int newStripNumber = (bankID * 1000000) + accountNumberIncrementor;

		return newStripNumber;

	}

	public int generateAccountNumber(int stripNumber, String acctType) {

		if ((acctType.startsWith("s")) || (acctType.startsWith("S"))) {

			// Savings is stripNumber with a 1 at the end

			acctNumber = (stripNumber * 10) + 1;

		} else {

			// Checking is stripNumber with a 2 at the end

			acctNumber = (stripNumber * 10) + 2;

		}

		return acctNumber;

	}
}
