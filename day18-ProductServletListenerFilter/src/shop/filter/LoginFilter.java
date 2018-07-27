package shop.filter;

import java.io.IOException;

import static shop.util.LoginChecker.isLogin;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;


@WebFilter(filterName = "loginfilter"
         , urlPatterns = {"/main/*"})
public class LoginFilter implements Filter {

	@Override
	public void init(FilterConfig config) throws ServletException {

	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// 1. 전처리
		System.out.println("==== 2. 로그인 필터 진입 ====");
		
		if (isLogin((HttpServletRequest)request)) {
			// 현재 로그인 상태를 request에 속성 추가
			request.setAttribute("isLogin", "true");
			
			// 2. 다음 필터 진행
			chain.doFilter(request, response);
		} else {
			// 로그인 시도인 경우는 정상진행
			// 로그인 시도 화면으로부터 요청파라미터가
			// 전달되었을 것이므로 그 파라미터로 분기
			String userid = ((HttpServletRequest) request).getParameter("userid");
			if (userid != null) {
				// 로그인 처리를 그대로 수행하게 함
				// 2. 다음 필터 진행
				chain.doFilter(request, response);
			} else {
				// 로그인이 아닌 상태라면 로그인 화면으로 이동
				request.setAttribute("isLogin", "false");
				
				RequestDispatcher reqd = request.getRequestDispatcher("login");
				reqd.forward(request, response);
			}
			
		}
		
		// 3. 후처리
		System.out.println("==== 2. 로그인 필터 종료 ====");
		
	}
	
	@Override
	public void destroy() {

	}





}
