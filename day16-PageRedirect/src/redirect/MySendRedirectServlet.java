package redirect;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 서블릿 페이지 이동 방식 중 <br />
 * sendRedirect 방식을 사용하여 페이지 이동을 <br />
 * 테스트 하는 클래스 <br />
 * --------------------------------------------- <br />
 * sendRedirect 기법은
 * 최초의 request 에 대하여 response를 일으키고
 * 새로운 request 로 다음 서블릿을 요청한다.
 * 
 * 따라서 최초의 request 에 설정했던 속성들이
 * 유지되지 않는다.
 * 
 * @author PC38219
 *
 */
@WebServlet("/redirect")
public class MySendRedirectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * redirect 목적페이지인 next로 이동하기 전
	 * 현재의 request 에 대한 응답이 일어나기 때문에 
	 * 브라우저 주소창에 요청 주소가 다음 페이지로 변경됨
	 * 
	 * 그리고 request 가 변경되기 때문에 설정한 속성이
	 * 유지되지않는다.
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 1. request 객체에 name 속성을 설정
		request.setAttribute("name", "강현");
		
		// 2. sendRedirect() 로 next를 요청
		//    redirect 는 응답을 일으키므로 response에 적용
		response.sendRedirect("next");
		
	}

}
