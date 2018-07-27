package shop.dao;

import java.util.ArrayList;
import java.util.List;

import shop.exception.DuplicateException;
import shop.exception.NotFoundException;
import shop.vo.Product;

/**
 * 제품 정보를 저장할 자료구조를 리스트로 관리하는 창고 클래스
 * 
 * @author PC38219
 *
 */

public class ListWarehouse implements GeneralWarehouse {

	private List<Product> products;
	
	public ListWarehouse() {
		products = new ArrayList<Product>();
	}
	
	public ListWarehouse(List<Product> products) {
		super();
		this.products = products;
	}

	@Override
	public int add(Product product) throws DuplicateException {
		int addCnt = 0;
		int getIndex = findProductIdx(product);
		
		if (getIndex == -1) {
			products.add(product);
				addCnt++;
		} else {
			throw new DuplicateException("add", product);
		}
		
		return addCnt;
	}

	@Override
	public Product get(Product product) throws NotFoundException {
		int getIndex = findProductIdx(product);
		Product finded = null;
		
		if (getIndex > -1) {
			// 찾아올 제품이 존재
			finded = products.get(getIndex);
		} else {
			throw new NotFoundException("get", product);
		}
		
		return finded;
	}

	@Override
	public int set(Product product) throws NotFoundException {
		int setIdx = findProductIdx(product);
		
		if (setIdx > -1) {
			products.set(setIdx, product);
		} else {
			throw new NotFoundException("set", product);
		}
		
		return setIdx;
		
	}

	@Override
	public int remove(Product product) throws NotFoundException {
		int rmvIdx = findProductIdx(product);
		
		if (rmvIdx > -1) {
			products.remove(rmvIdx);
		} else {
			throw new NotFoundException("remove", product);
		}
		
		return rmvIdx;
		
	}

	@Override
	public List<Product> getAllProducts() {
		return products;
	}
	
	// 리스트 안에 찾으려는 제품의 인덱스를 구하는 지원 메소드
	private int findProductIdx(Product product) {
		int index = -1;
		
		for (int idx = 0; idx < products.size(); idx++) {
			if (products.get(idx).equals(product)) {
				index = idx;
				break;
			}
		}
		
		return index;
	}

}
