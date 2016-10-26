package java_oo.pizza.biz;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import java_oo.pizza.CreditCardPaymentServiceImpl;
import java_oo.pizza.Order;
import java_oo.pizza.OrderItem;
import java_oo.pizza.PaymentService;

public class PizzaServiceImpl {

	private BlockingQueue<OrderItem> orderQueue;

	private BlockingQueue<Order> deliveryQueue;

	private List<PaymentService> payments;

	public PizzaServiceImpl() {

		orderQueue = new ArrayBlockingQueue<OrderItem>(10);

		deliveryQueue = new ArrayBlockingQueue<Order>(10);

		payments = new ArrayList<PaymentService>();
		payments.add(new CreditCardPaymentServiceImpl());

		ExecutorService pool = Executors.newFixedThreadPool(20);

		for (int i = 0; i < 10; i++) {
			// pool.submit(new Consumer(bq));
		}

	}

	public Order processPizzaOrder(Order order) {

		for (PaymentService p : payments) {

			if (order.getCreditCard() != null) {
				//p.processAmount(new BigDecimal(order.getAmountToCharge()));
			}

			// p.charge(new BigDecimal(2.2));
		}

		return null;
	}

}
