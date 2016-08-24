package java_oo.mars;

import java.util.HashMap;
import java.util.Map;

public class Plateau {

	private final int x;

	private final int y;

	private Map<String, Rover> rovers = new HashMap<String, Rover>();

	public Plateau(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public static final String ALREADY_EXIST = "Already Rover exist at this position , please choose any other co-ordinates";
	
	public static final String INVALID_X_POSITION = "Rover cannot be placed inside Plateau , invalid x";
	public static final String INVALID_Y_POSITION = "Rover cannot be placed inside Plateau , invalid y";
	
	public void validatePosition(int x,int y){
		
		if ((0 <= x && x <= this.x) == false) {
			throw new RuntimeException(INVALID_X_POSITION);
		}
		if ((0 <= y && y <= this.y) == false) {
			throw new RuntimeException(INVALID_Y_POSITION);
		}
		if (getRover(x, y) != null) {
			throw new RuntimeException(ALREADY_EXIST);
		}
	}

	public void intiRover(Rover rover) {
		validatePosition(rover.getX(),rover.getY());
		this.rovers.put(rover.getName(), rover);
	}

	private Rover getRover(int x, int y) {

		for (Map.Entry<String, Rover> roverEntry : rovers.entrySet()) {
			if(roverEntry.getValue().getX()==x && roverEntry.getValue().getY()==y ){
				return roverEntry.getValue();
			}
		}
		return null;
	}

}
