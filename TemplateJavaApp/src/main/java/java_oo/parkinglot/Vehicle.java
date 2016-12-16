package java_oo.parkinglot;

public class Vehicle {

	private boolean handicappedTag = false;

	public Vehicle(String string) {
	}

	public Vehicle(String string, boolean handicappedTag) {
		this.handicappedTag = handicappedTag;
	}

	public boolean isEligibleForHandicapped() {
		return this.handicappedTag;
	}

}

class CompactVehicle extends Vehicle {

	public CompactVehicle(String string) {
		super(string);
	}

	public CompactVehicle(String string, boolean handicapped) {
		super(string, handicapped);
	}

}

class Car extends CompactVehicle {

	public Car(String string) {
		super(string);
	}

}

class Bike extends CompactVehicle {

	public Bike(String string) {
		super(string);
	}

}

class RegularVehicle extends Vehicle {

	public RegularVehicle(String string) {
		super(string);
	}

	public RegularVehicle(String string, boolean handicapped) {
		super(string, handicapped);
	}

}
