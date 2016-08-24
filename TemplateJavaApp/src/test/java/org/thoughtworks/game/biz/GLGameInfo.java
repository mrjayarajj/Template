package org.thoughtworks.game.biz;

import java.util.ArrayList;
import java.util.List;

import org.thoughtworks.game.vo.Card;
import org.thoughtworks.game.vo.Person;

public class GLGameInfo {

	public GLGameInfo(Person person){
		this.person = person;
	}
	
	private Person person;

	private List<Card> cardList = new ArrayList<Card>();

	private int total = 0;

	public List<Card> getCardList() {
		return cardList;
	}

	public void addCard(Card card) {
		cardList.add(card);
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

}
