package com.itwillbs.web;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.itwillbs.domain.MemberVO;
import com.itwillbs.service.MemberService;

//@RequestMapping("/member/*") => 컨트롤러를 구분하는 매핑주소

@Controller
@RequestMapping("/member/*")
public class MemberController {
	
	private static final Logger log
	     = LoggerFactory.getLogger(MemberController.class);
	
	// 서비스 객체 주입(DI)
//	@Inject
	@Autowired
	private MemberService service;
	
	// http://localhost:8080/web/member/test (x)
	// http://localhost:8080/member/test (o)
	//	@RequestMapping("/test")
	//	public void TestMember() {
	//		log.info(" MemberController 실행!!! ");
	//	}
	
	// http://localhost:8080/member/insert
	
	// 회원가입 GET (조회,입력) - /member/insert
	@RequestMapping(value = "/insert",method = RequestMethod.GET)
	public void insertGET() {
		log.info(" insertGET() 호출 ");
		log.info(" 연결된 view페이지 출력 ");
	}
	
	// 회원가입 POST (처리)
	//@PostMapping()
	@RequestMapping(value = "/insert",method = RequestMethod.POST)
	public String insertPOST(MemberVO vo) throws Exception {
		log.info(" insertPOST() 호출 ");
		
		// 한글처리
		//request.setCharacterEncoding("UTF-8");
		// 전달정보 저장(userid,userpw,username,useremail)
		// MemberVO 객체에 저장
		//		MemberVO vo = new MemberVO();
		//		vo.setUserid(request.getParameter("userid"));
		//		vo.setUserpw(request.getParameter("userpw"));
		//		vo.setUsername(request.getParameter("username"));
		//		vo.setUseremail(request.getParameter("useremail"));
		log.info(vo+"");
		
		// 회원가입 -> 서비스 -> DB에 저장(DAO 객체 생성)		
		// MemberDAO dao = new MemberDAOImpl();(x)
	    // MemberService service = new MemberServiceImpl();(x)
		service.memberJoin(vo);
		log.info(" 회원가입 성공! ");
		
		// 페이지 이동 (로그인페이지)
		
		//return "/member/login";(x)
		return "redirect:/member/login";
	}
	
	
	// http://localhost:8080/member/login
	
	// 로그인GET
	@RequestMapping(value = "/login",method = RequestMethod.GET )
	public String loginGET() {
		log.info(" loginGET() 실행 ");
		log.info(" 연결된 뷰 페이지로 이동 ");
		
		//return "memberLogin"; (x)
		return "/member/memberLogin";
	}
	
	// 로그인POST
	@RequestMapping(value = "/login",method = RequestMethod.POST)
	public String loginPOST(MemberVO vo,HttpSession session/*,@ModelAttribute("userpass") String userpass*/) {
		log.info(" loginPOST()  호출 ");
		
		// 한글처리 => 필터사용(생략)
		// 전달 정보 저장( 파라미터 - userid,userpw )
		
		//log.info("userpass : "+userpass);
		log.info("vo : "+vo);
		// DB에서 확인 ( 컨트롤러 -> 서비스 -> DAO )
		MemberVO loginVO = service.memberLogin(vo);
		
		log.info("loginVO : "+loginVO);
		
		// 로그인 여부 확인
		if(loginVO != null) {
			// 성공 -> 메인페이지 이동, 로그인정보를 저장(세션)
			
			// JSP(view)에서 session 정보를 가져와서 사용
			session.setAttribute("loginVO",loginVO);
			return "redirect:/member/main";
		}else {
			// 실패 -> 로그인페이지 이동
			return "redirect:/member/login";
		}
	}
	
	// 메인페이지 GET    http://localhost:8080/member/main
	@RequestMapping(value = "/main",method = RequestMethod.GET )
	public void mainGET() {
		log.info(" mainGET() 호출 ");
		log.info(" void 리턴 : /member/main.jsp 뷰 호출 ");
	}
	
	// 로그아웃 GET/POST
	@RequestMapping(value = "/logout",method = RequestMethod.GET)
	public String logoutGET(HttpSession session) {
		// 로그아웃 -> 세션초기화
		session.invalidate();
		log.info(" 세션 초기화 완료 => 로그아웃 ");
		
		// 페이지 이동	
		return "redirect:/member/main";
	}
	
	// 회원정보 조회 GET
	@RequestMapping(value = "/info",method = RequestMethod.GET )
	public void infoGET(HttpSession session,Model model) {
		log.info(" infoGET() 호출 ");
		// main페이지(session) -> ID정보 -> info페이지
		MemberVO vo = (MemberVO) session.getAttribute("loginVO");
		//vo.getUserid();
		log.info(" ID : "+vo.getUserid());
		
		// 서비스 사용 -> DB정보를 가져오기
		MemberVO userVO = service.memberGet(vo.getUserid());
		log.info("@@@@@@ userVO : "+userVO);
		
		// 전달정보를 Model객체에 저장 / view 출력	
		model.addAttribute("userVO", userVO);
		
	}
	
	
	
	
	
	
}// class
