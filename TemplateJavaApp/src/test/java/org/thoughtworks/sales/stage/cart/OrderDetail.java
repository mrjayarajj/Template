package org.thoughtworks.sales.stage.cart;

import java.math.BigDecimal;

import org.thoughtworks.sales.stage.dc.Item;
import org.thoughtworks.sales.stage.people.Passport;

public class OrderDetail {	

	private Item item = null;
	
	private Tax tax = new Tax();

	public OrderDetail(Item item) {
		this.item = item;
	}
	
	public void process(){
		tax.calculateTax(item);	
	}
	
	public void process(Passport passport){
		tax.calculateTax(item,passport);	
	}
	
	public Item getItem() {
		return item;
	}
	
	/**
	 * Get the price of the product.
	 * 
	 * @return price of the product.
	 */
	public BigDecimal getExtendedTaxPrice() {
		return tax.getTotalUnitTax().add(this.item.getPrice());
	}
	
	public BigDecimal getTotalUnitTax(){
		return tax.getTotalUnitTax();
	}
}
