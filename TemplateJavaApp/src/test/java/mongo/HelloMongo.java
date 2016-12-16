package mongo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HelloMongo {

	public static void main(String[] args) {
		List<Emp> listEmps = new ArrayList<Emp>();
		listEmps.add(new Emp(1,new Date()));
		listEmps.add(new Emp(2,new Date()));
		System.out.println(listEmps.contains(new Integer(1)));
	}	
}

class Emp{
	private Integer id;
	private Date loginTime;
	
	Emp(Integer id,Date loginTime){
		this.id=id;
		this.loginTime=loginTime;
	}
}
