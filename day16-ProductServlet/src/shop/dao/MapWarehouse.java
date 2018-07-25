package shop.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import shop.exception.DuplicateException;
import shop.exception.NotFoundException;
import shop.vo.Product;

public class MapWarehouse implements GeneralWarehouse {

	// 멤버변수 선언부
	private Map<String, Product> products;
	
	// 생성자 선언부	
	public MapWarehouse() {
		
		products = new HashMap<String, Product>();
	}
	
	public MapWarehouse(Map<String, Product> products) {
		super();
		this.products = products;
	}

	// 메소드 선언부
	@Override
	public int add(Product product) throws DuplicateException {
		int addResult = -1;
		
		if (!products.containsValue(product)) {
			products.put(product.getProdCode(), product);
			addResult = 1;
		} else {
			throw new DuplicateException("add", product);
		}
			
		return addResult;
	}

	@Override
	public Product get(Product product) throws NotFoundException {
		Product found = null;
		
		if (products.containsValue(product)) {
			found = products.get(product.getProdCode());
		} else {
			throw new NotFoundException("get", product);
		}
		return found;
	}

	@Override
	public int set(Product product) throws NotFoundException {
		int setResult = -1;
		
		if (products.containsValue(product)) {			
			products.put(product.getProdCode(), product);
			setResult = 1;
		} else {
			// 수정할 제품이 존재하지 않음
			throw new NotFoundException("set", product);
		}
		
		return setResult;
	}

	@Override
	public int remove(Product product) throws NotFoundException {
		int rmResult = -1;
		Product removed = products.remove(product.getProdCode());
		
		if (removed != null) {
			rmResult = 1;
		} else {
			throw new NotFoundException("remove", product);
		}
		return rmResult;
	}

	@Override
	public List<Product> getAllProducts() {
		Set<String> ketSet = products.keySet();
		List<Product> prodList = new ArrayList<Product>();
		
		for (String key : ketSet) {
			prodList.add(products.get(key));
		}
		
		return prodList;
	}

}
