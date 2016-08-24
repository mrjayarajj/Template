package org.thoughtworks.rovers.stage1.board;

public class Board {

	public Board(int x, int y, Direction direction) {
		this.x = x;
		this.y = y;
		this.direction = direction;
	}

	private int x = 0;

	private int y = 0;

	private Direction direction = null;;

	public void applyMoves(String completeCommand) {
		
		String commands[] = completeCommand.split("");

		for (String command : commands) {
			if (command.equals("L")) {
				direction = applyLeftToDirection();
			}
			if (command.equals("R")) {
				direction = applyRightToDirection();
			}
			if (command.equals("M")) {
				applyMove();
			}
		}
	}

	public String toString() {
		String s = "";
		s = s + " X=" + x;
		s = s + " Y=" + y;
		s = s + " D=" + direction;
		return s;
	}

	private void applyMove() {
		if (direction == Direction.N) {
			y++;
		}
		if (direction == Direction.E) {
			x++;
		}
		if (direction == Direction.W) {
			x--;
		}
		if (direction == Direction.S) {
			y--;
		}
	}

	private Direction applyRightToDirection() {
		if (direction == Direction.N) {
			return Direction.E;
		}
		if (direction == Direction.E) {
			return Direction.S;
		}
		if (direction == Direction.W) {
			return Direction.N;
		}
		if (direction == Direction.S) {
			return Direction.W;
		}
		throw new IllegalArgumentException();
	}

	private Direction applyLeftToDirection() {
		if (direction == Direction.N) {
			return Direction.W;
		}
		if (direction == Direction.E) {
			return Direction.N;
		}
		if (direction == Direction.W) {
			return Direction.S;
		}
		if (direction == Direction.S) {
			return Direction.E;
		}
		throw new IllegalArgumentException();
	}

}
