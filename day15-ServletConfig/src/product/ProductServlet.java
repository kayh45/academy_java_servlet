package product;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/product", "/myProd"}, 
            initParams = {@WebInitParam(name="company", value="리복"), 
                          @WebInitParam(name="ceoName", value="길동이")
                         }
           )
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private String company;
	private String ceoName;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		company = config.getInitParameter("company");
		ceoName = config.getInitParameter("ceoName");
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 응답 객체 한글 처리
		response.setContentType("text/html;charset=utf-8");
		
		// 2. request 객체에 attribute로 추가
		request.setAttribute("company", company);
		request.setAttribute("ceoName", ceoName);
		
		// 3. 초기화 파라미터 읽은 내용 출력
		PrintWriter out = response.getWriter();
		
		out.print("company : " + company + "<br />");
		out.print("ceoName : " + ceoName + "<br />");
		out.print("<hr />");
		out.print((String) request.getAttribute("company") + "<br />");
		out.print((String) request.getAttribute("ceoName") + "<br />");
		
		out.close();
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
