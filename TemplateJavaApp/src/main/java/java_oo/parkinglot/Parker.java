package java_oo.parkinglot;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class Parker {

	private TreeSet<ParkingSpace> sortedParkingSpace = new TreeSet<ParkingSpace>();;

	private Map<Vehicle, ParkingSpace> occupliedPSMap = new HashMap<Vehicle, ParkingSpace>();;

	public void addParkingSpace(ParkingSpace ps) {
		sortedParkingSpace.add(ps);
	}

	public void park(Vehicle v) {
		ParkingSpace pSpace = sortedParkingSpace.pollFirst();
		occupliedPSMap.put(v, pSpace);
	}

	public void unPark(Vehicle v) {
		ParkingSpace pSpace = occupliedPSMap.remove(v);
		sortedParkingSpace.add(pSpace);
	}

	@Override
	public String toString() {
		return this.sortedParkingSpace + "\n" + this.occupliedPSMap;
	}

}
