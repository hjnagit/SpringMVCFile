package com.itwillbs.service;

import com.itwillbs.domain.MemberVO;

public interface MemberService {
	// 회원가입
	public void memberJoin(MemberVO vo);
	
	// 로그인 체크
	public MemberVO memberLogin(MemberVO vo);
	//public MemberVO memberLogin(String userid,String userpw);
	
	// 회원정보 조회
	public MemberVO memberGet(String userid);
	
	
}
