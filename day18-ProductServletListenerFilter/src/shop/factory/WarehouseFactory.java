package shop.factory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import shop.dao.ArrayWarehouse;
import shop.dao.GeneralWarehouse;
import shop.dao.JdbcWarehouse;
import shop.dao.ListWarehouse;
import shop.dao.MapWarehouse;
import shop.dao.MybatisWarehouse;
import shop.dao.SetWarehouse;
import shop.vo.Product;

/**
 * 창고 객체 (GeneralWarehouse 타입) 생성을
 * 전문적으로 수행하는 팩토리 클래스
 *  
 * @author PC38219
 *
 */
public class WarehouseFactory {

	public static GeneralWarehouse getWarehouse(String type) {
		GeneralWarehouse warehouse = null;
		
		if ("array".equals(type)) {
			Product[] products = new Product[0];
			warehouse = new ArrayWarehouse(products);
			
		} else if ("list".equals(type)) {
			List<Product> products = new ArrayList<Product>();
			warehouse = new ListWarehouse(products);
			
		} else if ("set".equals(type)) {
			Set<Product> products = new HashSet<Product>();
			warehouse = new SetWarehouse(products);
			
		} else if ("map".equals(type)) {
			Map<String, Product> products = new HashMap<String, Product>();
			warehouse = new MapWarehouse(products);
			
		} else if ("jdbc".equals(type)) {
			warehouse = new JdbcWarehouse();
			
		} else if ("mybatis".equals(type)) {
			warehouse = new MybatisWarehouse();
		}
		
		return warehouse;
	}
	
}
