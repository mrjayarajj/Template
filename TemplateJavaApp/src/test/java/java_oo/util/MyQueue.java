package java_oo.util;

import java.util.AbstractQueue;
import java.util.Iterator;
import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * First in
 * 
 * 
 * @author mrjayarajj
 *
 * @param <E>
 */
public class MyQueue<E> extends AbstractQueue<E> {

	private Stack<E> stackA = new Stack<E>();
	private Stack<E> stackB = new Stack<E>();

	public E poll() {
		stackB = reverse(stackA);

		if (!stackB.isEmpty()) {
			E e = stackB.pop();
			stackA = reverse(stackB);
			return e;
		} else {
			return null;
		}
	}

	public static void testQueue(Queue mq) {
		mq.add("a");
		mq.add("b");
		mq.add("c");

		System.out.println(mq.peek());
		mq.add("d");
		System.out.println(mq.remove());
		mq.add("e");
		mq.add("f");
		mq.add("g");
		System.out.println(mq.remove());
		System.out.println(mq.remove());
		mq.add("h");
		System.out.println(mq.remove());
		System.out.println(mq.remove());
		mq.add("i");
		System.out.println(mq.remove());
		System.out.println(mq.remove());
		System.out.println(mq.remove());
		System.out.println(mq.remove());

		
	}

	public static void main(String[] args) {

		testQueue(new MyQueue<String>());
		System.out.println("----");
		//testQueue(new ArrayBlockingQueue<String>(100));

	}

	public Stack reverse(Stack a) {

		Stack b = new Stack();

		while (!a.isEmpty()) {
			b.push(a.pop());
		}

		return b;

	}

	@Override
	public boolean offer(E e) {
		try {
			stackA.push(e);
		} catch (Exception ex) {
			return false;
		}

		return true;
	}

	@Override
	public E peek() {
		stackB = reverse(stackA);

		if (!stackB.isEmpty()) {
			E e = stackB.peek();
			stackA = reverse(stackB);
			return e;
		} else {
			return null;
		}
	}

	@Override
	public Iterator<E> iterator() {
		return stackA.iterator();
	}

	@Override
	public int size() {
		return stackA.size();
	}

}
