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
 * 로그아웃 링크가 클릭되면 현재 세션을
 * 명시적으로 해제하여 로그아웃 처리하는 서블릿
 */
@WebServlet({ "/logout", "/session/logout" })
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 이미 존재하는 세션을 얻어냄
		HttpSession session;
		session = request.getSession(false);
		/* 로그아웃 처리를 위해서는 얻어진 세션이
		 * 이전에 로그인 되었던 것이라는 보증이 필요함
		 * 따라서, false 옵션으로 현재의 요청과
		 * 연결되어있던 세션을 얻어내야 함.
		 *
		 */
		if (session != null) {
			// 2. 로그인 되었던 세션이 있으면 해제
			session.invalidate();
			// invalidate() 하고나면
			// 세션객체에 설정되었던 모든 속성도 자동삭제됨
		}
		
		// 3. 로그아웃 처리 되었으므로 로그인 화면으로 이동
		ServletContext context = getServletContext();
		String path = context.getContextPath();
		String location = path + "/session/login";
		
		response.sendRedirect(location);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

}
