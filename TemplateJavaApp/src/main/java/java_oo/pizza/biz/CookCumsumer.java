package java_oo.pizza.biz;

import java.util.concurrent.BlockingQueue;

import java_oo.pizza.Order;
import java_oo.pizza.OrderItem;

public class CookCumsumer implements Runnable {

	private BlockingQueue<OrderItem> orderQueue;

	private BlockingQueue<Order> deliveryQueue;

	public CookCumsumer() {

	}

	public void run() {

	}

}
