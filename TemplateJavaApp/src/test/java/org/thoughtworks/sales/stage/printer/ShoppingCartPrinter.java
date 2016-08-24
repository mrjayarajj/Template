package org.thoughtworks.sales.stage.printer;

import java.io.OutputStream;
import java.io.PrintStream;

import org.thoughtworks.sales.stage.cart.Order;
import org.thoughtworks.sales.stage.cart.OrderDetail;
import org.thoughtworks.sales.stage.people.Passport;

public class ShoppingCartPrinter {

	private PrintStream out = null;

	public ShoppingCartPrinter(OutputStream out) {
		this.out = new PrintStream(out);
	}

	public void print(Order o,Passport passport) {
		
		if(passport!=null && passport.isDomestic()==false){
			out.println("Passport Number : "+passport.getPassportNumber());
			out.println();
		}

		for (OrderDetail orderDetail : o.getOrderDetails()) {
			out.println(orderDetail.getItem()+"\t"+orderDetail.getExtendedTaxPrice()+"\t"+orderDetail.getTotalUnitTax());
		}
		out.println();
		out.println("Total Sales Tax :" + o.getTotalSalesTax());
		out.println("Total Price :" + o.getTotalPrice());
	}
}
