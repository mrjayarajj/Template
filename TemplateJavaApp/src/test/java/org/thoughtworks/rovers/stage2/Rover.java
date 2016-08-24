package org.thoughtworks.rovers.stage2;

public class Rover {

	private Coordinate coordinate = null;

	private Direction direction = null;

	private Plateau plateau = null;

	public Rover(Coordinate coordinate, Direction direction, Plateau plateau) {
		this.coordinate = coordinate;
		this.direction = direction;
		this.plateau = plateau;
	}

	public void processRover(String completeCommand) {
		String commands[] = completeCommand.split("");

		for (String command : commands) {
			if (command.equals("L")) {
				moveRoverDirection(Command.L);
			}
			if (command.equals("R")) {
				moveRoverDirection(Command.R);
			}
			if (command.equals("M")) {
				moveRoverPosition();
			}
		}

		boolean withinLimit = coordinate.checkLimit(plateau.getMinCoordinate(), plateau.getMaxCoordinate());

		if (withinLimit == false) {
			throw new IllegalArgumentException("illegal move exceed plateau range");
		}
	}

	public void moveRoverPosition() {
		if (direction == Direction.N) {
			getCoordinate().setY(getCoordinate().getY() + 1);
		}
		if (direction == Direction.E) {
			getCoordinate().setX(getCoordinate().getX() + 1);
		}
		if (direction == Direction.W) {
			getCoordinate().setX(getCoordinate().getX() - 1);
		}
		if (direction == Direction.S) {
			getCoordinate().setY(getCoordinate().getY() - 1);
		}
	}

	public void moveRoverDirection(Command command) {
		setDirection(getDirection().getNextDirection(command));
	}

	public Coordinate getCoordinate() {
		return coordinate;
	}

	public Direction getDirection() {
		return direction;
	}

	public void setCoordinate(Coordinate coordinate) {
		this.coordinate = coordinate;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}
}
