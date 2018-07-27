package shop.exception;

import shop.vo.Product;

/**
 * 수정, 삭제 작업 중 대상 제품이 없는 경우
 * 발생시킬 예외를 정의한 클래스
 * 
 *  @author PC38219
 *
 */
public class NotFoundException extends Exception {

	public NotFoundException() {
		super("제품 정보가 존재하지 않습니다.");
	}
	
	public NotFoundException(String type, Product product) {
		super(String.format("%s:[%s] 제품 정보가 존재하지 않습니다.", type, product.getProdCode()));
	}
}
