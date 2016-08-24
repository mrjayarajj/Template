package org.thoughtworks.lift.main;

import org.thoughtworks.lift.biz.LiftService;
import org.thoughtworks.lift.vo.Lift;
import org.thoughtworks.lift.vo.LiftCommand;

public class Main {

	public static void main(String[] args) {

		LiftService liftService = new LiftService(10);
		liftService.addLiftExecuter(9, new Lift("X"));
		liftService.addLiftExecuter(1, new Lift("Y"));
		liftService.addLiftExecuter(9, new Lift("Z"));
		
		liftService.show();

		Lift lift = liftService.getLift(3, LiftCommand.UP);
		System.out.println(lift.getName());

	}

}
