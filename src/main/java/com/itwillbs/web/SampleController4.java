package com.itwillbs.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class SampleController4 {

	private static final Logger log 
	    = LoggerFactory.getLogger(SampleController4.class);
	
	// http://localhost:8080/web/doE
	@RequestMapping("/doE")
	public String doE(Model model ,RedirectAttributes rttr ) {
		log.info(" /doE 호출 ");

		//return "/doF";
		// -> sendRedirect 동작 수행가능 (주소,화면 모두 변경)
		//return "redirect:/doF";
		
		//return "redirect:/doF?msg=itwill"; 

//		model.addAttribute("msg", "busan"); //2
		rttr.addFlashAttribute("msg", "spring"); //3
		
		/*
		 * model.addAttribute()
		 * => 전달값이 URI표시O, F5실행시 데이터 유지O (영구적)
		 * rttr.addFlashAttribute()
		 * => 전달값이 URI표시X, F5실행시 데이터 유지X (일시적)
		 * */
		
		
		
		return "redirect:/doF";
		
		
//		return "forward:/doF";
		// -> forward 동작 수행가능 (주소 변경x, 화면 변경o)
	}
	
	@RequestMapping("/doF")
	public void doF(@ModelAttribute("msg") String msg) {
		log.info(" /doF 호출 ");
		log.info(" msg :"+msg);
		
	}
	
	
	
}
