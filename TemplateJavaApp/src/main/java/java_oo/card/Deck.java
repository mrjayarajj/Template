package java_oo.card;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class Deck {

	private List<Card> cards = new ArrayList<Card>();

	public Deck() {

		CardSymbol cardSymbolArray[] = new CardSymbol[] { CardSymbol.DIAMOND, CardSymbol.SPADE, CardSymbol.CLUB,
				CardSymbol.HEART };

		for (int i = 0; i < cardSymbolArray.length; i++) {
			for (int j = 0; j < 10; j++) {
				addCard(new NumberCard(j + 1, cardSymbolArray[i]));
			}
			addCard(new FaceCard(FaceCardValue.JACK, cardSymbolArray[i]));
			addCard(new FaceCard(FaceCardValue.QUEEN, cardSymbolArray[i]));
			addCard(new FaceCard(FaceCardValue.KING, cardSymbolArray[i]));
			addCard(new FaceCard(FaceCardValue.ACE, cardSymbolArray[i]));
		}

		addCard(new JokerCard(1));
		addCard(new JokerCard(2));
	}

	public void addCard(Card card) {
		this.cards.add(card);
	}

	public void removeCard(Card card) {
		this.cards.remove(card.toString());
	}

	public Deck shuffle() {
		for (int i = 0; i < cards.size(); i++) {
			Random r = new Random();
			int ran = r.nextInt(cards.size());
			Card c = cards.remove(ran);
			cards.add(c);
		}
		return this;
	}

	public String toString() {
		StringBuffer sbf = new StringBuffer();
		for (Card c : cards) {
			sbf.append(c);
			sbf.append("\n");
		}
		return sbf.toString();
	}
}

class Card {

	private String cardValue;

	public Card(String cardValue) {
		this.cardValue = cardValue;
	}

	public String toString() {
		return this.cardValue;
	}
}

class NumberCard extends Card {

	private CardSymbol cardSymbol;
	private int cardValue;

	public NumberCard(int cardValue, CardSymbol cardSymbol) {
		super(cardValue + "-" + cardSymbol);
		this.cardValue = cardValue;
		this.cardSymbol = cardSymbol;
	}
}

class FaceCard extends Card {

	private FaceCardValue faceCardValue;
	private CardSymbol cardSymbol;

	public FaceCard(FaceCardValue faceCardValue, CardSymbol cardSymbol) {
		super(faceCardValue + "-" + cardSymbol);
		this.faceCardValue = faceCardValue;
		this.cardSymbol = cardSymbol;
	}
}

class JokerCard extends Card {

	public JokerCard(int id) {
		super("Joker-" + id);
	}

}

enum FaceCardValue {
	KING, QUEEN, JACK, ACE;
}

enum CardSymbol {
	DIAMOND, SPADE, CLUB, HEART;
}
