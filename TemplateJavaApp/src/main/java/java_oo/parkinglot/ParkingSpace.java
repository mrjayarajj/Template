package java_oo.parkinglot;

public class ParkingSpace {

	public static final String COMPACT = "compact";
	public static final String REGULAR = "regular";
	public static final String HANDICAPPED = "Handicapped";

	private boolean occupied = false;

	public Object isVacant() {
		return this.occupied;
	}

	public void setOccupied(boolean occupied) {
		this.occupied = occupied;
	}

}

class BuildingParkingSpace extends ParkingSpace {

	public BuildingParkingSpace(String type, int parkingSpaceFloor, int paarkingSpaceNumber) {
	}
}

class OutsideParkingSpace extends ParkingSpace {

	public OutsideParkingSpace(String type, int paarkingSpaceNumber) {
	}
}
