package java_oo.atm.learn;

//Used as the fictional card passed in to check 
//if it is valid

public class Card {

	private int pin;
	private int stripNumber;

	// Used to create a temp card to check for
	// a valid card strip number in a bank database

	Card(int stripNumber) {

		this.stripNumber = stripNumber;

	}

	// Used to create a temp card to check for
	// a valid card strip number and PIN in a
	// bank database

	Card(int pin, int stripNumber) {

		this.pin = pin;
		this.stripNumber = stripNumber;

	}

	public int getPIN() {
		return pin;
	}

	public int getStripNumber() {
		return stripNumber;
	}

	public void setPIN(int pin) {

		this.pin = pin;

	}

	public void setStripNumber(int stripNumber) {

		this.stripNumber = stripNumber;

	}

	public boolean equals(Object obj) {
		return this.stripNumber==((Card)obj).stripNumber;
	}

}
