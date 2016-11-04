package java_oo.parkinglot;

public class ParkingLotTest {

	public static void main(String[] args) {

		Vehicle car = new Vehicle("Car", true);

		ParkingLot pLot = new ParkingLot();

		pLot.park(car);

	}

}
