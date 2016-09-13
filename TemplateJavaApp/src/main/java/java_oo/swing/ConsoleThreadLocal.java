package java_oo.swing;

public class ConsoleThreadLocal {

	public static final ThreadLocal<Console> userThreadLocal = new ThreadLocal<Console>();

	public static void set(Console user) {
		userThreadLocal.set(user);
	}

	public static void unset() {
		userThreadLocal.remove();
	}

	public static Console get() {
		return userThreadLocal.get();
	}
}
