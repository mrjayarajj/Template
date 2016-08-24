package xml.third_party.digestor.dto.order;

public class Order {

	private String UBLVersionID = null;
	private String CustomizationID = null;
	private String ProfileID = null;
	private String ID = null;
	private String SalesOrderID = null;
	private String CopyIndicator = null;
	private String UUID = null;
	private String IssueDate = null;
	private String Note = null;
	
	private BuyerCustomerParty BuyerCustomerParty = null;

	public String getCustomizationID() {
		return CustomizationID;
	}

	public void setCustomizationID(String customizationID) {
		CustomizationID = customizationID;
	}

	public String getProfileID() {
		return ProfileID;
	}

	public void setProfileID(String profileID) {
		ProfileID = profileID;
	}

	public String getID() {
		return ID;
	}

	public void setID(String id) {
		ID = id;
	}

	public String getSalesOrderID() {
		return SalesOrderID;
	}

	public void setSalesOrderID(String salesOrderID) {
		SalesOrderID = salesOrderID;
	}

	public String getCopyIndicator() {
		return CopyIndicator;
	}

	public void setCopyIndicator(String copyIndicator) {
		CopyIndicator = copyIndicator;
	}

	public String getUUID() {
		return UUID;
	}

	public void setUUID(String uuid) {
		UUID = uuid;
	}

	public String getIssueDate() {
		return IssueDate;
	}

	public void setIssueDate(String issueDate) {
		IssueDate = issueDate;
	}

	public String getNote() {
		return Note;
	}

	public void setNote(String note) {
		Note = note;
	}

	public String getUBLVersionID() {
		return UBLVersionID;
	}

	public void setUBLVersionID(String versionID) {
		UBLVersionID = versionID;
	}

	public BuyerCustomerParty getBuyerCustomerParty() {
		return BuyerCustomerParty;
	}

	public void setBuyerCustomerParty(BuyerCustomerParty buyerCustomerParty) {
		BuyerCustomerParty = buyerCustomerParty;
	}

}
