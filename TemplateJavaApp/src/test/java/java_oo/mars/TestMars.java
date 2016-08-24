package java_oo.mars;

import org.junit.Assert;
import org.junit.Test;

public class TestMars {

	@Test
	public void testRover() {
		Plateau marsPlateau = new Plateau(5, 5);

		Rover roverA = new Rover("A", 1, 2, Direction.N, marsPlateau);
		roverA.control('L');
		roverA.control('M');

		roverA.control('L');
		roverA.control('M');

		roverA.control('L');
		roverA.control('M');

		roverA.control('L');
		roverA.control('M');

		roverA.control('M');

		Assert.assertEquals(1,roverA.getX());
		Assert.assertEquals(3,roverA.getY());
		Assert.assertEquals(Direction.N,roverA.getDirection());

		Rover roverB = new Rover("B", 3, 3, Direction.E, marsPlateau);
		roverB.display('i');
		
		roverB.control('M');
		roverB.control('M');

		roverB.control('R');
		roverB.control('M');
		roverB.control('M');

		roverB.control('R');
		roverB.control('M');

		roverB.control('R');
		roverB.control('R');
		roverB.control('M');

		Assert.assertEquals(5,roverB.getX());
		Assert.assertEquals(1,roverB.getY());
		Assert.assertEquals(Direction.E,roverB.getDirection());

	}
	
	@Test
	public void testRoverHits() {
		Plateau marsPlateau = new Plateau(5, 5);
		Rover roverA = new Rover("A", 1, 2, Direction.N, marsPlateau);
		try{
			Rover roverB = new Rover("B", 1, 2, Direction.E, marsPlateau);
		}catch(RuntimeException e){
			Assert.assertEquals(Plateau.ALREADY_EXIST, e.getMessage());
		}
		try{
			Rover roverB = new Rover("B", 1, 1, Direction.N, marsPlateau);
			roverB.control('M');
		}catch(RuntimeException e){
			Assert.assertEquals(Plateau.ALREADY_EXIST, e.getMessage());
		}		
	}
	
	@Test
	public void testPlacingRoverOuOfPlateau() {
		Plateau marsPlateau = new Plateau(5, 5);
		try{
			Rover roverA = new Rover("A", 5, 6, Direction.E, marsPlateau);
		}catch(RuntimeException e){
			Assert.assertEquals(Plateau.INVALID_Y_POSITION, e.getMessage());
		}
		try{
			Rover roverA = new Rover("A", 0, -1, Direction.E, marsPlateau);
		}catch(RuntimeException e){
			Assert.assertEquals(Plateau.INVALID_Y_POSITION, e.getMessage());
		}
		try{
			Rover roverA = new Rover("A", 6, 5, Direction.E, marsPlateau);
		}catch(RuntimeException e){
			Assert.assertEquals(Plateau.INVALID_X_POSITION, e.getMessage());
		}
		try{
			Rover roverA = new Rover("A", -1, 0, Direction.E, marsPlateau);
		}catch(RuntimeException e){
			Assert.assertEquals(Plateau.INVALID_X_POSITION, e.getMessage());
		}
	}
}
