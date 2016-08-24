package org.thoughtworks.sales.stage1.biz;

import org.thoughtworks.sales.stage1.vo.Item;

/**
 * Sales Tax engine.
 * @author Jayaraj Jaganathan
 */
public interface SalesTaxEngine {

	/**
	 * calculation process of an item.
	 * @param item item for sale.
	 */
	public abstract void calculate(Item item);
}
