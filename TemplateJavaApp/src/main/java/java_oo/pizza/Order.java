package java_oo.pizza;

import java.util.Date;
import java.util.List;

public class Order {

	private Date orderDate;

	private List<OrderItem> orderItem;

	private CreditCard creditCard;

	private Status status;

	public CreditCard getCreditCard() {
		return this.creditCard;
	}
}
