package org.thoughtworks.sales.stage.dc;

import java.math.BigDecimal;
import java.util.Map;
import java.util.TreeMap;


public class Inventory {

	/**
	 * All product information.
	 */
	private Map<Integer, Item> items = new TreeMap<Integer, Item>();

	/**
	 * default product are loaded and ready for sales.
	 */
	public Inventory() {
		items.put(1, new Item("Head First", ItemType.BOOK, new BigDecimal("12.49"), false));
		items.put(2, new Item("MJ Dangerous", ItemType.MOVIE, new BigDecimal("14.99"), false));
		items.put(3, new Item("Nestle Kit Kat", ItemType.FOOD, new BigDecimal("0.85"), false));
		items.put(4, new Item("Cadbury's", ItemType.FOOD, new BigDecimal("10"), true));
		items.put(5, new Item("Halle Berry", ItemType.PERFUME, new BigDecimal("47.5"), true));
		items.put(6, new Item("Crystal Noir", ItemType.PERFUME, new BigDecimal("27.99"), true));
		items.put(7, new Item("Star Walker", ItemType.PERFUME, new BigDecimal("18.99"), false));
		items.put(8, new Item("Anacin Aspirin", ItemType.MEDICAL, new BigDecimal("9.75"), false));
		items.put(9, new Item("Stoberry ", ItemType.FOOD, new BigDecimal("11.25"), true));
	}
	
	public Item getItem(int itemID){
		return items.get(itemID);
	}
}
