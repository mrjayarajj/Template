package java_oo.design_pattern.strategy.learn;

 enum AnimalType {
	DOG,BIRD
}
class Animal {

	private String name;
	private double height;
	private int weight;
	private String favFood;
	private double speed;
	private String sound;
	private AnimalType type;
	
	Animal(AnimalType type){
		this.type = type;
	}
	

	// Instead of using an interface in a traditional way
	// we use an instance variable that is a subclass
	// of the Flys interface.

	// Animal doesn't care what flyingType does, it just
	// knows the behavior is available to its subclasses

	// This is known as Composition : Instead of inheriting
	// an ability through inheritance the class is composed
	// with Objects with the right ability

	// Composition allows you to change the capabilities of
	// objects at run time!

	public Flys flyingType;

	public void setName(String newName) {
		name = newName;
	}

	public String getName() {
		return name;
	}

	public void setHeight(double newHeight) {
		height = newHeight;
	}

	public double getHeight() {
		return height;
	}

	public void setWeight(int newWeight) {
		if (newWeight > 0) {
			weight = newWeight;
		} else {
			System.out.println("Weight must be bigger than 0");
		}
	}

	public double getWeight() {
		return weight;
	}

	public void setFavFood(String newFavFood) {
		favFood = newFavFood;
	}

	public String getFavFood() {
		return favFood;
	}

	public void setSpeed(double newSpeed) {
		speed = newSpeed;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSound(String newSound) {
		sound = newSound;
	}

	public String getSound() {
		return sound;
	}

	/*
	 * BAD You don't want to add methods to the super class. You need to
	 * separate what is different between subclasses and the super class public
	 * void fly(){
	 * 
	 * System.out.println("I'm flying");
	 * 
	 * }
	 */

	// Animal pushes off the responsibility for flying to flyingType

	private CantFly cantFly = new CantFly();
	private ItFlys itFlys = new ItFlys();
	
	public String tryToFly() {

		if(flyingType!=null){
			return flyingType.fly();
		}
		
		if(type.equals(AnimalType.DOG)){
			return cantFly.fly();
		}
		else if(type.equals(AnimalType.BIRD)){
			return itFlys.fly();
		}
		
		return null;

	}

	// If you want to be able to change the flyingType dynamically
	// add the following method

	public void setFlyingAbility(Flys newFlyType) {

		flyingType = newFlyType;

	}

}


// The interface is implemented by many other
// subclasses that allow for many types of flying
// without effecting Animal, or Flys.

// Classes that implement new Flys interface
// subclasses can allow other classes to use
// that code eliminating code duplication

// I'm decoupling : encapsulating the concept that varies

interface Flys {

	String fly();

}

// Class used if the Animal can fly

class ItFlys implements Flys {

	public String fly() {

		return "Flying High";

	}

}

// Class used if the Animal can't fly

class CantFly implements Flys {

	public String fly() {

		return "I can't fly";

	}

}

class StrategyDesignPattern {

	public static void main(String[] args) {

		Animal sparky = new Animal(AnimalType.DOG);
		Animal tweety = new Animal(AnimalType.BIRD);

		System.out.println("Dog: " + sparky.tryToFly());

		System.out.println("Bird: " + tweety.tryToFly());

		// This allows dynamic changes for flyingType

		sparky.setFlyingAbility(new ItFlys());

		System.out.println("Dog: " + sparky.tryToFly());

	}

}