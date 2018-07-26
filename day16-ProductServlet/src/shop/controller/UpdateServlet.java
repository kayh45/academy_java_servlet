package shop.controller;

import static shop.factory.WarehouseFactory.getWarehouse;

import java.io.IOException;

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
 * 제품 1건 수정 요청 처리하는 서블릿
 * ------------------------------------------------
 * - GET
 * 1. detail.jsp 에서 [수정] 링크를 통해 들어온 요청
 *    => 현재 상세보기 하고 있던 제품을 조회를 하여
 *    => 수정할 수 있는 화면인 update.jsp 로 전달
 *    => 수정을 위한 화면 이동 (이미 있는 화면 요청)
 *    => 그래서 GET 요청으로 처리함
 * ------------------------------------------------
 * - POST
 * 2. update.jsp 에서 수정된 내용을 [저장] 버튼을 통해
 *    들어온 요청을 처리
 *    => 변경된 입력내용을 실제 update 쿼리를 통해 
 *       수행하여 DB에 영구반영
 *    => 수정 성공/실패를 알리는 메시지 발생
 *    => 메시지를 처리할 뷰를 선택
 *    => 2차 뷰를 선택 
 */
@WebServlet("/update")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/**
	 * detail.jsp 에서 update?prodCode=xxx 으로 발생한 
	 * GET 요청을 처리
	 * 1. 전달된 요청 파라미터(prodCode)를 추출
	 * 2. 해당 제품 정보 조회
	 * 3. 조회된 정보를 request 추가
	 * 4. 수정 가능한 뷰를 선택 후 화면 이동
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 1. 한글 처리 (요청, 응답) : 여기서는 필수는 아님
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		// 2. 모델 준비
		// (1)전달된 요청 파라미터(prodCode)를 추출 
		String prodCode = request.getParameter("prodCode");
		
		// (2)prodCode 만으로 Product 포장
		Product product = new Product(prodCode);
		
		// (3) DB조회에 사용할 객체 준비              
		GeneralWarehouse warehouse;
		warehouse = getWarehouse("mybatis");
		
		// 3. View 선택
		// (1) view 저장 변수 선언
		String view = null;
		String next = null;
		String message = null;
		
		try {
			// 2.(4) 수정을 위한 제품 정보 조회
			Product found = warehouse.get(product);
			
			// 2.(5) requsest 에 수정 제품 정보 속성 추가
			request.setAttribute("product", found);
			
			// 3.(2) view 선택
			view = "updateJsp";
					
		} catch (NotFoundException e) {
			// 2.(6) 수정 제품코드가 없는 경우 실패 메시지
			message = e.getMessage();
			request.setAttribute("message", message);
			
			// 3.(2) view 선택
			view = "messageJsp";
			
			// 3.(3) 2차 뷰 선택
			next = "list";
			request.setAttribute("next", next);
			
			e.printStackTrace();

		}
		
		// 4. 수정 가능한 뷰를 선택 후 화면 이동      
		RequestDispatcher reqd;
		reqd = request.getRequestDispatcher(view);
		
		reqd.forward(request, response);
		
	}

	/**
	 * update.jsp 에서 form 의 submit 이 일어났을 때 
	 * POST 로 요청되는 저장을 처리
	 * 1. 수정할 전체 제품 정보의 요청 파라미터 추출
	 * 2. 변경 내용으로 update 쿼리를 수행
	 * 3. 수정 성공 / 실패에 메시지 발생
	 * 4. 성공 / 실패 뷰 처리
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 한글 처리 (수정 데이터 : 제품 이름에 한글 존재)
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		// 2. 모델 생성
		// (1) 요청 파라미터 추출
		String prodCode = request.getParameter("prodCode");
		String prodName = request.getParameter("prodName");
		int price       = Integer.parseInt(request.getParameter("price"));
		int quantity    = Integer.parseInt(request.getParameter("quantity"));
		
		// (2) 요청 파라미터 Product로 포장
		Product product = new Product(prodCode, prodName, price, quantity);
		
		// (3) update 수행을 위하여 DB객체 얻기
		GeneralWarehouse warehouse;
		warehouse = getWarehouse("mybatis");
		
		// 3. view
		// (1) 관련 변수 선언
		String view = null;
		String next = null;
		String message = null;
		
		try {
			// (4) update 수행
			warehouse.set(product);
			
			// (5) 수정 성공 메시지 발생
			message = String.format("제품 정보 [%s] 수정에 성공하였습니다.", product.getProdCode());
			
			// 3.(3) 2차 뷰 선택
			next = "detail?prodCode=" + prodCode;
			
		} catch (NotFoundException e) {
			// (5) 수정 실패 메시지 발생
			message = e.getMessage();
			
			// 3.(3) 수정 실패 시 2차 뷰
			next = "list";
			e.printStackTrace();
		}
		
		// (6) 메시지 request에 속성으로 추가
		request.setAttribute("message", message);

		// 3. view 선택
		// (2) 수정에 실패 / 성공 모두 messageJsp로 전송
		view = "messageJsp";
		
		// (4) 2차 뷰 request 에 속성 추가
		request.setAttribute("next", next);
		
		RequestDispatcher reqd;
		reqd = request.getRequestDispatcher(view);
		
		reqd.forward(request, response);
		
		
	}

}
