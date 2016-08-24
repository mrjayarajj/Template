package apache.common;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;

class Sam{
	private String name = null;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}

class Fam{
	private String name = null;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
public class BeanUtilEx {

	public static void main(String[] args) throws IllegalAccessException, InvocationTargetException {
		Sam s = new Sam();
		s.setName("bean util");
		Fam f = new Fam();
		org.springframework.beans.BeanUtils.copyProperties(s,f);
		System.out.println(f.getName());
	}
}
