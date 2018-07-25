package shop.dao;

import java.util.List;

import shop.exception.DuplicateException;
import shop.exception.NotFoundException;
import shop.vo.Product;

public interface GeneralWarehouse {
	
	public int add(Product product) throws DuplicateException;
	public Product get(Product product) throws NotFoundException;
	public int set(Product product) throws NotFoundException;
	public int remove(Product product) throws NotFoundException;
	public List<Product> getAllProducts();

}
