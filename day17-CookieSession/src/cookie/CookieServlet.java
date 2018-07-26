package cookie;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * cookie/login.jsp 에서 전송된 로그인 정보를 사용하여
 * --------------------------------------------------
 * 1. 가상의 로그인 성공처리를 한 뒤 
 * 2. 로그인 성공 정보를 Cookie 로 생성
 * 3. 응답객체에 추가 후
 * 4. 로그인 성공 페이지로 이동
 * --------------------------------------------------
 * 시키며 쿠키를 테스트하는 서블릿
 * 
 */
@WebServlet({ "/CookieServlet", "/cookie/login" })
public class CookieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/**
	 *  /cookie/login 의 주소로 GET 요청 발생 시
	 *  쿠키 로그인 jsp로 페이지 이동
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 뷰 결정 (web.xml에 등록된 url 매핑 주소)
		String view = "/views/cookie/login";
		
		// 2. 뷰로 이동
		RequestDispatcher reqd;
		reqd = request.getRequestDispatcher(view);
		
		reqd.forward(request, response);
	
	}

	/**
	 * cookie/login.jsp 에서 전송된 요청 파라미터(아이디, 비번)를 
	 * 가상으로 로그인 처리하여
	 * 쿠키객체 생성 후 응답
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 한글 처리
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		// 2. 요청 파라미터 추출
		String userId = request.getParameter("userId");
		String password = request.getParameter("password");
		
		// 3. 로그인 성공을 가정함
		// 아이디 = java, 비밀번호 = jsp 인 경우 로그인 성공
		
		if ("java".equals(userId) && "jsp".equals(password)) {
			// Cookie 정보 생성
			Cookie ckUserId = new Cookie("userId", userId);
			Cookie ckPassword = new Cookie("password", password);
			
			// Cookie 만료 시간 설정
			ckUserId.setMaxAge(10); // 10 초 후 만료
			ckPassword.setMaxAge(10); // 10 초 후 만료
			
			// Cookie 를 응답(response)에 추가
			response.addCookie(ckUserId);
			response.addCookie(ckPassword);
			
		}
		
		// 4. 로그인 성공 페이지로 이동
		ServletContext context = getServletContext();
		String view = context.getContextPath() + "/cookie/views/success";	
		
		response.sendRedirect(view);
		
	}

}
