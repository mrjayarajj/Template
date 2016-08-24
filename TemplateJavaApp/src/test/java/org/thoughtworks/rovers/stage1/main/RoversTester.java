package org.thoughtworks.rovers.stage1.main;

import org.thoughtworks.rovers.stage1.board.Board;
import org.thoughtworks.rovers.stage1.board.Direction;

public class RoversTester {

	public static void main(String[] args) {
		
		Board b  = new Board(1,1,Direction.N);
		b.applyMoves("LMLMLMLMM");
		System.out.println(b);
		
		
		Board b2  = new Board(3,3,Direction.E);
		b2.applyMoves("MMRMMRMRRM");
		System.out.println(b2);
	}
	
}
