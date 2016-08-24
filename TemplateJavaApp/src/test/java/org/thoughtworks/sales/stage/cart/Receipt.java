package org.thoughtworks.sales.stage.cart;

import org.thoughtworks.sales.stage.people.Passport;

public class Receipt {
	
	private Passport passport = null;
	
	private Order order = null; 
	
	public Receipt(Order order,Passport passport){
		this.order = order;
		this.passport = passport;
	}

	public Receipt(Order order) {
		this.order = order;
	}

	public Passport getPassport() {
		return passport;
	}
}