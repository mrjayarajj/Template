package org.thoughtworks.sales.stage1.biz.sales_tax_2010;

import java.text.DecimalFormat;

import org.thoughtworks.sales.stage1.biz.SalesTaxEngine;
import org.thoughtworks.sales.stage1.vo.Item;
import org.thoughtworks.sales.stage1.vo.ItemType;

/**
 * Sales Tax calculation engine for the year 2010.
 * @author Jayaraj Jaganathan
 *
 */
public class SalesTax2010 implements SalesTaxEngine {
	
	/**
	 * Rounding factor.
	 */
	private final double ROUND_FACTOR = 0.05d;

	/**
	 * Calculate tax percentage,sales tax and price for each item.
	 * @param item item for sales.
	 */
	public void calculate(Item item) {
		calculateTaxPercentage(item);
		calculateSalesTax(item);
		calculatePrice(item);
	}

	/**
	 * Calculate total price of an item.
	 * @param item item for sales.
	 */
	private void calculatePrice(Item item) {
		double price = item.getCost() + item.getSalesTax();
		price = roundTwoDecimals(price);	
		item.setPrice(price);
	}

	/**
	 * Returns the value rounded up to the nearest 0.05.
	 * @param value value to be rounded
	 * @return double rounded up value
	 */
	private double roundOff(double value) {
		return Math.ceil(value / ROUND_FACTOR) * ROUND_FACTOR;
	}

	/**
	 * Calculate Sales Tax for an item.
	 * @param item  item for sales.
	 */
	private void calculateSalesTax(Item item) {
		double salesTax = (item.getCost() * item.getTaxPercentage()) / 100;
		salesTax = roundTwoDecimals(salesTax);
		salesTax = roundOff(salesTax);
		salesTax = roundTwoDecimals(salesTax);		
		item.setSalesTax(salesTax);
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

	/**
	 * Calucation tax percentage for items<BR>
	 * Sales tax is applicable at a rate of 10% on all goods, except books,<BR>
	 * food, and medical products that are exempt. Import duty is an additional<BR>
	 * sales tax applicable on all imported goods at a rate of 5%, with no<BR>
	 * exemptions.
	 * @param item item for sales.
	 */
	private void calculateTaxPercentage(Item item) {

		String type = item.getType();

		if (type.equals(ItemType.BOOK) || type.equals(ItemType.FOOD) || type.equals(ItemType.MEDICAL)) {
			item.setTaxPercentage(0);
		} else {
			item.setTaxPercentage(10);
		}

		if (item.isImported()) {
			item.setTaxPercentage(item.getTaxPercentage() + 5);
		}
	}
}
