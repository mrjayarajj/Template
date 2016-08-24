package org.thoughtworks.rovers.stage2;

public class Coordinate {

	private int x = 0;

	private int y = 0;

	public Coordinate(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public boolean checkLimit(Coordinate minCoordinate,Coordinate maxCoordinate) {
		boolean withinMinLimit = this.x >= minCoordinate.getX() && this.y >= minCoordinate.getY();
		boolean withinMaxLimit = this.x <= maxCoordinate.getX() && this.y <= maxCoordinate.getY();
		return withinMinLimit && withinMaxLimit;
	}

	public String toString() {
		return "x=" + this.x + "y=" + this.y;
	}
}

