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
import shop.exception.DuplicateException;
import shop.vo.Product;

/**
 * 신규 제품 등록을 처리하는 서블릿
 * 이 클래스에 GET 방식 / POST 방식으로 진입할 때
 * 각기 다르게 작동
 * -------------------------------------------------
 * GET : 등록 받는 화면 이동
 * POST : 등록화면에서 전달된 데이터를 DB insert 터리
 * 
 * @author PC38219
 *
 */
@WebServlet({"/insert", "/main/insert"})
public class InsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * menu.jsp 에서 링크를 클릭했을 때 발생하는
	 * 신규 제품 등록 GET 요청을 처리한다.
	 * insert.jsp 로 이동
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 뷰를 결정
		String view = "/insertJsp";
		
		// RequestDispatcher 로 이동
		RequestDispatcher reqd;
		reqd = request.getRequestDispatcher(view);
		
		reqd.forward(request, response);
	
	}

	/**
	 * insert.jsp 에서 등록하기 버튼이 클릭되었을 때
	 * post 요청을 처리한다.
	 * 내부적으로 insert.jsp 의 form 으로 전달된
	 * 파라미터를 추출하여 Product 객체로 포장하고
	 * insert 쿼리를 실행
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 2. 모델 생성
		// (1) insert.jsp 에서 넘어온 파라미터 추출
		String prodCode = request.getParameter("prodCode");
		String prodName = request.getParameter("prodName");
		int price = Integer.parseInt(request.getParameter("price"));
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		
		// (2) Product 타입으로 포장
		Product product = new Product(prodCode, prodName, price, quantity);
		
		// (3) DB입력에 필요한 객체 선언
		GeneralWarehouse warehouse;
		warehouse = (GeneralWarehouse) getServletContext().getAttribute("warehouse");
		
		// DB 입력 성공/실패 시 발생하는 메시지 변수
		String message = null;

		// 3.(2) 메시지 출력 후 이동할 뷰 페이지
		String next = "";

		try {
			// (4) insert 쿼리 수행 
			warehouse.add(product);
			message = String.format("제품 정보 [%s] 추가에 성공하였습니다.", product.getProdCode());
			
			// 추가 성공 후 전체 목록으로 자동 이동
			next = "main/list";
			
		} catch (DuplicateException e) {
			message = String.format(e.getMessage());
			
			// 실패 시 다시 입력하는 화면으로 자동이동
			next = "main/insert";

			e.printStackTrace();
			
		}
		
		// (5) 추가 성공 / 실패에 따른 발생 메시지를 
		//     request에 속성으로 추가
		request.setAttribute("message", message);
		
		// 3. View 결정
		// (1) 추가 성공 / 실패 메시지를 출력할 1차 뷰
		String view = "/messageJsp";
		
		// (2) 2차 뷰 선택된 내용을 request 에 속성으로 추가
		request.setAttribute("next", next);
		
		RequestDispatcher reqd;
		reqd = request.getRequestDispatcher(view);
		
		reqd.forward(request, response);
		
		
	}

}
