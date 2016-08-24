package org.thoughtworks.sales.stage.shop;

import java.util.ArrayList;
import java.util.List;

import org.thoughtworks.sales.stage.dc.Inventory;
import org.thoughtworks.sales.stage.dc.Item;
import org.thoughtworks.sales.stage.dc.ItemType;

public class Shop {

	private Inventory inventory = null;

	private Location location = null;

	private List<ItemType> taxableItemList = null;

	private List<ItemType> nonImportDutyItemList = null;

	public Shop(Location location) {
		inventory = new Inventory();
		this.location = location;
	}

	public void addTaxableItem(ItemType itemType) {
		taxableItemList.add(itemType);
	}

	public void addNonImportDutyItem(ItemType itemType) {
		if(nonImportDutyItemList==null){
			nonImportDutyItemList = new ArrayList<ItemType>();
		}
		nonImportDutyItemList.add(itemType);
	}

	public List<ItemType> getTaxableItems() {
		return taxableItemList;
	}

	public List<ItemType> getNonImportDutyItems() {
		return nonImportDutyItemList;
	}

	public Location getLocation() {
		return this.location;
	}

	public Item getItem(int itemID) {
		Item item = inventory.getItem(itemID);

		if (getTaxableItems() != null) {
			for (ItemType taxableItemType : getTaxableItems()) {
				ItemType itemType = item.getItemType();
				if (itemType == taxableItemType) {
					item.setTaxable(true);
					return item;
				}
			}
		}

		if (getNonImportDutyItems() != null) {
			for (ItemType nonImportDutyItemType : getNonImportDutyItems()) {
				ItemType itemType = item.getItemType();
				if (itemType != nonImportDutyItemType) {
					item.setImportDuty(true);
					return item;
				}
			}
		}

		return item;
	}
	
	public boolean isLocationAtAirport(){
		return this.location==Location.AIRPORT ? true : false ;
	}

}
