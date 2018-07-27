package book.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

/**
 * 서비스에 진입하는 요청에 대하여
 * 요청 객체와 응답 객체에 한글 설정을 하는
 * 인코딩 필터 클래스
 * ---------------------------------------------
 * web.xml에 <filter> 엘리먼트로 등록하거나
 * ---------------------------------------------
 * @WebFilter 애노테이션으로 등록해서 사용한다.
 * ---------------------------------------------
 * filterName = encodingFilter
 * 
 * 두 개의 초기화 파라미터 설정
 * charEncoding : utf-8
 * contentType : text/html;charset=utf-8
 * 
 * 이 필터가 작동할 주소 형태 등록
 * urlPatterns : /*
 * 				==> 이 서비스(어플리케이션)의 컨텍스트 패스
 *                  이하로 들어오는 모든 요청 주소
 *                  에 대하여 이 필터가 작동함
 * @author PC38219
 *
 */

@WebFilter(filterName="encodingFilter"
         , initParams={@WebInitParam(name="charEncoding", value="utf-8")
         			 , @WebInitParam(name="contentType", value="text/html;charset=utf-8")}
		 , urlPatterns={"/*"}  
		)
public class EncodingFilter implements Filter {

	// 이 필터가 사용할 필터 설정값을 저장하는 멤버 변수
	private FilterConfig config;
	
	@Override
	public void init(FilterConfig config) throws ServletException {
		/*
		 * 필터의 init() 메소드는 서블릿 컨테이너(톰캣)에 의해서
		 * 필터 초기화 시에 자동으로 작동함.
		 * init()이 호출될 때 서블릿 컨테이너는 
		 * web.xml(@WebFilter) 에 선언되어있는 필터 정보를 저장하는
		 * FilterConfig 객체를 매개변수로 전달
		 * 
		 * 내 멤버 변수로 저장하여 사용함. 
		 */
		this.config = config;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		// 1. 전처리 : doFilter() 메소드 전에 작성
		System.out.println("===== 1. 인코딩 필터 진입 =====");
		// (1) 요청에 대한 인코딩 설정
		String charEncoding = config.getInitParameter("charEncoding");
		request.setCharacterEncoding(charEncoding);
		
		// (2) 응답에 대한 인코딩 설정
		String contentType = config.getInitParameter("contentType");
		response.setContentType(contentType);
		
		// (3) 콘솔창에 값 출력
		System.out.println("charEncoding : " + charEncoding);
		System.out.println("contentType : " + contentType);
		
		// 2. 다음 필터 호출 : 체인 객체에 다음 필터를 호출하며
		//                     이때 요청(req), 응답(res)을 그대로 넘김
		chain.doFilter(request, response);
		
		// 3. 후처리 : doFilter() 메소드 후에 작성
		System.out.println("===== 1. 인코딩 필터 종료 =====");
	}
	
	@Override
	public void destroy() {
		
	}
	

}
