package book.dao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import book.exception.DuplicateException;
import book.exception.NotFoundException;
import book.vo.Book;

public class SetBookShelf implements BookShelf {

	// 멤버 변수 선언부
	Set<Book> books;
	
	// 생성자 선언부
	public SetBookShelf() {
		this.books = new HashSet<Book>();
	}
	
	public SetBookShelf(Set<Book> books) {
		this.books = books;
	}
	
	// 메소드 선언부
	@Override
	public int insert(Book book) throws DuplicateException {
		/*
		 * books 안에 매개변수로 입력받은 
		 * Book 타입의 객체와 BookId가 같은 객체가 있는지 확인하고
		 * 객체가 없다면 add 메소드를 통해 books에 객체를 추가
		 */
		int insCnt = 0;
		
		if (!isContains(book)) {
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
		 * books 안에 매개변수로 입력받은 
		 * Book 타입의 객체와 BookId가 같은 객체가 있으면
		 * 해당 객체를 삭제하고 매개변수로 입력받은 객체를 추가
		 */
		int udtCnt = 0;
		
		if (isContains(book)) {
			books.remove(book);
			books.add(book);
			udtCnt = 1;
		} else {
			throw new NotFoundException("수정", book);
		}
		
		return udtCnt;
	}

	@Override
	public int delete(Book book) throws NotFoundException {
		/*
		 * books 안에 매개 변수로 입력받은 
		 * Book 타입의 객체와 BookId가 같은 객체가 있으면
		 * 해당 객체를 삭제
		 */
		int rmvCnt = 0;
		
		if (isContains(book)) {
			books.remove(book);
			rmvCnt = 1;
		} else {
			throw new NotFoundException("삭제", book);
		}
		
		return rmvCnt;
	}

	@Override
	public Book select(Book book) throws NotFoundException {
		/*
		 * books 안에 매개변수로 입력받은
		 * Book 타입의 객체와 bookId가 같은 객체가 있는지 찾고
		 * 그 객체를 foreach 반복문을 통해 찾아내어 리턴
		 */
		Book foundBook = null;
		
		if (isContains(book)) {
			for (Book found : books) {
				if (book.equals(found)) {
					foundBook = found;
					break;
				}
			}
		} else {
			throw new NotFoundException("삭제", book);
		}
		
		return foundBook;
	}

	@Override
	public List<Book> select() {
		/*
		 * 모든 도서 정보를 Set -> List로 바꾸어 리턴
		 */
		List<Book> allBooks = new ArrayList<Book>();
		
		for (Book book : books) {
			allBooks.add(book);
		}
		
		return allBooks;
	}
	
	@Override
	public List<Book> select(int low, int high) {
		
		List<Book> foundBooks = new ArrayList<Book>();

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
		 * books의 Set 크기를 리턴
		 */
		return books.size();
	}
	
	// 지원 메소드 선언부
	/**
	 * books 안에 매개변수로 입력받은 Book 타입의 객체가
	 * 존재하는지 확인
	 * 
	 * @param book
	 * @return 있으면 true, 없으면 false
	 */
	private boolean isContains(Book book) {
		return books.contains(book);
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


}
