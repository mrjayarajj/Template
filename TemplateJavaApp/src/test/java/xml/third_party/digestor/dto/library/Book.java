package xml.third_party.digestor.dto.library;

public class Book {
	   private String author;
	   private String title;

	   public Book() {}

	   public void setAuthor( String rhs ) { author = rhs; }
	   public void setTitle(  String rhs ) { title  = rhs; }

	   public String toString() {
	      return "Book: Author='" + author + "' Title='" + title + "'";
	   }

	public String getAuthor() {
		return author;
	}

	public String getTitle() {
		return title;
	}
	}