package org.thoughtworks.lift.vo;

public class Lift {

	private String name;

	public Lift(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public String toString() {
		return getName();
	}

}
