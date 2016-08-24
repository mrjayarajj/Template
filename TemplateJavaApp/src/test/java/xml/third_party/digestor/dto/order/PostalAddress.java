package xml.third_party.digestor.dto.order;

public class PostalAddress {
	private String StreetName = null;
	private String BuildingName = null;
	private String BuildingNumber = null;
	private String CityName = null;
	private String PostalZone = null;
	private String CountrySubentity = null;
	public String getStreetName() {
		return StreetName;
	}
	public void setStreetName(String streetName) {
		StreetName = streetName;
	}
	public String getBuildingName() {
		return BuildingName;
	}
	public void setBuildingName(String buildingName) {
		BuildingName = buildingName;
	}
	public String getBuildingNumber() {
		return BuildingNumber;
	}
	public void setBuildingNumber(String buildingNumber) {
		BuildingNumber = buildingNumber;
	}
	public String getCityName() {
		return CityName;
	}
	public void setCityName(String cityName) {
		CityName = cityName;
	}
	public String getPostalZone() {
		return PostalZone;
	}
	public void setPostalZone(String postalZone) {
		PostalZone = postalZone;
	}
	public String getCountrySubentity() {
		return CountrySubentity;
	}
	public void setCountrySubentity(String countrySubentity) {
		CountrySubentity = countrySubentity;
	}
}
