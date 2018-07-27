package shop.controller;

import java.io.IOException;


import static shop.factory.WarehouseFactory.getWarehouse;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.dao.GeneralWarehouse;
import shop.exception.NotFoundException;
import shop.vo.Product;

/**
 * detail.jsp 에서 삭제 링크로 발생하는 요청 
 * (delete?prodCode=xxx) 을 처리하는 서블릿
 * @author PC38219
 *
 */
@WebServlet("/delete")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 제품 상세 조회에서 삭제 링크를 통해 발생하는 
	 * GET 요청을 처리
	 * 1. 요청파라미터 prodCode를 추출
	 * 2. delete 쿼리 수행
	 * 3. 성공 / 실패 메시지 발생
	 * 4. 성공 / 실패 뷰 처리
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 한글 처리
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		// 2. 모델 생성 : 삭제
		// (1) 요청 파라미터 추출
		String prodCode = request.getParameter("prodCode");
		
		// (2) Product 객체로 포장
		Product product = new Product(prodCode);
		
		// (3) DB 객체 선언/얻기
		GeneralWarehouse warehouse;
		warehouse = getWarehouse("mybatis");
		
		// 3. view 관련 변수들 선언
		// (1)
		String view = null;
		String next = null;
		String message = null;
		
		try {
			// 2.(4) delete 수행
			warehouse.remove(product);
			
			// 2.(5) 삭제 성공 메시지 발생
			message = String.format("제품 정보 [%s] 삭제에 성공하였습니다.", product.getProdCode());
			
		} catch (NotFoundException e) {
			// 2.(5) 삭제 실패 메시지 발생
			message = e.getMessage();
			
			e.printStackTrace();
		}
		
		request.setAttribute("message", message);
		
		// 3. view 선택
		// (2) 1차 뷰 선택
		view = "messageJsp";
		// (3) 2차 뷰 선택
		next = "list";
		
		request.setAttribute("next", next);
		
		// 4. 결정된 view로 이동
		RequestDispatcher reqd;
		reqd = request.getRequestDispatcher(view);
		
		reqd.forward(request, response);
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
