package org.thoughtworks.sales.stage.cart;

import org.thoughtworks.sales.stage.dc.ItemType;
import org.thoughtworks.sales.stage.people.Passport;
import org.thoughtworks.sales.stage.printer.ShoppingCartPrinter;
import org.thoughtworks.sales.stage.shop.Location;
import org.thoughtworks.sales.stage.shop.Shop;

public class CheckoutTester {
	
	public static void main(String[] args) {
		
		Shop shop = new Shop(Location.AIRPORT);
		shop.addNonImportDutyItem(ItemType.FOOD);
		
		ShoppingCart shoppingCart = new ShoppingCart(shop);
		/*
		shoppingCart.addItem(1);
		shoppingCart.addItem(2);
		shoppingCart.addItem(3);
		Receipt r = shoppingCart.checkout(new Passport("TW638493748937"));
		*/
		
		shoppingCart.addItem(4);
		shoppingCart.addItem(5);		
		Passport p =new Passport("TWG8392592364");		
		Order o = shoppingCart.checkout(p);					
		new  ShoppingCartPrinter(System.out).print(o,p);		
		
		System.out.println();
		
		Passport p2 =new Passport("TW128392592364");		
		Order o2 = shoppingCart.checkout(p2);					
		new  ShoppingCartPrinter(System.out).print(o2,p2);
		
		/*
		shoppingCart.addItem(6);
		shoppingCart.addItem(7);
		shoppingCart.addItem(8);
		shoppingCart.addItem(9);		
		Passport p = new Passport("83947388GGOFTW");
		*/
		
		
		
	}

	
}
