package org.headfirst.ooad.game.gsf.model;

import java.util.List;

public class Tile {

	private Terrain terrian;
	
	private List<Unit> units;

	private int y, x = 0;

	public Tile(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public String toString() {
		return (this.x + 1) + "," + (this.y + 1);
	}
}
