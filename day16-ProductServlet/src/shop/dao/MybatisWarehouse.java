package shop.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import shop.exception.DuplicateException;
import shop.exception.NotFoundException;
import shop.mapper.ProductMapper;
import shop.mybatis.MybatisClient;
import shop.vo.Product;

public class MybatisWarehouse implements GeneralWarehouse {

	private SqlSessionFactory factory;
	
	public MybatisWarehouse() {
		factory = MybatisClient.getFactory();
	}
	
	@Override
	public int add(Product product) throws DuplicateException {
		// 추가할 대상 제품이 있는지 선 조회
		if (isExists(product)) {
			throw new DuplicateException("추가", product);
		}
		int insCnt = 0;

		// 1. SqlSession 얻기 : DML 작업은 auto-commit을 활성화
		SqlSession session = factory.openSession(true);
		
		// 2. Mapper 인터페이스 객체를 session에서 얻기
		ProductMapper mapper;
		mapper = session.getMapper(ProductMapper.class);
		
		try {
			// 3. mapper를 통하여 삭제 진행
			insCnt = mapper.insert(product);
		} finally {
			if (session != null) {
				session.close();
			}
		
		}
		
		return insCnt;
	}

	@Override
	public Product get(Product product) throws NotFoundException {
		// 조회할 대상 제품이 있는지 선 조회
		if (!isExists(product)) {
			throw new NotFoundException("수정", product);
		}
		Product found = null;
		// 1. SqlSession 얻기 : DML 작업은 auto-commit을 활성화
		SqlSession session = factory.openSession(false);
		
		// 2. Mapper 인터페이스 객체를 session에서 얻기
		ProductMapper mapper;
		mapper = session.getMapper(ProductMapper.class);
		
		try {
			// 3. mapper를 통하여 삭제 진행
			found = mapper.selectOne(product);
			
		} finally {
			if (session != null) {
				session.close();
			}
		
		}
		
		return found;
	}

	@Override
	public int set(Product product) throws NotFoundException {
		// 수정할 대상 제품이 있는지 선 조회
		if (!isExists(product)) {
			throw new NotFoundException("수정", product);
		}
		int setCnt = 0;

		// 1. SqlSession 얻기 : DML 작업은 auto-commit을 활성화
		SqlSession session = factory.openSession(true);
		
		// 2. Mapper 인터페이스 객체를 session에서 얻기
		ProductMapper mapper;
		mapper = session.getMapper(ProductMapper.class);
		
		try {
			// 3. mapper를 통하여 삭제 진행
			setCnt = mapper.update(product);
		} finally {
			if (session != null) {
				session.close();
			}
		
		}
		
		return setCnt;
	}

	@Override
	public int remove(Product product) throws NotFoundException {
		// 삭제할 대상 제품이 있는지 선 조회
		if (product != null && !isExists(product)) {
			throw new NotFoundException("삭제", product);
		}
		int rmCnt = 0;

		// 1. SqlSession 얻기 : DML 작업은 auto-commit을 활성화
		SqlSession session = factory.openSession(true);
		
		// 2. Mapper 인터페이스 객체를 session에서 얻기
		ProductMapper mapper;
		mapper = session.getMapper(ProductMapper.class);
		
		try {
			// 3. mapper를 통하여 삭제 진행
			rmCnt = mapper.delete(product);
		} finally {
			if (session != null) {
				session.close();
			}
		
		}
		
		return rmCnt;
	}

	@Override
	public List<Product> getAllProducts() {

		// 1. SqlSession 얻기
		SqlSession session = factory.openSession();
		
		// 2. mapper 인터페이스 객체를 session에서 얻기
		ProductMapper mapper;
		mapper = session.getMapper(ProductMapper.class);
		
		// 3. mapper 를 통하여 조회
		List<Product> products = null;
		
		try {
			products = mapper.selectAll();
			
		} finally {
			// 4. session 닫기
			if (session != null) {
				session.close();
			}
			
		} 
		
		return products;
	}
	
	// isExists 지원 메소드 작성
	private boolean isExists(Product product) {
		boolean isExists = false;
		
		// 1. SqlSession 얻기
		SqlSession session = factory.openSession();
		
		// 2. Mapper 인터페이스 객체를 session에서 얻기
		ProductMapper mapper;
		mapper = session.getMapper(ProductMapper.class);
		
		// 3. mapper 객체에 메소드 호출로 쿼리 실행
		try {
			String prodCode = mapper.isExists(product);
			
			if (prodCode != null) {
				isExists = true;
			}
			
		} finally {
			// 4. session 닫기
			if (session != null) {
				session.close();
			}
			
		}
		return isExists;
	}

}
