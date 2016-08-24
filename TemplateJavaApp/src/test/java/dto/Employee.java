package dto;

import org.codehaus.jackson.annotate.JsonManagedReference;

public class Employee {

	private int id;

	private String name;

	private char gender;

	private boolean status;

	@JsonManagedReference
	private Department department;

	private Location location;

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String toString() {

		String base = this.id + "-" + this.name + "-" + this.status + "";

		if (this.location != null) {
			base = base + this.location.getCity();

		}

		if (this.department != null) {
			base = base + "<" + this.department.getId() + "-" + this.department.getName() + ">";
		}

		return base;

	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

}
