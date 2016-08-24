package cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;



/***
 * 
 * @author baliuka
 */
public class Trace implements MethodInterceptor {

	int ident = 1;
	static Trace callback = new Trace();

	/*** Creates a new instance of Trace */
	private Trace() {
	}

	public static Object newInstance(Class clazz) {
		try {
			Enhancer e = new Enhancer();
			e.setSuperclass(clazz);
			e.setCallback(callback);
			return e.create();
		} catch (Throwable e) {
			e.printStackTrace();
			throw new Error(e.getMessage());
		}

	}

	/***
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String[] args) {
		Demo d = (Demo) newInstance(Demo.class);
		d.emp();
	}

	public Object intercept(Object obj, java.lang.reflect.Method method, Object[] args, MethodProxy proxy) throws Throwable {
		printIdent(ident);
		System.out.println(method);
		for (int i = 0; i < args.length; i++) {
			printIdent(ident);
			System.out.print("arg" + (i + 1) + ": ");
			if (obj == args[i])
				System.out.println("this");
			else
				System.out.println(args[i]);
		}
		ident++;

		Object retValFromSuper = null;
		try {
			retValFromSuper = proxy.invokeSuper(obj, args);
			ident--;
		} catch (Throwable t) {
			ident--;
			printIdent(ident);
			System.out.println("throw " + t);
			System.out.println();
			throw t.fillInStackTrace();
		}

		printIdent(ident);
		System.out.print("return ");
		if (obj == retValFromSuper)
			System.out.println("this");
		else
			System.out.println(retValFromSuper);

		if (ident == 1)
			System.out.println();

		return retValFromSuper;
	}

	void printIdent(int ident) {

		while (--ident > 0) {
			System.out.print(".......");
		}
		System.out.print("  ");
	}

}

class Demo{
	public void emp(){
		System.out.println("emp");
		secure();
	}
	
	private void secure(){
		
	}
}
