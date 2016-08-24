package xml.third_party.digestor.dto.order;

public class BuyerCustomerParty {
	
	private String CustomerAssignedAccountID = null;
	private String SupplierAssignedAccountID = null;
	
	private Party Party = null;

	public String getCustomerAssignedAccountID() {
		return CustomerAssignedAccountID;
	}

	public void setCustomerAssignedAccountID(String customerAssignedAccountID) {
		CustomerAssignedAccountID = customerAssignedAccountID;
	}

	public String getSupplierAssignedAccountID() {
		return SupplierAssignedAccountID;
	}

	public void setSupplierAssignedAccountID(String supplierAssignedAccountID) {
		SupplierAssignedAccountID = supplierAssignedAccountID;
	}

	public Party getParty() {
		return Party;
	}

	public void setParty(Party party) {
		Party = party;
	}
}
