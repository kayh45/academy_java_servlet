package book.dao;

import java.util.ArrayList;
import java.util.List;

import book.exception.DuplicateException;
import book.exception.NotFoundException;
import book.vo.Book;

public class ListBookShelf implements BookShelf {
	
	// 멤버 변수 선언부
	List<Book> books;
	
	// 생성자 선언부
	public ListBookShelf() {
		this.books = new ArrayList<Book>();
	}
	
	public ListBookShelf(List<Book> books) {
		this.books = books;
	}
	
	// 메소드 선언부 
	@Override
	public int insert(Book book) throws DuplicateException {
		/*
		 * 매개변수로 입력받은 book 객체와 bookId가 같은 객체가
		 * books(List<Book>) 안에 있는지 확인하고
		 * 객체가 없을 때 add 메소드를 통해 
		 * 그 객체를 books에 넣음
		 */
		int insCnt = 0;
		int foundIdx = findBookIdx(book);
		
		if (foundIdx == -1) {
			books.add(book);
			insCnt = 1;
		} else {
			throw new DuplicateException("추가", book);
		}
		
		return insCnt;
	}

	@Override
	public int update(Book book) throws NotFoundException {
		/*
		 * 매개변수로 입력받은 book 객체와 bookId가 같은 객체가
		 * books(List<Book>) 안에 있는지, 
		 * 어떤 인덱스 번호를 가지고 있는지 확인하고
		 * findBookIdx() 메소드로 리턴받은 인덱스 번호를 얻고
		 * set 메소드를 통해 
		 * 리턴 받은 인덱스 번호에 해당하는 books 안의 객체를 
		 * 입력 받은 매개 변수의 객체로 수정
		 */
		int udtCnt = 0;
		int foundIdx = findBookIdx(book);
		
		if (foundIdx > -1) {
			books.set(foundIdx, book);
			udtCnt = 1;
		} else {
			throw new NotFoundException("수정", book);
		}
		
		return udtCnt;
	}

	@Override
	public int delete(Book book) throws NotFoundException {
		/*
		 * 매개변수로 입력받은 book 객체와 bookId가 같은 객체가
		 * books(List<Book>) 안에 있는지, 
		 * 어떤 인덱스 번호를 가지고 있는지 확인하고
		 * findBookIdx() 메소드로 리턴받은 인덱스 번호를 얻고
		 * remove 메소드를 통해 
		 * 리턴 받은 인덱스 번호에 해당하는 books 안의 객체를 삭제
		 */
		int dltCnt = 0;
		int foundIdx = findBookIdx(book);
		
		if (foundIdx > -1) {
			books.remove(foundIdx);
		} else {
			throw new NotFoundException("삭제", book);
		}
		
		return dltCnt;
	}

	@Override
	public Book select(Book book) throws NotFoundException {
		/*
		 * 매개변수로 입력받은 book 객체와 bookId가 같은 객체가
		 * books(List<Book>) 안에 있는지, 
		 * 어떤 인덱스 번호를 가지고 있는지 확인하고
		 * findBookIdx() 메소드로 리턴받은 인덱스 번호를 얻고
		 * get 메소드를 통해 
		 * 리턴 받은 인덱스 번호에 해당하는 books 안의 객체를 리턴
		 */
		int foundIdx = findBookIdx(book);
		Book foundBook = null;
		
		if (foundIdx > -1) {
			foundBook = books.get(foundIdx);
		} else {
			throw new NotFoundException("조회", book);
		}
		
		return foundBook;
	}

	@Override
	public List<Book> select() {
		/*
		 * 모든 도서 정보를 리턴 
		 * 멤버 변수인 books (List<Book>) 자체를 리턴
		 * 
		 */
		return books;
	}
	
	@Override
	public List<Book> select(int low, int high) {

		List<Book> foundBooks = new ArrayList<Book>();
		
//		for (int idx = 0; idx < books.size(); idx++) {
//			// 해당 인덱스의 도서 객체의 가격을 저장할 변수
//			int bookPrice = books.get(idx).getPrice();
//			// 가격이 low 이상, high 이하인 도서 객체를 
//			// foundBooks 리스트에 추가
//			if (bookPrice >= low && bookPrice <= high) {
//				foundBooks.add(books.get(idx));
//			}
//		}
// 아래 코드가 훨씬 간결
		
		for (Book book : books) {
			// 해당 인덱스의 도서 객체의 가격을 저장할 변수
			int bookPrice = book.getPrice();
			// 가격이 low 이상, high 이하인 도서 객체를 
			// foundBooks 리스트에 추가
			if (bookPrice >= low && bookPrice <= high) {
				foundBooks.add(book);
			}
		}
		
		return foundBooks;
	}
	
	@Override
	public List<Book> select(String keyword) {
		
		List<Book> foundBooks = new ArrayList<Book>();
		
		for (Book book : books) {
			// book 객체의 title이 keyword를 포함하고 있으면
			// 그 객체를 foundBooks 리스트에 추가
			if (book.getTitle().contains(keyword)) {
				foundBooks.add(book);
			}
		} 
		
		return foundBooks;
	}
	
	@Override
	public int totalCount() {
		/*
		 * 모든 도서의 개수를 리턴하기 위해
		 * books의 리스트 크기를 리턴
		 */
		return books.size();
	}

	@Override
	public int delete() {
		/**
		 * books 안의 모든 객체들을 삭제하고
		 * 삭제한 건수를 리턴
		 */
		int dltCnt = 0;
		
		for (Book book : books) {
			books.remove(book);
			dltCnt++;
		}
		
		return dltCnt;
	}

	// 지원 메소드 선언부
	/**
	 * books(List<Book>) 안에 
	 * 매개변수로 입력받은 객체가 가지고 있는 인덱스 번호를 리턴
	 * 
	 * @param book
	 * @return
	 */
	private int findBookIdx(Book book) {
		/* 배열의 인덱스가 될 수 없는 음수로 초기화 */
		int foundIdx = -1;
		
		for (int idx = 0; idx < books.size(); idx++) {
			if (book.equals(books.get(idx))) {
				foundIdx = idx;
				break;
			}
		}
		
		return foundIdx;
		
	}


}
