package book.dao;

import static java.sql.DriverManager.getConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import book.exception.DuplicateException;
import book.exception.NotFoundException;
import book.vo.Book;

public class JdbcBookShelf implements BookShelf {

	// 커넥션 정보
	private static final String URL = "jdbc:oracle:thin:@//127.0.0.1:1521/XE";
	private static final String USER = "SCOTT";
	private static final String PASSWORD = "TIGER";
	private static final String DRIVER = "oracle.jdbc.OracleDriver";
	
	// 멤버 변수 선언부
	public static JdbcBookShelf instance;
	
	/**
	 * 싱글턴 패턴을 위한
	 * getInstance() 메소드
	 * <br>
	 * JdbcBookShelf 타입의 
	 * instance 가 존재하지 않으면 새 인스턴스 생성
	 */
	public static JdbcBookShelf getInstance() {
		
		if (instance == null) {
			instance = new JdbcBookShelf();
		}
		
		return instance;
	}
	
	// 생성자 선언부
	
	/**
	 * 인스턴스 생성과 함께
	 * JDBC를 연결하기 위한 드라이브 로드를 진행
	 */
	private JdbcBookShelf() {
		try {
			Class.forName(DRIVER);
			
		} catch (ClassNotFoundException e) {
			System.err.println("드라이버 오류!!");
			e.printStackTrace();
			
		}
	}
	
	// 메소드 선언부
	@Override
	public int insert(Book book) throws DuplicateException {
		/*
		 * INSERT 쿼리를 이용하여 DB에 새로운 도서 정보를 추가  
		 */
		
		// 이미 존재하는 도서라면 예외처리
		if (isExist(book)) {
			throw new DuplicateException("추가", book);
		}
		
		int insCnt = 0;
		
		// 필요 객체 선언
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			// 커넥션 맺기
			conn = DriverManager.getConnection(URL, USER, PASSWORD);

			// 쿼리 준비
			String sql = "INSERT INTO book (bookid, title, author, price, isbn, publish)"
					   + "VALUES (?, ?, ?, ?, ?, ?)";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, book.getBookId());
			pstmt.setString(2, book.getTitle());
			pstmt.setString(3, book.getAuthor());
			pstmt.setInt(4, book.getPrice());
			pstmt.setString(5, book.getIsbn());
			pstmt.setString(6, book.getPublish());
			
			// 쿼리 실행 (성공 시 1 리턴)
			insCnt = pstmt.executeUpdate();
			
			// 결과 처리
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			// 자원 해제
			closeResources(null, pstmt, conn);
		}
		
		return insCnt;
	}

	@Override
	public int update(Book book) throws NotFoundException {
		/*
		 * UPDATE 쿼리를 이용하여 DB에 도서 정보를 수정  
		 */
		
		// 존재하지 않는 도서라면 예외처리
		if (!isExist(book)) {
			throw new NotFoundException("수정", book);
		}
		
		int udtCnt = 0;
		
		// 필요 객체 선언
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			// 커넥션 맺기
			conn = DriverManager.getConnection(URL, USER, PASSWORD);

			// 쿼리 준비
			String sql = "UPDATE book b"
					   + "   SET b.title = ?"
					   + "	   , b.author = ?"
					   + "	   , b.price = ?"
					   + "	   , b.isbn = ?"
					   + "	   , b.publish = ?" 
					   + "	   , b.moddate = sysdate"
					   + " WHERE b.bookid = ?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, book.getTitle());
			pstmt.setString(2, book.getAuthor());
			pstmt.setInt(3, book.getPrice());
			pstmt.setString(4, book.getIsbn());
			pstmt.setString(5, book.getPublish());
			pstmt.setString(6, book.getBookId());
			
			// 쿼리 실행 (성공 시 1 리턴)
			udtCnt = pstmt.executeUpdate();
			
			// 결과 처리
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			// 자원 해제
			closeResources(null, pstmt, conn);
		}
		
		return udtCnt;
	}

	@Override
	public int delete(Book book) throws NotFoundException {
		/*
		 * DELETE 쿼리를 이용하여 DB에 도서 정보를 삭제 
		 */
		
		// 존재하지 않는 도서라면 예외처리
		if (!isExist(book)) {
			throw new NotFoundException("삭제", book);
		}
		
		int delCnt = 0;
		
		// 필요 객체 선언
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			// 커넥션 맺기
			conn = DriverManager.getConnection(URL, USER, PASSWORD);

			// 쿼리 준비
			String sql = "DELETE book b"
					   + " WHERE b.bookid = ?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, book.getBookId());
			
			// 쿼리 실행 (성공 시 1 리턴)
			delCnt = pstmt.executeUpdate();
			
			// 결과 처리
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			// 자원 해제
			closeResources(null, pstmt, conn);
		}
		
		return delCnt;
	}

	@Override
	public Book select(Book book) throws NotFoundException {
		/*
		 * SELECT 쿼리를 이용하여 DB에서 도서 정보(1 건)를 조회
		 */
		
		// 존재하지 않는 도서라면 예외처리
		if (!isExist(book)) {
			throw new NotFoundException("조회", book);
		}
		
		// 필요 객체 선언
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		Book foundBook = null;
		
		try {
			// 커넥션 맺기
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			
			// 쿼리 준비
			String sql = "SELECT b.bookid    "
				       + "     , b.title     "
				       + "     , b.author    "
				       + "     , b.price     "
				       + "     , b.isbn      "
				       + "     , b.publish   "
				       + "  FROM book b      "
				       + " WHERE b.bookid = ?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, book.getBookId());
			
			// 쿼리 실행
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				String bookId = rs.getString(1);
				String title = rs.getString(2);
				String author = rs.getString(3);
				int price = rs.getInt(4);
				String isbn = rs.getString(5);
				String publish = rs.getString(6);

				foundBook = new Book(bookId, title, author, price, isbn, publish);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
		
		return foundBook;
	}

	@Override
	public List<Book> select() {
		/*
		 * SELECT 쿼리를 이용하여 DB에서 모든 도서 정보를 조회
		 */
		// 필요 객체 선언
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<Book> allBooks = new ArrayList<Book>();

		try {
			// 커넥션 맺기
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			
			// 쿼리 준비
			String sql = "SELECT b.bookid    "
				       + "     , b.title     "
				       + "     , b.author    "
				       + "     , b.price     "
				       + "     , b.isbn      "
				       + "     , b.publish   "
				       + "  FROM book b      ";
			
			pstmt = conn.prepareStatement(sql);
			
			// 쿼리 실행
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				String bookId = rs.getString(1);
				String title = rs.getString(2);
				String author = rs.getString(3);
				int price = rs.getInt(4);
				String isbn = rs.getString(5);
				String publish = rs.getString(6);

				Book foundBook = new Book(bookId, title, author, price, isbn, publish);
				
				allBooks.add(foundBook);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
		
		return allBooks;
	}

	@Override
	public List<Book> select(int low, int high) {
		/*
		 * SELECT 쿼리를 이용하여 DB에서
		 * 가격이 매개변수 low와 high 사이인 도서 정보를 조회
		 * 
		 */
		// 필요 객체 선언
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<Book> foundBooks = new ArrayList<Book>();

		try {
			// 커넥션 맺기
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			
			// 쿼리 준비
			String sql = "SELECT b.bookid               "
				       + "     , b.title                "
				       + "     , b.author               "
				       + "     , b.price                "
				       + "     , b.isbn                 "
				       + "     , b.publish              "
				       + "  FROM book b                 "
				       + " WHERE b.price BETWEEN ? AND ?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, low);
			pstmt.setInt(2, high);
			
			// 쿼리 실행
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				String bookId = rs.getString(1);
				String title = rs.getString(2);
				String author = rs.getString(3);
				int price = rs.getInt(4);
				String isbn = rs.getString(5);
				String publish = rs.getString(6);

				Book foundBook = new Book(bookId, title, author, price, isbn, publish);
				
				foundBooks.add(foundBook);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
		
		return foundBooks;
	}

	@Override
	public List<Book> select(String keyword) {
		/*
		 * SELECT 쿼리를 이용하여 DB에서
		 * 제목에 매개변수로 받은 키워드(keyword)가 포함된
		 * 도서 정보들을 조회 
		 * 
		 */
		// 필요 객체 선언
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<Book> foundBooks = new ArrayList<Book>();

		try {
			// 커넥션 맺기
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			
			// 쿼리 준비
			String sql = "SELECT b.bookid                         "
				       + "     , b.title                          "
				       + "     , b.author                         "
				       + "     , b.price                          "
				       + "     , b.isbn                           "
				       + "     , b.publish                        "
				       + "  FROM book b                           "
				       + " WHERE b.title LIKE '%' || ? || '%'     ";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, keyword);
			
			// 쿼리 실행
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				String bookId = rs.getString(1);
				String title = rs.getString(2);
				String author = rs.getString(3);
				int price = rs.getInt(4);
				String isbn = rs.getString(5);
				String publish = rs.getString(6);

				Book foundBook = new Book(bookId, title, author, price, isbn, publish);
				
				foundBooks.add(foundBook);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
		
		return foundBooks;
	}

	@Override
	public int totalCount() {
		/*
		 * SELECT count() 쿼리를 이용하여 DB에
		 * 존재하는 데이터의 개수를 조회
		 * 
		 */
		// 필요 객체 선언
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int allCnt = 0;

		try {
			// 커넥션 맺기
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			
			// 쿼리 준비
			String sql = "SELECT count(b.bookid)"
					   + "  FROM book b         ";
			
			pstmt = conn.prepareStatement(sql);
			
			// 쿼리 실행
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				allCnt = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		}

		
		return allCnt;
	}
	
	// 지원 메소드 선언부
	
	/**
	 * 등록 수정 삭제 조회할 대상 도서의
     * 존재 여부를 확인하는 메소드
     * 
	 * @param book
	 * @return 존재하면 true, 존재하지 않으면 false
	 */
	private boolean isExist(Book book) {
		boolean isExist = false;
		
		// 필요 객체 선언
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			// 커넥션
			conn = getConnection(URL, USER, PASSWORD);
			// 쿼리 준비
			String sql = "SELECT b.bookid    "
					   + "  FROM book b      "
					   + " WHERE b.bookid = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, book.getBookId());
			
			// 쿼리 실행
			rs = pstmt.executeQuery();
			
			// 결과 처리
			while (rs.next()) {
				// 조회 결과가 있다는 뜻은
				// 동일 제품 코드가 등록되었음을 뜻함
				isExist = true;
			}
					
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			// 자원 해제
			closeResources(rs, pstmt, conn);
		}
		
		return isExist;
	}
	
	/**
	 * 반복되는 자원해제 코드를 수행하는 지원 메소드
	 * 
	 * @param rs
	 * @param stmt
	 * @param conn
	 */
	private void closeResources(ResultSet rs, Statement stmt, Connection conn) {
		try {
			if (rs != null)
				rs.close();
			
			if (stmt != null) 
				stmt.close();
			
			if (conn != null)
				conn.close();
			
		} catch (SQLException e) {
			System.err.println("자원 반납 오류!");
			e.printStackTrace();
			
		}
		
	}

	@Override
	public int delete() {
		/*
		 * DELETE 쿼리를 이용하여 DB에서 모든 도서 정보를 삭제 
		 */		
		
		int delCnt = 0;
		
		// 필요 객체 선언
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			// 커넥션 맺기
			conn = DriverManager.getConnection(URL, USER, PASSWORD);

			// 쿼리 준비
			String sql = "DELETE book b";
			
			pstmt = conn.prepareStatement(sql);
			
			// 쿼리 실행 (성공 시 1 리턴)
			delCnt = pstmt.executeUpdate();
			
			// 결과 처리
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			// 자원 해제
			closeResources(null, pstmt, conn);
		}
		
		return delCnt;
	}

}
