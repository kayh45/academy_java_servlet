package session;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * session/login.jsp 에서 넘어온 요청 파라미터
 * (userId, password) 로 로그인 성공을 가정하고
 * HttpSession 을 사용하여 다음 페이지로 이동 처리하는 서블릿
 */
@WebServlet({ "/login", "/session/login" })
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 세션 로그인 화면으로 이동
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 뷰 설정
		String view = "/views/session/login";
		
		// 2. 페이지 이동
		RequestDispatcher reqd = request.getRequestDispatcher(view);
		reqd.forward(request, response);
		
	}

	/**
	 * userId, password 요청 파라미터 처리하여
	 * 로그인 성공을 가정한 후 HttpSession을 사용하여
	 * 로그인을 유지
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 한글 처리
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		// 2. 요청 파라미터 추출
		String userId = request.getParameter("userId");
		String password = request.getParameter("password");
		
		// 3. 아이디 = java , 비밀번호= jsp 인 경우 성공으로 가정
		if ("java".equals(userId) && "jsp".equals(password)) {
			// 4. HttpSession 타입의 객체를 생성
			//    세션은 요청(request)에서 생성
			HttpSession session;
			// 현재의 요청(request)에 연결된 세션을 알아냄
			session = request.getSession();
			
			/*-----------------------------------------------
			 * 세션 객체를 요청으로부터 얻어낼 떄 주의점
			 * 1. getSession() 메소드를 사용하여 얻어냄
			 * 2. getSession() 메소드 파라미터로
			 *    어떤 값을 사용하느냐에 따라 다르게 작동
			 * 3. 입력파라미터 종류 true, false, 생략
			 * 
			 * getSession(true); getSession();
			 *   ==> 현재의 요청(request)와 이미 연결된
			 *       세션이 존재하면 그 세션을 리턴
			 * 		 없으면 새로 생성 후 리턴
			 * 	 ==> 신규 로그인 처리 시도에 사용
			 * 
			 * getSession(false); 
			 *   ==> 현재의 요청(request)와 이미 연결된
			 *       세션이 존재하면 그 세션을 리턴
			 *       없으면 null 리턴
			 *       없어도 새로 생성 안함
			 *   ==> 로그아웃 처리에 사용
			 * 
			 */
			
			// 5. 생성된 세션에 유저 아이디를 속성 추가
			session.setAttribute("userId", userId);
			
			// 6. 다음 페이지로 이동
			String view = "/views/session/success";
			RequestDispatcher reqd = request.getRequestDispatcher(view);
			reqd.forward(request, response);
			
		} else {
			// 비밀번호, 아이디 불일치 로그인 실패
			ServletContext context = getServletContext();
			String path = context.getContextPath();
			String location = path + "/views/sessoin/fail";
			
			response.sendRedirect(location);
		}
		
	
	}

}
