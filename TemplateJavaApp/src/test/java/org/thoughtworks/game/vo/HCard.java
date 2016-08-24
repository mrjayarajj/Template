package org.thoughtworks.game.vo;

public class HCard extends Card {

	private String hType = null;

	public HCard(String hType, String symbol, int value) {
		this.hType = hType;
		setSymbol(symbol);
		setValue(value);
	}

	public String getHType() {
		return hType;
	}

	public String toString() {
		return this.hType + "-" + getSymbol() + "=" + getValue();
	}

}
