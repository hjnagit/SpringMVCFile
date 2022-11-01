package com.itwillbs.web;

import java.sql.Connection;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

//@RunWith(SpringJUnit4ClassRunner.class)
// -> 이 클래스를 스프링모드로 테스트 하겠습니다.

//@ContextConfiguration(
//		locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml" }
//		)
// => 프로젝트 실행할 때 사용할 설정, 위치 : ~~~root-context.xml 파일등록



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml" }
		)
public class DataSourceTest {
	// DataSource 객체 생성여부 확인

	private static final Logger log 
	     = LoggerFactory.getLogger(DataSourceTest.class);
	
	// DataSource 객체 생성 (직접생성 - 강한결합 => 주입)
	
	// @Inject : Spring에 있는 객체(빈)을 가져와서 주입 하겠다.
	//           객체 직접 생성X, 의존관계 주입
	//          => DI (Dependency Injection)
	
	//  @Autowired
	//	@Resource
	@Inject
	private DataSource ds; 
	
	@Test
	public void DataSource있는지() {
		// DataSource 객체가 필요 => 의존하고 있다(의존관계)
		log.info(ds.toString());
		log.info(ds+"");
	}
	
	@Test
	public void 디비연결되는지() {
		// 디비연결 체크
		try {
			Connection con = ds.getConnection();
			
			if( con != null) {
				log.info(" 디비연결 성공!!! ");
				log.info(con+"");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
}
