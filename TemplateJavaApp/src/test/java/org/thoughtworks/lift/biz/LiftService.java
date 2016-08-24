package org.thoughtworks.lift.biz;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.thoughtworks.lift.vo.Lift;
import org.thoughtworks.lift.vo.LiftCommand;

public class LiftService {

	private Map<Integer, List<Lift>> liftExecuterMap;

	public LiftService(int numberOfFloors) {
		liftExecuterMap = new TreeMap<Integer, List<Lift>>();

		for (int floorNumber = 0; floorNumber < numberOfFloors; floorNumber++) {
			liftExecuterMap.put(floorNumber, null);
		}
	}

	public void addLiftExecuter(int floorNumber, Lift lift) {

		List<Lift> liftList = liftExecuterMap.get(floorNumber);

		if (liftList == null) {
			liftList = new ArrayList<Lift>();
		}

		liftList.add(lift);

		liftExecuterMap.put(floorNumber, liftList);
	}

	public Lift getLift(int userFloorNumber, LiftCommand command) {

		Lift lift = getHigherLift(userFloorNumber);
		if (lift == null) {
			lift = getLowerLift(userFloorNumber);
		}
		return lift;

	}

	private Lift getLowerLift(int userFloorNumber) {

		int minFloorNumber = 0;

		for (; userFloorNumber > minFloorNumber; userFloorNumber--) {

			List<Lift> liftList = liftExecuterMap.get(userFloorNumber);

			System.out.println("L>" + userFloorNumber + "=" + liftList);

			if (liftList != null) {
				return liftList.remove(liftList.size() - 1);
			}
		}

		return null;
	}

	private Lift getHigherLift(int userFloorNumber) {

		int maxFloorNumber = liftExecuterMap.size();

		for (; userFloorNumber < maxFloorNumber; userFloorNumber++) {

			List<Lift> liftList = liftExecuterMap.get(userFloorNumber);

			System.out.println("U>" + userFloorNumber + "=" + liftList);

			if (liftList != null) {
				return liftList.remove(liftList.size() - 1);
			}
		}

		return null;

	}

	public void show() {
		System.out.println(liftExecuterMap);
	}
}
