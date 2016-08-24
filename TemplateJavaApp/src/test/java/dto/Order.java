package dto;

import java.util.Set;

public class Order {

	private Integer orderId = null;

	private Set<Payment> orderPayments = null;

	private Set<OrderDetail> orderDetails = null;

	public Set<OrderDetail> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(Set<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}

	public void setOrderPayments(Set<Payment> orderPayments) {
		this.orderPayments = orderPayments;
	}

	public Set<Payment> getOrderPayments() {
		return orderPayments;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public String toString() {
		String s = "O>" + this.orderId + " ";
		if (this.orderPayments != null) {
			s = s + this.orderPayments + " ";
		}
		if (this.orderDetails != null) {
			s = s + this.orderDetails;
		}
		return s;
	}
}
