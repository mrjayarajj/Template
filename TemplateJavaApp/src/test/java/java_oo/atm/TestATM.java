package java_oo.atm;

import java_oo.atm.learn.ATM;
import java_oo.atm.learn.Account;
import java_oo.atm.learn.BankNetwork;

public class TestATM {

	public static void main(String[] args) {

		ATM atm = buildBankSystem();
		atm.startup();

	}

	private static ATM buildBankSystem() {

		Account theAccount = new Account(1, "XYZ Bank", "Derek Banas", "C", 10000.00, 1234);

		BankNetwork bankNetwork = new BankNetwork();
		bankNetwork.addBankToNetwork(theAccount.getBankComputer());

		ATM atm = new ATM();

		return atm;
	}
}
