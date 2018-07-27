package book.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import book.dao.BookShelf;
import book.exception.NotFoundException;
import book.vo.Book;

/**
 * 도서 1건 수정 요청 처리하는 서블릿
 * ------------------------------------------------
 * - GET
 * 1. detail.jsp 에서 [수정] 링크를 통해 들어온 요청
 *    => 현재 상세보기 하고 있던 도서를 조회를 하여
 *    => 수정할 수 있는 화면인 update.jsp 로 전달
 *    => 수정을 위한 화면 이동 (이미 있는 화면 요청)
 *    => 그래서 GET 요청으로 처리함
 * ------------------------------------------------
 * - POST
 * 2. update.jsp 에서 수정된 내용을 [저장] 버튼을 통해
 *    들어온 요청을 처리
 *    => 변경된 입력내용을 실제 update 쿼리를 통해 
 *       수행하여 DB에 영구반영
 *    => 수정 성공/실패를 알리는 메시지 발생
 *    => 메시지를 처리할 뷰를 선택
 *    => 2차 뷰를 선택 
 */
@WebServlet({"/update", "/main/update"})
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/**
	 * detail.jsp 에서 update?bookId=xxx 으로 발생한 
	 * GET 요청을 처리
	 * 1. 전달된 요청 파라미터(bookId)를 추출
	 * 2. 해당 도서 정보 조회
	 * 3. 조회된 정보를 request 추가
	 * 4. 수정 가능한 뷰를 선택 후 화면 이동
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 2. 모델 준비
		// (1)전달된 요청 파라미터(bookId)를 추출 
		String bookId = request.getParameter("bookId");
		
		// (2) bookId 만으로 Book 포장
		Book book = new Book(bookId);
		
		// (3) DB조회에 사용할 객체 준비              
		BookShelf bookshelf;
		bookshelf = (BookShelf) getServletContext().getAttribute("bookshelf");
		
		// 3. View 선택
		// (1) view 저장 변수 선언
		String view = null;
		String next = null;
		String message = null;
		
		try {
			// 2.(4) 수정을 위한 도서 정보 조회
			Book found = bookshelf.select(book);
			
			// 2.(5) requsest 에 수정 도서 정보 속성 추가
			request.setAttribute("book", found);
			
			// 3.(2) view 선택
			view = "/updateJsp";
					
		} catch (NotFoundException e) {
			// 2.(6) 수정 도서번호가 없는 경우 실패 메시지
			message = e.getMessage();
			request.setAttribute("message", message);
			
			// 3.(2) view 선택
			view = "/messageJsp";
			
			// 3.(3) 2차 뷰 선택
			next = "list";
			request.setAttribute("next", next);
			
			e.printStackTrace();

		}
		
		// 4. 수정 가능한 뷰를 선택 후 화면 이동      
		RequestDispatcher reqd;
		reqd = request.getRequestDispatcher(view);
		
		reqd.forward(request, response);
		
	}

	/**
	 * update.jsp 에서 form 의 submit 이 일어났을 때 
	 * POST 로 요청되는 저장을 처리
	 * 1. 수정할 전체 도서 정보의 요청 파라미터 추출
	 * 2. 변경 내용으로 update 쿼리를 수행
	 * 3. 수정 성공 / 실패에 메시지 발생
	 * 4. 성공 / 실패 뷰 처리
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 2. 모델 생성
		// (1) 요청 파라미터 추출
		String bookId = request.getParameter("bookId");
		String title = request.getParameter("title");
		String author = request.getParameter("author");
		int price = Integer.parseInt(request.getParameter("price"));
		String isbn = request.getParameter("isbn");
		String publish = request.getParameter("publish");
		
		// (2) 요청 파라미터 Book으로 포장
		Book book = new Book(bookId, title, author, price, isbn, publish);
		
		// (3) update 수행을 위하여 DB객체 얻기
		BookShelf bookshelf;
		bookshelf = (BookShelf) getServletContext().getAttribute("bookshelf");
		
		// 3. view
		// (1) 관련 변수 선언
		String view = null;
		String next = null;
		String message = null;
		
		try {
			// (4) update 수행
			bookshelf.update(book);
			
			// (5) 수정 성공 메시지 발생
			message = String.format("도서 정보 [%s] 수정에 성공하였습니다.", book.getBookId());
			
			// 3.(3) 2차 뷰 선택
			next = "main/detail?bookId=" + bookId;
			
		} catch (NotFoundException e) {
			// (5) 수정 실패 메시지 발생
			message = e.getMessage();
			
			// 3.(3) 수정 실패 시 2차 뷰
			next = "main/list";
			e.printStackTrace();
		}
		
		// (6) 메시지 request에 속성으로 추가
		request.setAttribute("message", message);

		// 3. view 선택
		// (2) 수정에 실패 / 성공 모두 messageJsp로 전송
		view = "/messageJsp";
		
		// (4) 2차 뷰 request 에 속성 추가
		request.setAttribute("next", next);
		
		RequestDispatcher reqd;
		reqd = request.getRequestDispatcher(view);
		
		reqd.forward(request, response);
		
		
	}

}
