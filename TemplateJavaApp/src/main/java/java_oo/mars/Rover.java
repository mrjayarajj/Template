package java_oo.mars;

public class Rover {

	private String name;

	private int x;

	private int y;

	private Direction direction;

	private Plateau plateau;

	public Rover(String name, int x, int y, Direction direction, Plateau plateau) {
		this.name = name;
		this.x = x;
		this.y = y;
		this.direction = direction;
		this.plateau = plateau;
		this.plateau.intiRover(this);
	}

	public String getName() {
		return name;
	}

	public Direction getDirection() {
		return direction;
	}

	public Plateau getPlateau() {
		return plateau;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void control(char command) {
		if (command == 'M') {
			movePosition();
		} else if (command == 'L') {
			moveLeftDirection();
		} else if (command == 'R') {
			moveRightDirection();
		}
		display(command);
	}

	public void display(char command) {
		System.out.println("---------------------------------------------");
		System.out.println("COMMAND=" + command + "");
		System.out.println("x=" + this.x + " y=" + this.y + " D=" + this.direction);
		System.out.println("---------------------------------------------");
	}

	private void moveRightDirection() {
		if (getDirection() == Direction.N) {
			this.direction = Direction.E;
		} else if (getDirection() == Direction.S) {
			this.direction = Direction.W;
		} else if (getDirection() == Direction.E) {
			this.direction = Direction.S;
		} else if (getDirection() == Direction.W) {
			this.direction = Direction.N;
		}
	}

	private void moveLeftDirection() {
		if (getDirection() == Direction.N) {
			this.direction = Direction.W;
		} else if (getDirection() == Direction.S) {
			this.direction = Direction.E;
		} else if (getDirection() == Direction.E) {
			this.direction = Direction.N;
		} else if (getDirection() == Direction.W) {
			this.direction = Direction.S;
		}
	}

	private void movePosition() {
		if (getDirection() == Direction.N) {
			getPlateau().validatePosition(this.x, this.y + 1);
			this.y++;
		} else if (getDirection() == Direction.S) {
			getPlateau().validatePosition(this.x, this.y - 1);
			this.y--;
		} else if (getDirection() == Direction.E) {
			getPlateau().validatePosition(this.x + 1, this.y);
			this.x++;
		} else if (getDirection() == Direction.W) {
			getPlateau().validatePosition(this.x - 1, this.y);
			this.x--;
		}
	}

}
