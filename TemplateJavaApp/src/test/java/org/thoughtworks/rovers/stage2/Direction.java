package org.thoughtworks.rovers.stage2;

public enum Direction {

	N, E, W, S;

	public Direction getNextDirection(Command command) {

		Direction currentDirection = this;

		if (command == Command.L) {
			if (currentDirection == Direction.N) {
				return Direction.W;
			}
			if (currentDirection == Direction.E) {
				return Direction.N;
			}
			if (currentDirection == Direction.W) {
				return Direction.S;
			}
			if (currentDirection == Direction.S) {
				return Direction.E;
			}
		}
		if (command == Command.R) {
			if (currentDirection == Direction.N) {
				return Direction.E;
			}
			if (currentDirection == Direction.E) {
				return Direction.S;
			}
			if (currentDirection == Direction.W) {
				return Direction.N;
			}
			if (currentDirection == Direction.S) {
				return Direction.W;
			}
		}
		throw new IllegalArgumentException();
	}
}
