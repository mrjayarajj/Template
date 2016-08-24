package xml.third_party.digestor.dto.order;

public class Party {
	private PartyName PartyName = null;
	private PostalAddress PostalAddress = null;

	public PartyName getPartyName() {
		return PartyName;
	}

	public void setPartyName(PartyName partyName) {
		PartyName = partyName;
	}

	public PostalAddress getPostalAddress() {
		return PostalAddress;
	}

	public void setPostalAddress(PostalAddress postalAddress) {
		PostalAddress = postalAddress;
	}
}
