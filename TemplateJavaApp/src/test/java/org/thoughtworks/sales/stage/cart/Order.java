package org.thoughtworks.sales.stage.cart;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.thoughtworks.sales.stage.dc.Item;
import org.thoughtworks.sales.stage.dc.ItemException;
import org.thoughtworks.sales.stage.people.Passport;

public class Order {

	private List<OrderDetail> orderDetails = null;

	public BigDecimal getTotalPrice() {

		BigDecimal totalPrice = new BigDecimal("0.0");

		if (orderDetails == null) {
			throw new ItemException("No Item in the basket");
		}

		for (OrderDetail orderDetail : orderDetails) {
			totalPrice = totalPrice.add(orderDetail.getExtendedTaxPrice());
		}
		return totalPrice;
	}

	public BigDecimal getTotalSalesTax() {
		
		BigDecimal totalSalesTax = new BigDecimal("0.0");

		if (orderDetails == null) {
			throw new ItemException("No Item in the basket");
		}

		for (OrderDetail orderDetail : orderDetails) {
			totalSalesTax = totalSalesTax.add(orderDetail.getTotalUnitTax());
		}
		
		return totalSalesTax;
	}

	public void addOrderDetail(Item item) {
		
		if (orderDetails == null) {
			orderDetails = new ArrayList<OrderDetail>();
		}
		orderDetails.add(new OrderDetail(item));
	}

	public void submitOrder() {
		for (OrderDetail orderDetail : orderDetails) {
			orderDetail.process();
		}
	}

	public void submitOrder(Passport passport) {
		for (OrderDetail orderDetail : orderDetails) {
			orderDetail.process(passport);
		}
	}

	public List<OrderDetail> getOrderDetails() {
		return orderDetails;
	}

}
