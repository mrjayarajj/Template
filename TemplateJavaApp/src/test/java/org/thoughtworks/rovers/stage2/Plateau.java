package org.thoughtworks.rovers.stage2;

public class Plateau {

	private Coordinate minCoordinate = null;

	private Coordinate maxCoordinate = null;

	public Plateau(Coordinate minCoordinate, Coordinate maxCoordinate) {
		this.minCoordinate = minCoordinate;
		this.maxCoordinate = maxCoordinate;
	}

	public Coordinate getMaxCoordinate() {
		return maxCoordinate;
	}

	public Coordinate getMinCoordinate() {
		return minCoordinate;
	}
}