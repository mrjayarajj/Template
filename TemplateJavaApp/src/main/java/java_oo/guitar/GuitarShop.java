package java_oo.guitar;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GuitarShop {

	public static void main(String[] args) {

		String newName = new String("A").intern();
		String justName = "A";

		System.out.println(newName.hashCode());
		System.out.println(justName.hashCode());

		System.out.println(newName.hashCode() == justName.hashCode());
		System.out.println(newName == justName);

		Guitar g1 = new Guitar("1", 1, "E");
		Guitar g2 = new Guitar("1", 1, "E");
		
		System.out.println(g1.hashCode());
		System.out.println(g2.hashCode());

		System.out.println(g1.hashCode() == g2.hashCode());
		System.out.println(g1.equals(g2));
	}

}

class Guitar {

	private String serialNumber;

	private int price;

	private String type;

	public Guitar(String serialNumber, int price, String type) {
		this.serialNumber = serialNumber;
		this.price = price;
		this.type = type;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean equals(Object obj) {

		if (obj == null) {
			return false;
		}

		if (!(obj instanceof Guitar)) {
			return false;
		}

		if (((Guitar) obj).equals(this.getSerialNumber())) {
			return true;
		} else {
			return true;
		}
	}

	public int hashCode() {
		return this.getSerialNumber().hashCode();
	}

}

class Inventory {

	private List<Guitar> guitarList = new ArrayList<Guitar>();

	public void addGuitar(Guitar g) {
		guitarList.add(g);
	}

	public Guitar searchGuitar(Guitar searchGuitorItem) {

		for (Iterator<Guitar> i = guitarList.iterator(); i.hasNext();) {

			Guitar invGuitorItem = i.next();

			String invGuitorItemSerialNumber = invGuitorItem.getSerialNumber();
			String searchGuitorItemSerialNumber = searchGuitorItem.getSerialNumber();

			if (searchGuitorItemSerialNumber != null && searchGuitorItemSerialNumber.length() == 0 && searchGuitorItemSerialNumber.equals(invGuitorItemSerialNumber)) {
				continue;
			}

			Integer invGuitorItemPrice = invGuitorItem.getPrice();
			Integer searchGuitorItemPrice = searchGuitorItem.getPrice();

			if (searchGuitorItemPrice != null && searchGuitorItemPrice.intValue() > 0 && searchGuitorItemPrice.equals(invGuitorItemPrice)) {
				continue;
			}

			String invGuitorItemType = invGuitorItem.getSerialNumber();
			String searchGuitorItemType = searchGuitorItem.getSerialNumber();

			if (searchGuitorItemType != null && searchGuitorItemType.length() == 0 && searchGuitorItemType.equals(invGuitorItemType)) {
				continue;
			}

		}

		return null;
	}

}
