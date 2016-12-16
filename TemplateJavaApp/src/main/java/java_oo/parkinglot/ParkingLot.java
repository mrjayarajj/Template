package java_oo.parkinglot;

import java.util.HashMap;
import java.util.Map;

import java_oo.parkinglot.ParkingLotTest.ParkingLotHelper;

public class ParkingLot {

	private Parker handicappedCompactParker = null;

	private Parker handicappedRegularParker = null;

	private Parker regularParker = null;

	private Parker compactParker = null;

	private Map<Vehicle, Parker> vehicleParker = new HashMap<Vehicle, Parker>();

	public ParkingLot() {

	}

	public ParkingLot(ParkingLotHelper parkingLotHelper) {
		this.handicappedCompactParker = parkingLotHelper.handicappedCompactParker;
		this.handicappedRegularParker = parkingLotHelper.handicappedRegularParker;
		this.regularParker = parkingLotHelper.regularParker;
		this.compactParker = parkingLotHelper.compactParker;
	}

	public void parkVehicle(Vehicle v) {
		if (v instanceof RegularVehicle && v instanceof Car) {
			parkRegularVehicle(v);
		}
		if (v instanceof CompactVehicle) {
			parkCompactVehicle(v);
		}
	}

	public void parkRegularVehicle(Vehicle v) {

		Parker parker = null;

		if (v.isEligibleForHandicapped()) {
			parker = handicappedRegularParker;
		} else {
			parker = regularParker;
		}

		parker.park(v);

		vehicleParker.put(v, parker);
	}

	public void parkCompactVehicle(Vehicle v) {

		Parker parker = null;

		if (v.isEligibleForHandicapped()) {
			parker = handicappedCompactParker;
		} else {
			parker = compactParker;
		}

		parker.park(v);

		vehicleParker.put(v, parker);
	}

	public void unParkCompactVehicle(CompactVehicle v) {

		Parker parker = vehicleParker.get(v);
		parker.unPark(v);
	}

}
