package org.thoughtworks.sales.stage1.biz;

import org.thoughtworks.sales.stage1.biz.sales_tax_2010.SalesTax2010;

/**
 * Factory class which decide right engine for the year.
 * @author Jayaraj Jaganathan
 */
public class SalesTaxFactory {

	/**
	 * Factory method which return corresponding engine based on year.
	 * @param year year information.
	 * @return engine type.
	 */
	public static SalesTaxEngine getSalesTaxEngine(int year) {
		if (year == 2010) {
			return new SalesTax2010();
		}
		return null;
	}

}
