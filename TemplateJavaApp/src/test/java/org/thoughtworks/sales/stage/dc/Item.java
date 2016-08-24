package org.thoughtworks.sales.stage.dc;

import java.math.BigDecimal;

/**
 * This class hold the data related to item.
 * 
 * @author Jayaraj Jaganathan
 */
public class Item {
	
	/**
	 * Name of the product.
	 */
	private String name = null;

	/**
	 * Price of the product.
	 */
	private BigDecimal price = null;

	/**
	 * Product type.
	 */
	private ItemType itemType = null;

	/**
	 * Imported product.
	 */
	private boolean imported = false;
	
	private boolean taxable = false;
	
	private boolean importDuty = false;

	public boolean isImportDuty() {
		return importDuty;
	}

	public void setImportDuty(boolean importDuty) {
		this.importDuty = importDuty;
	}

	public boolean isTaxable() {
		return taxable;
	}

	public void setTaxable(boolean taxable) {
		this.taxable = taxable;
	}

	/**
	 * Mandatory information of the product.
	 * 
	 * @param name
	 *            name of the product.
	 * @param type
	 *            type of the product.
	 * @param price
	 *            price of the product.
	 * @param imported
	 *            is this product imported.
	 */
	public Item(String name, ItemType itemType, BigDecimal price, boolean imported) {
		this.name = name;
		this.itemType = itemType;
		this.price = price;
		this.imported = imported;
	}	

	/**
	 * Get the name of the product.
	 * 
	 * @return name of the product.
	 */
	public String getName() {
		return name;
	}	

	/**
	 * Get the price of the product.
	 * 
	 * @return price of the product.
	 */
	public BigDecimal getPrice() {
		return price;
	}	

	/**
	 * Get the product type.
	 * 
	 * @return product type.
	 */
	public ItemType getItemType() {
		return itemType;
	}
	
	/**
	 * Get the Imported product.
	 * 
	 * @return true if it is an imported product.
	 */
	public boolean isImported() {
		return imported;
	}	

	/**
	 * Show the item information.
	 */
	public String toString() {
		return this.name + "\t" + this.price + "\t" + this.imported + "\t" + this.itemType;
	}
}
