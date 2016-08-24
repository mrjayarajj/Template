package org.thoughtworks.sales.stage1.vo;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;


/**
 * Result data are stored in this class.
 * @author Jayaraj Jaganathan
 */
public class Result {
	
	/**
	 * Total sales tax of the product selected.
	 */
	private double totalSalesTax = 0.0d;

	/**
	 * Total price of the product selected.
	 */
	private double totalPrice = 0.0d;

	/**
	 * Selected item list.
	 */
	private List<Item> selectedItemList = new ArrayList<Item>();

	/**
	 * accumulate the total price and sales tax for the product selected.
	 * @param item item for sales.
	 */
	public void accumulate(Item item) {	
		totalSalesTax = totalSalesTax + item.getSalesTax();		
		totalPrice = totalPrice + item.getPrice();		
		selectedItemList.add(item);
	}

	/**
	 * Show the result.
	 */
	public void showResult() {
		
		System.out.println("Selected Items are:");
		
		System.out.println("-----------------------------------------------");
		System.out.println("Name\t\tCost\tImport\tType");
		System.out.println("------------------------------------------------");

		for(Item item : selectedItemList){
			System.out.println(item);
		}
		System.out.println();
		System.out.println("Sales Taxes : "+getTotalSalesTax());
		System.out.println("Total : "+getTotalPrice());
	}

	/**
	 * Get total sales tax of the product selected.
	 */
	public double getTotalSalesTax() {
		return roundTwoDecimals(totalSalesTax);
	}	

	/**
	 * Get total price of the product selected.
	 */
	public double getTotalPrice() {
		return roundTwoDecimals(totalPrice);
	}

	/**
	 * Get selected item list.
	 */
	public List<Item> getSelectedItemList() {
		return selectedItemList;
	}
	
	/**
	 * Rounding to two decimal places.
	 * @param value value to be rounded to two decimal places.
	 * @return two decimal place value.
	 */
	private double roundTwoDecimals(double value) {
		DecimalFormat twoDForm = new DecimalFormat("#.##");
		return Double.valueOf(twoDForm.format(value));
	}
	
}
