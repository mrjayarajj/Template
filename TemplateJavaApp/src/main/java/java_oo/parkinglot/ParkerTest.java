package java_oo.parkinglot;

import static java_oo.parkinglot.ParkingSpace.COMPACT_PARKING_SPACE;

public class ParkerTest {

	public static void main(String[] args) {
		
		Parker compactParker = new Parker();
		compactParker.addParkingSpace(new BuildingParkingSpace(COMPACT_PARKING_SPACE, 1, 5));
		compactParker.addParkingSpace(new BuildingParkingSpace(COMPACT_PARKING_SPACE, 1, 6));
		compactParker.addParkingSpace(new BuildingParkingSpace(COMPACT_PARKING_SPACE, 2, 7));
		compactParker.addParkingSpace(new BuildingParkingSpace(COMPACT_PARKING_SPACE, 1, 1));

		System.out.println(compactParker);
		
	}

}
