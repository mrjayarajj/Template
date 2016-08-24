package org.thoughtworks.game.biz;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.thoughtworks.game.vo.Card;
import org.thoughtworks.game.vo.CardDeck;
import org.thoughtworks.game.vo.Person;

public class GLGame {

	private static final int NUMBER_OF_CARD = 2;

	private List<GLGameInfo> gameInfoList = new ArrayList<GLGameInfo>();

	private CardDeck cardDeck = null;

	private GameRule gameRule = null;

	public GLGame(CardDeck cardDeck) {
		this.cardDeck = cardDeck;
	}

	public void setGameRules(GameRule gameRule) {
		this.gameRule = gameRule;
	}

	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			Random rand = new Random();
			int num = rand.nextInt(52);
			System.out.println(num);
		}
	}

	public void startGame() {

		if (gameInfoList.size() != 2) {
			throw new RuntimeException("game must be played by two person only");
		}
		
		for (int count = 0; count < NUMBER_OF_CARD; count++) {

			for (GLGameInfo gameInfo : gameInfoList) {
				
				Random rand = new Random();
				int num = rand.nextInt(getCardDeck().getCardList().size());

				Card card = getCardDeck().removeCard(num);

				gameInfo.addCard(card);			
				
			}

		}

		GLGameInfo winner = gameRule.process(gameInfoList);

		System.out.println("Winner is " + winner.getPerson().getName());

	}

	public CardDeck getCardDeck() {
		return cardDeck;
	}

	public void setCardDeck(CardDeck cardDeck) {
		this.cardDeck = cardDeck;
	}

	public void addPerson(Person person) {
		gameInfoList.add(new GLGameInfo(person));
	}

}
