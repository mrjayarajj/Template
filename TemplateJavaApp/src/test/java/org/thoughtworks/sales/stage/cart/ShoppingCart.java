package org.thoughtworks.sales.stage.cart;

import java.util.ArrayList;
import java.util.List;

import org.thoughtworks.sales.stage.dc.Item;
import org.thoughtworks.sales.stage.people.Passport;
import org.thoughtworks.sales.stage.shop.Location;
import org.thoughtworks.sales.stage.shop.Shop;

public class ShoppingCart {

	private Order order = null; 
	
	private Shop shop = null;

	public ShoppingCart(Shop shop) {
		this.shop = shop;
	}

	public void addItem(int itemID) {
		if(order==null){
			order = new Order();
		}		
		order.addOrderDetail(shop.getItem(itemID));
	}

	public Order checkout() {
		
		if(shop.isLocationAtAirport()){
			throw new IllegalAccessError("need user passport");
		}		
		order.submitOrder();

		return order;
	}
	
	public Order checkout(Passport passport) {
		
		Receipt r = new Receipt(order,passport);
		order.submitOrder(passport);

		return order;
	}

}
