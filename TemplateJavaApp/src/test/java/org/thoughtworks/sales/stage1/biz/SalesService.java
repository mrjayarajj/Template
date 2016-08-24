package org.thoughtworks.sales.stage1.biz;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.Map.Entry;

import org.thoughtworks.sales.stage1.vo.Item;
import org.thoughtworks.sales.stage1.vo.ItemType;
import org.thoughtworks.sales.stage1.vo.Result;

/**
 * Processing business logic Implementation.
 * @author Jayaraj Jaganathan
 */
public class SalesService {

	/**
	 * All product information.
	 */
	private Map<Integer, Item> itemMap = new TreeMap<Integer, Item>();

	/**
	 * default product are loaded and ready for sales.
	 */
	public SalesService() {		
		itemMap.put(1, new Item("Head First", ItemType.BOOK, 12.49d, false));
		itemMap.put(2, new Item("MJ Dangerous", ItemType.ENTERTAINMENT, 14.99d, false));
		itemMap.put(3, new Item("Nestle Kit Kat", ItemType.FOOD, 0.85d, false));
		itemMap.put(4, new Item("Cadbury's", ItemType.FOOD, 10d, true));
		itemMap.put(5, new Item("Halle Berry", ItemType.PERFUME, 47.5d, true));
		itemMap.put(6, new Item("Crystal Noir", ItemType.PERFUME, 27.99d, true));
		itemMap.put(7, new Item("Star Walker", ItemType.PERFUME, 18.99d, false));
		itemMap.put(8, new Item("Anacin Aspirin", ItemType.MEDICAL, 9.75d, false));
		itemMap.put(9, new Item("Stoberry ", ItemType.FOOD, 11.25d, true));
	}

	/**
	 * Show all the item information for sales.
	 */
	public void showAllItems() {
		Set<Map.Entry<Integer, Item>> s = itemMap.entrySet();

		System.out.println("ID\tName\t\tCost\tImport\tType");
		System.out.println("-----------------------------------------------------");
		//iterate and show the information
		for (Entry<Integer, Item> e : s) {
			System.out.println(e.getKey() + "\t" + e.getValue());
		}
	}

	/**
	 * Show the result for the selected product.
	 * @param ids selected product ids.
	 */
	public void showResult(int[] ids) {
		try{
			Result r = new Result();
	
			//ASSUMTION: sales tax %, taxable product may vary for every year
			
			//Get sales tax for the year 2010 from a factory class.
			SalesTaxEngine engine = SalesTaxFactory.getSalesTaxEngine(2010);
	
			for (int id : ids) {
				Item item = itemMap.get(id);
				//Calculate the sales tax and prices for each item.
				engine.calculate(item);
				//accumulate the item price's
				r.accumulate(item);
			}
	
			//show the result to user.
			r.showResult();
		}catch(Exception e){
			System.out.println("System encountered a exception..");			
		}
	}
}
