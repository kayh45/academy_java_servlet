package book.mapper;

import java.util.List;
import java.util.Map;

import book.vo.Book;

public interface BookMapper {
	
	/**
	 * 도서 정보를 추가
	 * 
	 * @param book : 도서 정보가 담긴 객체
	 * @return 삽입된 행 개수 리턴
	 */
	int insert(Book book);
	
	/**
	 * 도서 정보를 수정
	 * 
	 * @param book : 도서 정보가 담긴 객체
	 * @return 수정된 행 개수 리턴
	 */
	int update(Book book);
	
	/**
	 * 도서 정보를 삭제
	 * 
	 * 매개변수로 null이 들어오면
	 * 모든 데이터를 삭제
	 * 
	 * @param book : 도서 정보가 담긴 객체
	 * @return 삭제된 행 개수 리턴
	 */
	int delete(Book book);
	
	/**
	 * 도서 정보 1개를 조회
	 * 
	 * @param book : 도서 정보가 담긴 객체
	 * @return 조회된 도서 객체 리턴
	 */
	Book selectOne(Book book);
	
	/**
	 * 모든 도서 정보 목록을 조회
	 * 
	 * @return 모든 도서 정보를 도서 객체를 담는 List로 리턴
	 */
	List<Book> selectAll();

	/**
	 * 
	 * 
	 * @param priceMap
	 * @return
	 */
	List<Book> selectByPrice(Map<String, Integer> priceMap);

	/**
	 * 
	 * @param keyword
	 * @return
	 */
	List<Book> selectByKeyword(String keyword);
	
	/**
	 * 
	 * @return
	 */
	int totalCount();
	
	/**
	 * 도서 정보가 존재하는지 확인
	 * 
	 * @param book : 도서 ID가 들어있는 도서 객체
	 * @return 존재하면 그 도서 ID를 리턴하고, 
	 *         존재하지 않으면 null을 리턴  
	 */
	String isExists(Book book);

	
}
