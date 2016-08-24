package org.thoughtworks.sales.stage.cart;

import java.math.BigDecimal;

import org.thoughtworks.sales.stage.dc.Item;
import org.thoughtworks.sales.stage.people.Passport;

public class Tax {

	private static final BigDecimal ROUND_FACTOR = new BigDecimal("0.05");

	private static final BigDecimal SALES_TAX_PERCENTAGE = new BigDecimal("10");

	private static final BigDecimal IMPORT_TAX_PERCENTAGE = new BigDecimal("5");

	private static final BigDecimal IMPORT_DUTY_PERCENTAGE = new BigDecimal("3");

	private BigDecimal salesTax = new BigDecimal("0.0");

	private BigDecimal importTax = new BigDecimal("0.0");

	private BigDecimal importDutyTax = new BigDecimal("0.0");

	public BigDecimal getSalesTax() {
		return salesTax;
	}

	private void setSalesTax(BigDecimal salesTax) {
		this.salesTax = salesTax;
	}

	private void setImportTax(BigDecimal importTax) {
		this.importTax = importTax;
	}

	private void setImportDutyTax(BigDecimal importDutyTax) {
		this.importDutyTax = importDutyTax;
	}

	public BigDecimal getTotalUnitTax() {
		return this.salesTax.add(this.importTax).add(this.importDutyTax);
	}

	public void calculateTax(Item item) {
		if (item.isTaxable()) {
			BigDecimal salesTax = calculateTax(item, SALES_TAX_PERCENTAGE);
			setSalesTax(salesTax);
		}
		if (item.isImported()) {
			BigDecimal importTax = calculateTax(item, IMPORT_TAX_PERCENTAGE);
			setImportTax(importTax);
		}
	}

	public void calculateTax(Item item, Passport passport) {

		calculateTax(item);

		if (passport.isDomestic() && item.isImportDuty()) {
			BigDecimal importDutyTax = calculateTax(item, IMPORT_DUTY_PERCENTAGE);
			setImportDutyTax(importDutyTax);
		}
	}
	
	/**
	 * Calculate Sales Tax for an item.
	 * 
	 * @param item
	 *            item for sales.
	 */
	private BigDecimal calculateTax(Item item, BigDecimal taxPercentage) {
		BigDecimal tax = item.getPrice().multiply(taxPercentage).divide(new BigDecimal("100"));
		return roundOff(tax);
	}

	/**
	 * Returns the value rounded up to the nearest 0.05.
	 * 
	 * @param value
	 *            value to be rounded
	 * @return double rounded up value
	 */
	private BigDecimal roundOff(BigDecimal value) {
		value = value.divide(ROUND_FACTOR);
		value = new BigDecimal(Math.ceil(value.doubleValue()));
		value = value.multiply(ROUND_FACTOR);
		return value;
	}
}