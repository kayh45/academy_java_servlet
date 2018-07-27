package shop.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * 이 어플리케이션(서비스)에서 사용할 초기 값을 설정하는
 * 리스너 클래스
 * 
 * warehouse 타입설정, mybatis 로그 파일 위치를 
 * web.xml <context-param> 으로 초기화
 * @author PC38219
 *
 */
public class ShopListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent event) {
		// 웹 어플리케이션이 서블릿 컨테이너(톰캣)에 의해
		// 구동될 때, 시작 이벤트가 발생하면 자동으로 호출됨
		System.out.println("Shop 어플리케이션 컨텍스트 리스터 구동");
		
		// 1. 전체 웹 어플리케이션의 설정 정보를 담는 컨텍스트
		//    객체를 이벤트(매개변수)로 부터 얻어냄
		ServletContext context = event.getServletContext();
		
		// 2. mybatis 의 로그 파일을 원하는 위치에 쓰려면
		//    파일 쓰기이기 때문에 물리적인 디스크의 위치가 필요
		String realPath = context.getRealPath("/");
		
		// 3. web.xml 에 설정된 logFileLoc 초기화 파라미터 추출
		String logFileLoc = context.getInitParameter("logFileLoc");
		
		// 4. 물리적인 디스크의 위치인 realPath 와
		//    그 이후 로그 파일을 저장할 위치를 합쳐서
		//    이 시스템의 변수로 추가
		System.setProperty("shopLog", realPath + logFileLoc);
		
		// 5. 이 웹 어플리케이션이 유지하는 context 객체에
		//    컨텍스트 경로를 속성으로 추가
		context.setAttribute("contextPath", context.getContextPath());
	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		// 서블릿 컨테이너(톰캣)에 의해 웹 어플리케이션이
		// 해제될 때 해제 이벤트를 자동으로 잡아서
		// 호출되는 메소드
		
		// 이 예제에서는 아무런 것도 진행하지 않음.
		
	}


}
