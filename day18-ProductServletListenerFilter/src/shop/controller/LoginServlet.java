package shop.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet({ "/login", "/main/login" })
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * login/login.jsp 로 이동
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 뷰 결정
		String view = "loginJsp";
		
		// 페이지 이동
		RequestDispatcher reqd = request.getRequestDispatcher(view);
		reqd.forward(request, response);
	
	}

	/**
	 * 로그인 처리 된 것 처럼 가정
	 * 아이디 : java
	 * 비밀번호 : jsp
	 * 일 때 성공으로 가정함
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 로그인 시도 화면에서 전달된 파라미터 추출
		String userid = request.getParameter("userid");
		String password = request.getParameter("password");
		
		// 3. 페이지 이동에 사용할 객체들
		String view = null;
		String next = null;
		String message = null;
		
		if ("java".equals(userid) && "jsp".equals(password)) {
			// 로그인 성공 시 세션을 얻어냄
			// 매개변수를 생략하거나 true로 입력
			HttpSession session = request.getSession();
			
			// 생성된 session 객체에 로그인한 아이디 속성 추가
			session.setAttribute("userid", userid);
			
			// 로그인 성공 메시지를 request 에 속성으로 추가
			message = userid + "님이 로그인 하였습니다.";
			
			// 뷰 결정
			view = "messageJsp";
			
			// 2차 뷰 결정
			next = "menu";
		} else {
			message = "로그인 실패하였습니다.";
			view = "messageJsp";
			next = "login";
			
		}
		
		request.setAttribute("message", message);
		
		RequestDispatcher reqd = request.getRequestDispatcher(view);
		reqd.forward(request, response);

		
		
	}

}
