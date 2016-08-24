package org.thoughtworks.game.vo;

import java.util.ArrayList;
import java.util.List;

public class CardDeck {

	private List<Card> cardList = new ArrayList<Card>();

	// TODO cardList must be a argument.
	public CardDeck() {
		generateCard(Card.SPAD, 52);
		generateCard(Card.CLEAVER, 39);
		generateCard(Card.DIAMOND, 26);
		generateCard(Card.HEART, 13);
	}

	private void generateCard(String symbol, int value) {

		cardList.add(new HCard(Card.ACE, symbol, value));

		cardList.add(new HCard(Card.KING, symbol, value - 1));
		cardList.add(new HCard(Card.QUEEN, symbol, value - 2));
		cardList.add(new HCard(Card.JACK, symbol, value - 3));

		cardList.add(new NumberCard(2, symbol, value - 4));
		cardList.add(new NumberCard(3, symbol, value - 5));
		cardList.add(new NumberCard(4, symbol, value - 6));
		cardList.add(new NumberCard(5, symbol, value - 7));
		cardList.add(new NumberCard(6, symbol, value - 8));
		cardList.add(new NumberCard(7, symbol, value - 9));
		cardList.add(new NumberCard(8, symbol, value - 10));
		cardList.add(new NumberCard(9, symbol, value - 11));
		cardList.add(new NumberCard(10, symbol, value - 12));

	}

	public List<Card> getCardList() {
		return cardList;
	}

	public void setCardList(List<Card> cardList) {
		this.cardList = cardList;
	}

	public Card removeCard(int num) {		
		return cardList.remove(num);
	}

}
