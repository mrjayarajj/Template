package org.thoughtworks.game.main;

import org.thoughtworks.game.biz.GLGame;
import org.thoughtworks.game.biz.GLGameRule;
import org.thoughtworks.game.vo.CardDeck;
import org.thoughtworks.game.vo.Person;

public class Main {

	public static void main(String[] args) {
		
		Person jayaraj = new Person("jayaraj");
		Person chandra = new Person("chandra");
				
		GLGame glGame = new GLGame(new CardDeck());
		
		glGame.addPerson(jayaraj);
		glGame.addPerson(chandra);
		
		glGame.setGameRules(new GLGameRule());
		
		glGame.startGame();
		
	}
	
}
