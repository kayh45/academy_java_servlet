package book.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import book.exception.DuplicateException;
import book.exception.NotFoundException;
import book.vo.Book;

public class MapBookShelf implements BookShelf {

	// 멤버 변수 선언부
	Map<String, Book> books;
	
	// 생성자 선언부
	public MapBookShelf() {
		this.books = new HashMap<String, Book>();
	}
	
	public MapBookShelf(Map<String, Book> books) {
		this.books = books;
	}
	
	
	// 메소드 선언부
	@Override
	public int insert(Book book) throws DuplicateException {
		/*
		 * books 안에 매개변수로 입력받은 book 객체의
		 * bookId와 같은 Key가 없다면
		 * Key를 book 객체의 bookId로 
		 * 매핑되는 객체의 Value를 book 객체로 추가 
		 * 
		 */
		int insCnt = 0;
		
		if (!isContains(book)) {
			books.put(book.getBookId(), book);
			insCnt = 1;
		} else {
			throw new DuplicateException("추가", book);
		}
		
		return insCnt;
	}

	@Override
	public int update(Book book) throws NotFoundException {
		/*
		 * books 안에 매개변수로 입력받은 book 객체의
		 * bookId와 같은 Key가 있다면
		 * Key와 매핑되는 객체의 Value를
		 * book 객체로 변경
		 * 
		 */
		int udtCnt = 0;
		
		if (isContains(book)) {
			books.put(book.getBookId(), book);
			udtCnt = 1;
		} else {
			throw new NotFoundException("수정", book);
		}
		return udtCnt;
	}

	@Override
	public int delete(Book book) throws NotFoundException {
		/*
		 * books 안에 매개변수로 입력받은 book 객체의
		 * bookId와 같은 Key가 있다면
		 * Key와 매핑되는 객체의 Value를 삭제
		 * 
		 */
		int delCnt = 0;
		
		if (isContains(book)) {
			books.remove(book.getBookId());
			delCnt = 1;
		} else {
			throw new NotFoundException("삭제", book);
		}
		
		return delCnt;
	}

	@Override
	public Book select(Book book) throws NotFoundException {
		/*
		 * books 안에 매개변수로 입력받은 book 객체의
		 * bookId와 같은 Key가 있다면
		 * Key와 매핑되는 객체를 리턴
		 * 
		 */
		Book foundBook = null;
		
		if (isContains(book)) {
			foundBook = books.get(book.getBookId());
		} else {
			throw new NotFoundException("조회", book);
		}
		
		return foundBook;
	}

	@Override
	public List<Book> select() {
		/*
		 * Map 타입의 도서 정보 목록을 List 타입으로 바꿔서 리턴
		 */
		List<Book> allBooks = new ArrayList<Book>();
		
		Set<String> keySet = books.keySet();
		
		for (String key : keySet) {
			allBooks.add(books.get(key));
		}

		return allBooks;
	}

	@Override
	public List<Book> select(int low, int high) {
		
		List<Book> foundBooks = new ArrayList<Book>();
		Set<String> keySet = books.keySet();

		for (String key : keySet) {
			// 해당 인덱스의 도서 객체의 가격을 저장할 변수
			int bookPrice = books.get(key).getPrice();
			// 가격이 low 이상, high 이하인 도서 객체를 
			// foundBooks 리스트에 추가
			if (bookPrice >= low && bookPrice <= high) {
				foundBooks.add(books.get(key));
			}
		}
		
		return foundBooks;
	}
	
	@Override
	public List<Book> select(String keyword) {
		
		List<Book> foundBooks = new ArrayList<Book>();
		Set<String> keySet = books.keySet();
		
		for (String key : keySet) {
			// book 객체의 title이 keyword를 포함하고 있으면
			// 그 객체를 foundBooks 리스트에 추가
			if (books.get(key).getTitle().contains(keyword)) {
				foundBooks.add(books.get(key));
			}
		} 
		
		return foundBooks;
	}
	
	@Override
	public int totalCount() {
		/*
		 * 모든 도서의 개수를 리턴하기 위해
		 * books의 맵 크기를 리턴
		 */
		return books.size();
	}
	
	// 지원 변수 선언부
	/**
	 * books(Map<String, Book>) 안에 매개변수로 입력받은 
	 * book 객체의 bookId와 같은 key를 가지고 있는
	 * 객체가 있는지 검색  
	 * 
	 * @param book
	 * @return 있다면 true, 없으면 false
	 */
	private boolean isContains(Book book) {
		return books.containsKey(book.getBookId());
	}
	
	@Override
	public int delete() {
		/**
		 * books 안의 모든 객체들을 삭제하고
		 * 삭제한 건수를 리턴
		 */
		int dltCnt = 0;
		Set<String> keySet = books.keySet();
		
		for (String key : keySet) {
			books.remove(key);
			dltCnt++;
		}
		
		return dltCnt;
	}


}
