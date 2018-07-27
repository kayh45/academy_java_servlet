package book.vo;

/**
 * 도서 정보를 관리하는 클래스
 * 
 * @author PC38219
 *
 */
public class Book {

	// 멤버변수 선언부
	private String bookId;
	private String title;
	private String author;
	private int price;
	private String isbn;
	private String publish;
	
	// 생성자 선언부
	public Book() {
		
	}

	public Book(String bookId) {
		this();
		this.bookId = bookId;
	}

	public Book(String bookId, String title) {
		this(bookId);
		this.title = title;
	}

	public Book(String bookId, String title, String author) {
		this(bookId, title);
		this.author = author;
	}

	public Book(String bookId, String title, String author, int price) {
		this(bookId, title, author);
		this.price = price;
	}

	public Book(String bookId, String title, String author, int price, String isbn) {
		this(bookId, title, author, price);
		this.isbn = isbn;
	}

	public Book(String bookId, String title, String author, int price, String isbn, String publish) {
		this(bookId, title, author, price, isbn);
		this.publish = publish;
	}

	// 수정, 접근자 선언부
	public String getBookId() {
		return bookId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getPublish() {
		return publish;
	}

	public void setPublish(String publish) {
		this.publish = publish;
	}

	// 메소드 선언부
	@Override
	public String toString() {
		return "Book [bookId=" + bookId + ", title=" + title + ", author=" + author + ", price=" + price + ", isbn="
				+ isbn + ", publish=" + publish + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bookId == null) ? 0 : bookId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		if (bookId == null) {
			if (other.bookId != null)
				return false;
		} else if (!bookId.equals(other.bookId))
			return false;
		return true;
	}
	
	
	
	
}
