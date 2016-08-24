package org.headfirst.ooad.game.gsf.controller;

import org.headfirst.ooad.game.gsf.model.Board;
import org.headfirst.ooad.game.gsf.model.Game;

public class GameController {
	
	private Game game = new Game();
	
	public String createGame(){
		game.setName("Age of Empires III");
		return null;
	}
	
	public String createBoard(){		
		game.createBoard(10, 10);		
		return null;
	}
	

	public static void main(String[] args) {
		GameController controller = new GameController();
		controller.createGame();
		controller.createBoard();
	}
}
