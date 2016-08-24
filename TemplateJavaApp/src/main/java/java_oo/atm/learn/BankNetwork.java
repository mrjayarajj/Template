package java_oo.atm.learn;

import java.util.ArrayList;
import java.util.List;

public class BankNetwork {

	private List<BankComputer> bankComputers = new ArrayList<BankComputer>();

	public void addBankToNetwork(BankComputer bankComputer) {
		this.bankComputers.add(bankComputer);
	}

	public BankComputer getBankComputer(String bankName) {
		for (BankComputer bank : bankComputers) {
			if (bank.equals(bankName)) {
				return bank;
			}
		}
		return null;
	}

	public boolean isATMCardsBankInNetwork(Card theCard) {

		Boolean cardVerification = false;

		int cardBankID = getFirstTwoDigits(theCard.getStripNumber());

		for (BankComputer bank : bankComputers) {

			if (bank.getBankID() == cardBankID) {

				cardVerification = true;

			}

		}

		return cardVerification;

	}

	// Returns the first 2 digits from the number passed to it

	static int getFirstTwoDigits(int stripNumber) {

		String stringOfStripNumber = Integer.toString(stripNumber);

		// Get the first 2 numbers from the stripNumber and save them as an int

		int bankIDFromStrip = Integer.parseInt(stringOfStripNumber.substring(0, 2));

		return bankIDFromStrip;

	}

	public boolean verifyThePIN(Transaction theTransaction) {

		boolean cardVerification = false;

		for (BankComputer bank : bankComputers) {

			if (bank.getBankID() == theTransaction.getBankID()) {

				bank.verifyThePIN(theTransaction);

				cardVerification = true;

			} else {

				System.out.println("The card Bank ID doesn't match any in the system");

			}

		}

		return cardVerification;

	}

	public void requestWithdrawalAmt(Transaction theTransaction) {

		if (theTransaction.getDidCardVerify()) {

			for (BankComputer bank : bankComputers) {

				if (bank.getBankID() == theTransaction.getBankID()) {

					bank.requestWithdrawalAmt(theTransaction);

				}

			}

		} else {

			System.out.println("An Error Occurred During the Withdrawal");

		}

	}

}
