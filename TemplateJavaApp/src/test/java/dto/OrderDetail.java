package dto;

import java.util.Set;

public class OrderDetail {

	private int orderDetailId;

	private int orderId;

	private Set<OrderPayment> orderDetailPayments = null;

	public Set<OrderPayment> getOrderDetailPayments() {
		return orderDetailPayments;
	}

	public void setOrderDetailPayments(Set<OrderPayment> orderDetailPayments) {
		this.orderDetailPayments = orderDetailPayments;
	}

	public int getOrderDetailId() {
		return orderDetailId;
	}

	public void setOrderDetailId(int orderDetailId) {
		this.orderDetailId = orderDetailId;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String toString() {
		return "OD:>" + this.orderDetailId + "-" + this.orderDetailPayments;
	}

}
