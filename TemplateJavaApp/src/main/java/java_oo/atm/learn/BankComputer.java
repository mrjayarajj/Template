package java_oo.atm.learn;

import java.util.ArrayList;
import java.util.List;

public class BankComputer {

	private int bankID;

	private String name;

	private List<Account> accountList = new ArrayList<Account>();

	public boolean equals(Object obj) {
		return this.bankID == ((BankComputer) obj).bankID;
	}

	public BankComputer(int bankID, String name) {
		this.bankID = bankID;
		this.name = name;
	}

	public void displayBankInfo() {
		System.out.println("-----------------------");
		System.out.println("Welcome to " + this.name);
		System.out.println("------------------------");
	}

	public int getBankID() {
		return bankID;
	}

	// Verifies that a card with the strip number and PIN is in a bank

	public Boolean verifyThePIN(Transaction theTransaction) {

		Boolean cardVerification = false;

		if (theTransaction.getDidCardVerify()) {

			for (Account account : accountList) {

				if ((account.getCard().getPIN() == theTransaction.getPIN())
						&& (account.getCard().getStripNumber() == theTransaction.getStripNumber())) {

					cardVerification = true;

					theTransaction.setCustomerName(account.getCustomerName());

				}

			}

		}

		return cardVerification;

	}

	public void requestWithdrawalAmt(Transaction theTransaction) {

		for (Account account : accountList) {

			if (account.getAcctNumber() == theTransaction.getAccountNumberUsed()) {

				if (account.getAcctBalance() >= theTransaction.getWithdrawalAmt()) {

					double newAcctBalance = account.getAcctBalance() - theTransaction.getWithdrawalAmt();

					account.setAcctBalance(newAcctBalance);

					theTransaction.setAcctBalance(newAcctBalance);

				} else {

					System.out.println("You can't withdrawal that much money");

				}

			}

		}

	}

}
