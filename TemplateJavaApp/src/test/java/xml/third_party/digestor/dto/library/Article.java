package xml.third_party.digestor.dto.library;

public class Article {
	private String headline;
	private String page;

	public Article() {
	}

	public void setHeadline(String rhs) {
		headline = rhs;
	}

	public void setPage(String rhs) {
		page = rhs;
	}

	public String toString() {
		return "Article: Headline='" + headline + "' on page='" + page + "' ";
	}

	public String getHeadline() {
		return headline;
	}

	public String getPage() {
		return page;
	}
}