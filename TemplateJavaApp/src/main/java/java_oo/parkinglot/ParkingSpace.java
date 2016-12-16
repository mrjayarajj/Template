package java_oo.parkinglot;

public class ParkingSpace {

	public static final String COMPACT_PARKING_SPACE = "CompactPS";
	public static final String REGULAR_PARKING_SPACE = "RegularPS";
	public static final String HANDICAPPED_COMPACT_PARKING_SPACE = "HandicappedCompactPS";
	public static final String HANDICAPPED_REGULAR_PARKING_SPACE = "HandicappedRegularPS";
}

class BuildingParkingSpace extends ParkingSpace implements Comparable {

	private String type;
	private int parkingSpaceFloor = -1;
	private int paarkingSpaceNumber = -1;

	public BuildingParkingSpace(String type, int parkingSpaceFloor, int parkingSpaceNumber) {
		this.type = type;
		this.paarkingSpaceNumber = parkingSpaceNumber;
		this.parkingSpaceFloor = parkingSpaceFloor;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getParkingSpaceFloor() {
		return parkingSpaceFloor;
	}

	public void setParkingSpaceFloor(int parkingSpaceFloor) {
		this.parkingSpaceFloor = parkingSpaceFloor;
	}

	public int getPaarkingSpaceNumber() {
		return paarkingSpaceNumber;
	}

	public void setPaarkingSpaceNumber(int paarkingSpaceNumber) {
		this.paarkingSpaceNumber = paarkingSpaceNumber;
	}

	@Override
	public String toString() {
		return this.type + "-" + this.parkingSpaceFloor + "-" + this.paarkingSpaceNumber;
	}

	@Override
	public boolean equals(Object o) {
		BuildingParkingSpace otherBuildingParkingSpace = (BuildingParkingSpace) o;
		return new Integer(this.parkingSpaceFloor).equals(otherBuildingParkingSpace.getParkingSpaceFloor())
				&& new Integer(this.paarkingSpaceNumber).equals(otherBuildingParkingSpace.getPaarkingSpaceNumber());
	}

	@Override
	public int hashCode() {
		return this.parkingSpaceFloor + this.paarkingSpaceNumber;
	}

	@Override
	public int compareTo(Object o) {
		BuildingParkingSpace otherBuildingParkingSpace = (BuildingParkingSpace) o;

		int sFloor = new Integer(this.parkingSpaceFloor).compareTo(otherBuildingParkingSpace.getParkingSpaceFloor());

		if (sFloor == 0) {
			return new Integer(this.paarkingSpaceNumber).compareTo(otherBuildingParkingSpace.getPaarkingSpaceNumber());
		} else {
			return sFloor;
		}
	}

}

class OutsideParkingSpace extends ParkingSpace {

	private String type;
	private int paarkingSpaceNumber = -1;

	public OutsideParkingSpace(String type, int parkingSpaceNumber) {
		this.type = type;
		this.paarkingSpaceNumber = parkingSpaceNumber;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getPaarkingSpaceNumber() {
		return paarkingSpaceNumber;
	}

	public void setPaarkingSpaceNumber(int paarkingSpaceNumber) {
		this.paarkingSpaceNumber = paarkingSpaceNumber;
	}

}
