package java_oo.parkinglot;

public class Vehicle {

	private boolean handicapped = false;

	public Vehicle(String string) {
		// TODO Auto-generated constructor stub
	}

	public Vehicle(String string, boolean handicapped) {
		this.handicapped = handicapped;
	}

	public boolean isEligibleForHandicapped() {
		return this.handicapped;
	}

}
