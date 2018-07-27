package book.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import book.exception.DuplicateException;
import book.exception.NotFoundException;
import book.mapper.BookMapper;
import book.mybatis.MybatisClient;
import book.vo.Book;

public class MybatisBookShelf implements BookShelf {

private SqlSessionFactory factory;
	
	public MybatisBookShelf() {
		factory = MybatisClient.getFactory();
	}
	
	@Override
	public int insert(Book book) throws DuplicateException {

		if (isExists(book)) {
			throw new DuplicateException("추가", book);
		}
		
		int insCnt = 0;
		
		// SqlSession
		SqlSession session = factory.openSession(true);
		
		// Mapper 
		BookMapper mapper;
		mapper = session.getMapper(BookMapper.class);

		try {
			// mapper 객체에 메소드 호출로 쿼리 실행
			insCnt = mapper.insert(book);
			
		} finally {
			// 자원 해제
			if (session != null) {
				session.close();
			}
		}
		
		return insCnt;
	}

	@Override
	public int update(Book book) throws NotFoundException {
		
		if (!isExists(book)) {
			throw new NotFoundException("수정", book);
		}
		
		int udtCnt = 0;
		
		// SqlSession
		SqlSession session = factory.openSession(true);
		
		// Mapper 
		BookMapper mapper;
		mapper = session.getMapper(BookMapper.class);
		
		try {
			// mapper 객체에 메소드 호출로 쿼리 실행
			udtCnt = mapper.update(book);
			
		} finally {
			// 자원 해제
			if (session != null) {
				session.close();
			}
		}
		
		return udtCnt;
	}

	@Override
	public int delete(Book book) throws NotFoundException {

		if (book != null && !isExists(book)) {
			throw new NotFoundException("삭제", book);
		}
		
		int delCnt = 0;
		// Session
		SqlSession session = factory.openSession(true);
		
		// Mapper
		BookMapper mapper = session.getMapper(BookMapper.class);
		
		try {
			delCnt = mapper.delete(book);
		
		} finally {
			// 자원해제
			if (session != null) {
				session.close();
			}
		}
		
		return delCnt;
	}

	@Override
	public Book select(Book book) throws NotFoundException {
		
		if (book != null && !isExists(book)) {
			throw new NotFoundException("조회", book);
		}
		
		Book found = null;
		// Session
		SqlSession session = factory.openSession();
		
		// Mapper
		BookMapper mapper = session.getMapper(BookMapper.class);
		
		try {
			found = mapper.selectOne(book);
		
		} finally {
			// 자원해제
			if (session != null) {
				session.close();
			}
		}
		
		return found;
		
	}

	@Override
	public List<Book> select() {

		List<Book> allBooks = new ArrayList<Book>();
		
		// Session
		SqlSession session = factory.openSession();
		
		// Mapper
		BookMapper mapper = session.getMapper(BookMapper.class);
		
		try {
			allBooks = mapper.selectAll();
		
		} finally {
			// 자원해제
			if (session != null) {
				session.close();
			}
		}
		
		return allBooks;
	}

	@Override
	public List<Book> select(int low, int high) {
		List<Book> foundBooks = new ArrayList<Book>();
		
		// Session
		SqlSession session = factory.openSession();
		
		// Mapper
		BookMapper mapper = session.getMapper(BookMapper.class);
		
		try {
			Map<String, Integer> priceMap = new HashMap<>();
			
			priceMap.put("high", high);
			priceMap.put("low", low);
			
			foundBooks = mapper.selectByPrice(priceMap);
		
		} finally {
			// 자원해제
			if (session != null) {
				session.close();
			}
		}
		
		return foundBooks;
	}

	@Override
	public List<Book> select(String keyword) {
		List<Book> foundBooks = new ArrayList<Book>();
		
		// Session
		SqlSession session = factory.openSession();
		
		// Mapper
		BookMapper mapper = session.getMapper(BookMapper.class);
		
		try {
			
			foundBooks = mapper.selectByKeyword(keyword);
		
		} finally {
			// 자원해제
			if (session != null) {
				session.close();
			}
		}
		
		return foundBooks;
	}


	@Override
	public int totalCount() {
		
		// Session
		SqlSession session = factory.openSession();
		
		// Mapper
		BookMapper mapper = session.getMapper(BookMapper.class);
		
		int counted = 0;
		
		try {
			counted = mapper.totalCount();
		
		} finally {
			// 자원해제
			if (session != null) {
				session.close();
			}
		}
		
		return counted;
	}
	

	@Override
	public int delete() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	// 지원 메소드 선언부
	/**
	 * DB에 해당 객체가 존재하는지 확인하는 메소드
	 * 
	 * @param book : 도서 ID를 가지고 있는 도서 객체
	 * @return 존재하면 true 존재하지 않으면 false 리턴
	 */
	private boolean isExists(Book book) {
		
		boolean isExists = false;
		
		// 1. SqlSession 얻기
		SqlSession session = factory.openSession();
		
		// 2. Mapper 인터페이스 객체를 session에서 얻기
		BookMapper mapper;
		mapper = session.getMapper(BookMapper.class);
		
		// 3. mapper 객체에 메소드 호출로 쿼리 실행
		try {
			String bookId = mapper.isExists(book);
			
			if (bookId != null) {
				isExists = true;
			}
			
		} finally {
			// 4. session 닫기
			if (session != null) {
				session.close();
			}
			
		}
		return isExists;
		
	}
	

}
