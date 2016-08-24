package org.thoughtworks.rovers.stage2;

public class Main {

	public static void main(String[] args) {
		Plateau p = new Plateau(new Coordinate(0, 0), new Coordinate(5, 5));

		Rover r1 = new Rover(new Coordinate(1, 2), Direction.N, p);
		r1.processRover("LMLMLMLMM");

		System.out.println(r1.getCoordinate() + " " + r1.getDirection());

		Rover r2 = new Rover(new Coordinate(3, 3), Direction.E, p);
		r2.processRover("MMRMMRMRRM");

		System.out.println(r2.getCoordinate() + " " + r2.getDirection());
	}

}
