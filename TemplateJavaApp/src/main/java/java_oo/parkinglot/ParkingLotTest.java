package java_oo.parkinglot;

import static java_oo.parkinglot.ParkingSpace.COMPACT_PARKING_SPACE;
import static java_oo.parkinglot.ParkingSpace.HANDICAPPED_COMPACT_PARKING_SPACE;
import static java_oo.parkinglot.ParkingSpace.HANDICAPPED_REGULAR_PARKING_SPACE;
import static java_oo.parkinglot.ParkingSpace.REGULAR_PARKING_SPACE;

public class ParkingLotTest {

	public static void main(String[] args) {
		CompactVehicle car = new CompactVehicle("CupperCar", true);
		ParkingLot bartPLot = new ParkingLot(new ParkingLotHelper());
		bartPLot.parkVehicle(car);
	}

	static class ParkingLotHelper {

		Parker handicappedCompactParker = null;

		Parker handicappedRegularParker = null;

		Parker regularParker = null;

		Parker compactParker = null;

		public ParkingLotHelper() {
			handicappedCompactParker = new Parker();
			handicappedCompactParker.addParkingSpace(new BuildingParkingSpace(HANDICAPPED_COMPACT_PARKING_SPACE, 1, 1));
			handicappedCompactParker.addParkingSpace(new BuildingParkingSpace(HANDICAPPED_COMPACT_PARKING_SPACE, 1, 2));

			handicappedRegularParker = new Parker();
			handicappedRegularParker.addParkingSpace(new BuildingParkingSpace(HANDICAPPED_REGULAR_PARKING_SPACE, 1, 3));
			handicappedRegularParker.addParkingSpace(new BuildingParkingSpace(HANDICAPPED_REGULAR_PARKING_SPACE, 1, 4));

			compactParker = new Parker();
			compactParker.addParkingSpace(new BuildingParkingSpace(COMPACT_PARKING_SPACE, 1, 5));
			compactParker.addParkingSpace(new BuildingParkingSpace(COMPACT_PARKING_SPACE, 1, 6));
			compactParker.addParkingSpace(new BuildingParkingSpace(COMPACT_PARKING_SPACE, 1, 7));
			compactParker.addParkingSpace(new BuildingParkingSpace(COMPACT_PARKING_SPACE, 1, 8));

			regularParker = new Parker();
			regularParker.addParkingSpace(new BuildingParkingSpace(REGULAR_PARKING_SPACE, 1, 9));
			regularParker.addParkingSpace(new BuildingParkingSpace(REGULAR_PARKING_SPACE, 1, 10));
		}
	}
}
