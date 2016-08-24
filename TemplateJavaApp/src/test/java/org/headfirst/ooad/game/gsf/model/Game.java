package org.headfirst.ooad.game.gsf.model;

public class Game {

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private Board board;

	public void createBoard(int width, int height) {
		board = new Board(width, height);		
	}
}
