package java_oo.scjp;

interface User {
}

class UserImpl implements User {
}

class Person {

	Person() {
	}

	Person(int count) {
	}

	/**
	 * The method Super() is undefined for the type Super
	 * 
	 * @param initData
	 */
	/*
	 * Person(String initData) { Person(); }
	 */

	void start() {
		System.out.println("Super");
	}

	public String show() {
		return "";
	}

	public User getUser() {
		return null;
	}

}

class Student extends Person {

	Student() {
	}

	Student(int count) {
		super(count);
	}

	void start() {
		System.out.println("Sub");
	}

	/**
	 * The return type is incompatible with Super.show()
	 */
	/*
	 * public Object show() { return ""; }
	 */

	public UserImpl getUser() {
		return null;
	}
}

public class OverRiding {

	public static void main(String[] args) {

		Student sub = new Student();
		Person super_ = sub;
		super_.start();
	}

}
