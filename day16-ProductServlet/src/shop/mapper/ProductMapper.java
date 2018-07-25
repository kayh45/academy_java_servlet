package shop.mapper;

import java.util.List;

import shop.vo.Product;

public interface ProductMapper {

	int insert(Product product);
	Product selectOne(Product product);
	int update(Product product);
	int delete(Product product);
	List<Product> selectAll();
	String isExists(Product product);
	
}
