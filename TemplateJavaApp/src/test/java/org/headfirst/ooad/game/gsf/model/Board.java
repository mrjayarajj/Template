package org.headfirst.ooad.game.gsf.model;

import java.util.ArrayList;
import java.util.List;

public class Board {

	private int width = 0;
	private int height = 0;

	private List<List<Tile>> tiles = null;

	public Board(int width, int height) {
		this.width = width;
		this.height = height;
		initilize();
	}

	public void initilize() {
		tiles = new ArrayList<List<Tile>>(width);
		for (int x = 0; x < width; x++) {
			tiles.add(x, new ArrayList<Tile>(height));
			for (int y = 0; y < height; y++) {
				tiles.get(x).add(new Tile(x, y));
			}
		}
	}

	public void show() {
		for (List<Tile> ys : tiles) {
			for (Tile tile : ys) {
				System.out.println(tile);
			}
		}
	}

	
}
