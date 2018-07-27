package book.dao;

import java.util.List;

import book.exception.DuplicateException;
import book.exception.NotFoundException;
import book.vo.Book;

public interface BookShelf {

	/**
	 * 도서 정보를 입력
	 * 
	 * 입력 성공 시 1 을 리턴하고
	 * 입력 실패 시 0 을 리턴
	 * 
	 * @param book
	 * @return 성공시 1, 실패시 0
	 */
	public int insert(Book book) throws DuplicateException ;
	
	/**
	 * 도서 정보를 수정
	 * 
	 * 수정 성공 시 1 을 리턴하고
	 * 수정 실패 시 0 을 리턴
	 * 
	 * @param book
	 * @return 성공시 1, 실패시 0
	 */
	public int update(Book book) throws NotFoundException;
	
	/**
	 * 도서 정보를 삭제
	 * 
	 * 삭제 성공 시 1 을 리턴하고
	 * 삭제 실패 시 0 을 리턴
	 * 
	 * @param book
	 * @return 성공시 1, 실패시 0
	 */
	public int delete(Book book) throws NotFoundException;
	
	/**
	 * 도서 정보 1개를 조회
	 * 
	 * 
	 * @param book
	 * @return 입력받은 객체와 같은 BookId를 가진 객체 리턴
	 */
	public Book select(Book book) throws NotFoundException;
	
	/**
	 * 모든 도서들의 정보를 조회
	 * 
	 * @return 모든 도서들의 정보를 Book 타입의 List로 리턴
	 */
	public List<Book> select();
	
	/**
	 * low ~ hight 사이의 가격대 책을 검색
	 * 
	 * @param low
	 * @param high
	 * 
	 * @return 검색 결과를 목록으로 리턴
	 */
	public List<Book> select(int low, int high);
	
	/**
	 * 책 title에 keyword가 들어가는 책을 검색
	 * 
	 * @param keyword
	 * @return 검색 결과를 목록으로 리턴
	 */
	public List<Book> select(String keyword);
	
	/**
	 * 전체 등록된 책의 개수를 구하여 리턴
	 * 
	 * @return 전체 등록된 책의 개수를 정수로 리턴
	 */
	public int totalCount();
	
	/**
	 * 등록된 전체 책 정보를 삭제
	 * 
	 * @return 삭제된 건수를 리턴
	 */
	public int delete();
	
	
}
