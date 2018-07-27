package book.exception;

import book.vo.Book;

public class NotFoundException extends Exception {

	public NotFoundException() {
		super("도서 정보가 존재하지 않습니다.");
	}
	
	public NotFoundException(String type, Book book) {
		super(String.format("%s:[%s] 도서 정보가 존재하지 않습니다.", type, book.getBookId()));
	}
	
}
