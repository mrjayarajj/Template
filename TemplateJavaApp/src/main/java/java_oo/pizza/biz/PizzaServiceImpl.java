package java_oo.pizza.biz;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import java_oo.pizza.CreditCardPayment;
import java_oo.pizza.Order;
import java_oo.pizza.OrderItem;
import java_oo.pizza.Payment;

public class PizzaServiceImpl {

	private BlockingQueue<OrderItem> orderQueue;

	private BlockingQueue<Order> deliveryQueue;

	private List<Payment> payments;

	public PizzaServiceImpl() {

		orderQueue = new ArrayBlockingQueue<OrderItem>(10);

		deliveryQueue = new ArrayBlockingQueue<Order>(10);

		payments = new ArrayList<Payment>();
		payments.add(new CreditCardPayment());

		ExecutorService pool = Executors.newFixedThreadPool(20);

		for (int i = 0; i < 10; i++) {
			// pool.submit(new Consumer(bq));
		}

	}

	public Order processPizzaOrder(Order order) {

		for (Payment p : payments) {
			// p.processPayment();
		}

		return null;
	}

}
