package book.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LogoutServlet
 */
@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		
		String userid = (String) session.getAttribute("userid");
		
		if (userid != null) {
			session.invalidate();
			
			request.setAttribute("message", userid + "님이 로그아웃 되었습니다.");

			// 로그 아웃 메시지 처리 후 다시 로그인 화면으로
			// 이동시킬 next 값 추가
			request.setAttribute("next", "login");
		}
		
		// 페이지 이동
		request.getRequestDispatcher("/messageJsp").forward(request, response);
		
	}

}
