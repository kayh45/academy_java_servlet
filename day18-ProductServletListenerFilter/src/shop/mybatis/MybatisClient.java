package shop.mybatis;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * Mybatis 의 SqlSessionFactory 객체를
 * 싱글턴으로 생성하고 관리하는 클래스
 * 
 * @author PC38219
 *
 */
public class MybatisClient {

	// 싱글턴으로 관리할 타입의 static 변수를 선언
	public static SqlSessionFactory factory;
	
	// SqlSessionFactory 객체를 초기화
	// 이 클래스 생성자에서 진행
	// mybatis-config.xml 파일을 InputStream 으로 읽어들여
	// 초기화 함
	private MybatisClient() {
		String resource = "mybatis-config.xml";
		InputStream in = null;
		
		try {
			in = Resources.getResourceAsStream(resource);
			factory = new SqlSessionFactoryBuilder().build(in);
		
		} catch (IOException e) {
			System.err.println("mybatis 설정 읽기 오류!!");
			e.printStackTrace();
		}
	} // 생성자 종료
	
	// 싱글턴으로 관리할 타입을 리턴하는 static 메소드 선언
	public static SqlSessionFactory getFactory() {
		
		if (factory == null) {
			new MybatisClient();
		}
		
		return factory;
		
	}
	
	
	
}
