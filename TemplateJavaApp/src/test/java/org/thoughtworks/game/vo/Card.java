package org.thoughtworks.game.vo;

public abstract class Card {
	
	public static final String SPAD = "spade";

	public static final String DIAMOND = "diamond";

	public static final String HEART = "heart";

	public static final String CLEAVER = "cleaver";

	public static final String JACK = "J";

	public static final String KING = "K";

	public static final String QUEEN = "Q";

	public static final String ACE = "A";

	private String symbol = null;
	
	private int value = 0;

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}		
}
