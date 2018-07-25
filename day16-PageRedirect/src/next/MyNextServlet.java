package next;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 이 서블릿은 세 개의 서블릿에서 이동되는 두 번째 서블릿 <br />
 * ------------------------------------------------------ <br />
 * MyForwardServlet : forward 방식으로 이동 <br />
 * MyIncludeServlet : include 방식으로 이동 <br />
 * MySendRedirectServlet : send redirect 방식으로 이동 <br />
 * ------------------------------------------------------ <br />
 * 각각의 이동 방식에 따라 이 서블릿에서 결과가 달라지는 모습을 확인
 * 
 * @author PC38219
 *
 */
@WebServlet("/next")
public class MyNextServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 세 개의 출발 서블릿 클래스들에서
	 * request 에 name 속성을 추가해서 전달하므로
	 * 여기서는 name 속성을 추출하여 화면에 출력해본다
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 1. 요청 객체 한글 처리
		request.setCharacterEncoding("utf-8");
		
		// 2. 응답 객체 한글 처리
		response.setContentType("text/html;charset=utf-8");
		
		// 3. name 속성 추출
		String name = (String) request.getAttribute("name");
		
		// 4. 화면 출력
		PrintWriter out = response.getWriter();
		
		out.print("<h3>이전 페이지에서 등록한 이름: ");
		out.print(name);
		out.print("</h3>");
		out.close();
		
	}

}
