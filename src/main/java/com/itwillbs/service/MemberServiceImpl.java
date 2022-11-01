package com.itwillbs.service;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.itwillbs.domain.MemberVO;
import com.itwillbs.persistence.MemberDAO;

// @Service : 스프링(root-context.xml)에서 해당파일을 서비스로 인식

@Service
public class MemberServiceImpl implements MemberService{

	private static final Logger log = LoggerFactory.getLogger(MemberServiceImpl.class);
	
	// MemberDAO객체를 주입(DI) - 객체를 직접생성 X,만들어진거 사용하기 (root-context.xml)
	@Inject
	private MemberDAO dao;
	
	@Override
	public void memberJoin(MemberVO vo) {
		log.info("컨트롤러 -> 서비스(Impl)");
		log.info("MemberServiceImpl-memberJoin() 호출");
		log.info(" 서비스(Impl) -> DAO(Impl) ");
		// DAO 객체 생성 - 메서드 호출
		dao.insertMember(vo);
		
		log.info(" DAO 동작 완료!! 서비스 -> 컨트롤러 ");		
	}

	@Override
	public MemberVO memberLogin(MemberVO vo) {
		
		// 컨트롤러 -> 로그인정보(vo[id,pw]) -> 서비스
		log.info("memberLogin(MemberVO vo)  호출");
		// 서비스 -> 로그인정보(vo[id,pw])  -> DAO
		log.info(" DAO 로그인메서드 호출 ! ");
		 
		MemberVO loginVO = dao.loginMember(vo);
		log.info(" loginVO : "+loginVO);
		
		return loginVO;
		
		//return dao.loginMember(vo);
	}

	@Override
	public MemberVO memberGet(String userid) {
		log.info(" memberGet(userid) 호출 ");
		// 주입 객체 사용 - 메서드 호출
		
		return dao.getMember(userid);
	}
	
	
	
	
	
	
	

}
