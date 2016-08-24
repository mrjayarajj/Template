package java_oo.proxy;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

interface Foo {
	Object bar(Object obj);
}

class FooImpl implements Foo {

	public Object bar(Object obj) {
		System.out.println("Connection to server ...");
		return null;
	}

}

public class ProxyExample {

	public static void main(String[] args) {
		Foo foo = (Foo) DebugProxy.newInstance(new FooImpl());
		foo.bar(null);
	}
}
