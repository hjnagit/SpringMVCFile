package com.itwillbs.web;

import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.itwillbs.domain.MemberVO;
import com.itwillbs.persistence.MemberDAO;
import com.itwillbs.persistence.MemberDAOImpl;

// @RunWith(SpringJUnit4ClassRunner.class)
// => 해당파일을 스프링(Junit)을 사용해서 테스트 하도록 설정
//@ContextConfiguration(
//		locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"}
//		)
// => 스프링 테스트 할때 필요한 설정을 위 경로에서 가져다가 사용하겠다.



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"}
		)
public class MemberDAOTest {
	// DAO의 동작이 정상적으로 실행되는지 테스트 하는 파일


	// DAO 객체 생성(x) -> 객체 주입
	// MemberDAO dao = new MemberDAO();(인터페이스 객체생성x)
	// MemberDAOImpl daoImpl = new MemberDAOImpl(); (사용가능-강한결합)
	// MemberDAO dao = new MemberDAOImpl(); (사용가능-중간결합(인터페이스-업캐스팅))
	@Inject
	private MemberDAO dao;
	

	private static final Logger log
	  = LoggerFactory.getLogger(MemberDAOTest.class);
	
	//@Test
	public void daoTest() {
		log.info(" @@@@@ dao : "+dao);
	}
	
	//@Test
	public void 디비시간정보_조회() {
		
		log.info(" @@@@@ 시간정보 : "+ dao.getTime() );
		
	}
	
	//@Test
	public void 회원가입테스트() {
		
		log.info(" @@@@@ 회원가입테스트() 호출 ");
		log.info(" @@@@@ 테스트 -> DAOImpl ");
		
		MemberVO vo = new MemberVO();
		vo.setUserid("itwill04");
		vo.setUserpw("1234");
		vo.setUsername("사용자4");
		vo.setUseremail("itwill04@itwill.com");
		
		dao.insertMember(vo);
		
	}
	
	
	
	// 로그인 테스트
	//@Test
	public void 로그인로직_테스트() {
		log.info(" 로그인 체크 (입력받은 정보를 DB값과 비교)");
		
		MemberVO vo = new MemberVO();
		vo.setUserid("admin");
		vo.setUserpw("1234");
		
		//MemberVO resultVO = dao.loginMember(vo);
		MemberVO resultVO = dao.loginMember(vo.getUserid(),vo.getUserpw());
		
		if( resultVO == null ) {
			log.info(" 회원정보 없음, 로그인 실패! ");
		}else {
			log.info(" 회원정보 있음, 로그인 성공! ");
			log.info(resultVO+"");
		}
		
	}
	
	// 회원정보 조회(아이디만 사용)
	//@Test
	public void 회원정보_조회() {
		
		// dao 객체 생성(=> 객체 주입)
		// 회원정보 조회 메서드 호출
		MemberVO vo = dao.getMember("admin");

		if( vo != null ) {
			// 확인(출력)
			log.info("아이디 :  "+vo.getUserid() );
			log.info("비밀번호 :  "+vo.getUserpw() );
			log.info("이름 :  "+vo.getUsername() );
			log.info("이메일 :  "+vo.getUseremail());
			log.info("가입일 :  "+vo.getRegdate() );
			log.info("정보수정일 :  "+vo.getUpdatedate() );
		}
	}
	
	// 회원정보 수정 - 아이디,비밀번호 같을때 이메일 수정(+updatedate 수정)
	//@Test
	public void 회원정보_수정() {
		log.info(" 회원정보 수정 (테스트->DAOImpl)");
		
		MemberVO uvo = new MemberVO();
		uvo.setUserid("admin"); // 기존 ID
		uvo.setUserpw("1234444"); // 기존 PW
		uvo.setUseremail("Uadmin@admin.com"); // 수정할 이메일
		
		int result = dao.updateMember(uvo);
		
		if(result == 1) {
			log.info(" 회원정보 수정성공!!! ");
		}else { //result == 0
			log.info(" 회원정보 수정실패!!! ");
		}
		
	}
	
	// 회원정보 삭제
	@Test
	public void 회원정보_삭제() {
		
		MemberVO dvo = new MemberVO();
		dvo.setUserid("admin");
		dvo.setUserpw("1234");
		
		int result = dao.deleteMember(dvo);
		
		if(result == 1) {
			log.info(" 삭제 완료 !!! ");
		}else {
			log.info(" 삭제 실패 !!! ");
		}
	}
	
	
	// 회원목록(리스트) 조회
	@Test
	public void 회원목록리스트_조회() {
		  
		// DAO 회원목록 리스트 동작 호출
		List<MemberVO> memberList = dao.getMemberList();
		
		for(MemberVO vo :memberList) {
			log.info(" 아이디 : "+vo.getUserid()+", 이메일 : "+vo.getUseremail());
		}
		
	}
	
	
	
	
	
	
	

}
