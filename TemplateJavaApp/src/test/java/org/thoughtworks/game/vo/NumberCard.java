package org.thoughtworks.game.vo;

public class NumberCard extends Card {

	private int cardNumber = 0;

	public NumberCard(int cardNumber, String symbol, int value) {
		this.cardNumber = cardNumber;
		setSymbol(symbol);
		setValue(value);
	}

	public int getCardNumber() {
		return cardNumber;
	}

	public String toString() {
		return this.cardNumber + "-" + getSymbol()+"="+getValue();
	}
}
