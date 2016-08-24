package java_oo.atm.learn;

import java.math.BigDecimal;

public class ATM {

	private BankNetwork bankNetwork = new BankNetwork();

	private BankComputer homeBankComputer;

	private Card card;

	public Card getCard() {
		return card;
	}

	public ATM() {
		homeBankComputer = bankNetwork.getBankComputer("BOA");
		homeBankComputer.displayBankInfo();
	}

	public void startup() {

		Card card = new Card(new CardIO().getCardNumber());

		String pin = new PinIO(this).getPinNumber();

		TransactionOption transactionOption = new TransactionOptionIO().getTransactionOption();

		AccountType accountType = new AccountTypeIO().getAccountType();

		BigDecimal fundValue = new FundIO().getFundValue();

		bankNetwork.isATMCardsBankInNetwork(card);

	}

}
