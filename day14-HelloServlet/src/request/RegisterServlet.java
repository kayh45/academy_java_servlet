package request;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * 
 * @author PC38219
 *
 */
@WebServlet("/regist")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 *  1. 서버의 리소스 요청을 위해서 사용
	 *  2. 등록화면 요청에 사용함
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		RequestDispatcher reqd = request.getRequestDispatcher("registForm");
		reqd.forward(request, response);
		
	}

	/**
	 * 1. 사용자 데이터를 서버로 전송하기 위해서 사용
	 * 2. 폼에서 발생한 데이터를 처리
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 요청에 대한 한글 처리
		request.setCharacterEncoding("utf-8");
		
		// 2. 응답에 대한 한글 처리
		response.setContentType("text/html;charset=utf-8");
		
		// 3. <form>에서 넘어온 파라미터 호출
		// (1) <input>의 name 이 유일한 경우 일반적으로 1개의 String 변수로 받는다.
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		// (2) <input>의 동일한 name이 두 개 이상 전달될 경우 String[] 변수로 받는다.
		String[] hobbies = request.getParameterValues("hobby");
		
		// 4. 출력
		// (1) 기본 : sysout 출력
		System.out.println("등록된 사용자 이름 : " + username);
		System.out.println("등록된 비밀번호: " + password);
		
		// <input> 중에서 type이 checkbox인 경우는
		// 아무것도 선택하지 않은 상황에 대한 고려가 항상 있어야한다.
		if (hobbies != null) {
			for (String hobby : hobbies) {
				System.out.println("등록된 취미 : " + hobby);
			}
		} else {
			System.out.println("등록된 취미가 존재하지 않습니다.");
		}
		
		// (2) 브라우저 출력 : PrintWriter 객체 출력
		PrintWriter out = response.getWriter();
		out.println("<HTML>");
			out.println("<HEAD/>");
			out.println("<BODY>");
				out.println("등록된 사용자 이름 : " + username + "<br>");
				out.println("등록된 비밀번호: " + password + "<br>");
				if (hobbies != null) {
					for (String hobby : hobbies) {
						out.println("등록된 취미 : " + hobby + "<br>");
					}
				} else {
					out.println("등록된 취미가 존재하지 않습니다.<br>");
				}
			out.println("</BODY>");
		out.println("</HTML>");
	}

}
