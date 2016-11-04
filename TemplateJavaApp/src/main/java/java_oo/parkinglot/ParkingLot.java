package java_oo.parkinglot;

import static java_oo.parkinglot.ParkingSpace.COMPACT;
import static java_oo.parkinglot.ParkingSpace.HANDICAPPED;
import static java_oo.parkinglot.ParkingSpace.REGULAR;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot {

	private List<ParkingSpace> parkingSpaceList = new ArrayList<ParkingSpace>();

	public ParkingLot() {
		parkingSpaceList.add(new BuildingParkingSpace(COMPACT, 1, 1));
		parkingSpaceList.add(new BuildingParkingSpace(REGULAR, 1, 2));
		parkingSpaceList.add(new BuildingParkingSpace(HANDICAPPED, 1, 3));
		parkingSpaceList.add(new BuildingParkingSpace(COMPACT, 1, 4));
		parkingSpaceList.add(new BuildingParkingSpace(COMPACT, 1, 5));
	}

	public void park(Vehicle v) {

		if (v.isEligibleForHandicapped()) {
			ParkingSpace pSpace = getVacantParkingSpace();
			pSpace.setOccupied(true);
		}else{
			ParkingSpace pSpace = getVacantParkingSpace();
			pSpace.setOccupied(true);
		}

	}

	private ParkingSpace getVacantParkingSpace() {

		ParkingSpace pSpace;

		return null;
	}

}
