package java_oo.coingame.learn;

public class User {

	private String name = null;
	
	public User(String name){
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public String toString() {
		return this.name;
	}
	
	public boolean equals(Object obj) {
		return this.name.equals(((User)obj).getName());
	}
	
}
