package shop.dao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import shop.exception.DuplicateException;
import shop.exception.NotFoundException;
import shop.vo.Product;

public class SetWarehouse implements GeneralWarehouse {

	// 멤버 변수 선언부
	private Set<Product> products;
	
	// 생성자 선언부
	public SetWarehouse() {
		products = new HashSet<Product>();
	}
	
	public SetWarehouse(Set<Product> products) {
		super();
		this.products = products;
	}
	 
	// 메소드 선언부
	@Override
	public int add(Product product) throws DuplicateException {
		int addCnt = 0;
		boolean isContain = isContain(product);
		
		if (!isContain) {
			boolean success = products.add(product);
			if (success) {
				addCnt++;
			}
		} else {
			throw new DuplicateException("add", product);
		}
		
		return addCnt;
	}

	@Override
	public Product get(Product product) throws NotFoundException {
		Product found = null;

		for (Product prod : products) {
			// Product 클래스에서 equals를 재정의 했기때문에
			// prodCode만 같으면 같은 객체로 인식
			if (prod.equals(product)) {
				found = prod; 
				break;
			} else {
				throw new NotFoundException("get", product);
			}
		}
		
		return found;
	}

	@Override
	public int set(Product product) throws NotFoundException {
		int setResult = -1;
		
		if (isContain(product)) {
			products.remove(product);
			products.add(product);
			setResult = 1;
		} else {
			throw new NotFoundException("set", product);
		}
		
		return setResult;
	}

	@Override
	public int remove(Product product) throws NotFoundException {
		int rmResult = -1;
		
		if (isContain(product)) {
			products.remove(product);
			rmResult = 1;
		} else {
			throw new NotFoundException("remove", product);
		}
		
		return rmResult;
	}

	@Override
	public List<Product> getAllProducts() {
		List<Product> prodList = new ArrayList<Product>();
		
		for (Product product : products) {
			prodList.add(product);
		}
		
		return prodList;
	}
	
	/**
	 * 제품 Set에 수정하거나 삭제할 제품이 있으면
	 * true를 리턴하는 메소드
	 * 
	 * @param product
	 * @return
	 */
	private boolean isContain(Product product) {
		return products.contains(product);
	}
	

}
