package com.itwillbs.web;

import java.io.File;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Controller
public class FileController {
	private static final Logger log 
	= LoggerFactory.getLogger(FileController.class);
	
	
	//http://localhost:8080/upload
	
	@RequestMapping(value = "/upload",method = RequestMethod.GET)
	public String fileUPloadGET() {
		log.info("fileUPloadGET() 호출 ");
		return "uploadForm";
	}
	
	@RequestMapping(value = "/upload",method = RequestMethod.POST)
	public String fileUPloadPOST(MultipartHttpServletRequest multi) throws Exception {
		log.info("fileUPloadPOST() 호출 ");
		
		//파일의 정보를 저장하는 Map
		Map map = new HashMap();
		//log.info(multi + "");
		
		//객체덩어리로? Enumeration 형태로 받아오는
		Enumeration enu = multi.getParameterNames(); //파일 정보 x
		//log.info(enu+"");
		
		while(enu.hasMoreElements()) {
			String name = (String)enu.nextElement();
			//log.info("name : " + name);
			String value = multi.getParameter(name);
			map.put(name, value);
		}
		
		log.info(map+"");
		//전달정보 파라미터값을 map 저장
		////////////////////////////////////////////////////////////////////////////////
		//업로드 파일 처리
		fileProcess(multi);
		
		
		
		
		
		///////////////////////////////////////////////////////////////////////////////
		
		return "result";
	}//fileUPloadPOST
	
	//전달된 파일 전용 처리 메서드
	public List<String> fileProcess(MultipartHttpServletRequest multi)throws Exception{
		log.info("첨부파일 처리 시작");
		// 파일 정보를 저장하는 리스트(리턴)
		List<String> fileList = new ArrayList<String>();
		
		//전달된 파일정보를 받아서 처리
		Iterator<String> fileNames = multi.getFileNames();
		//log.info(fileNames + "");
		while(fileNames.hasNext()) {
			String fileName = fileNames.next(); //파일의 파라미터명
			//log.info("fileName : " + fileName);
			
			MultipartFile mFile = multi.getFile(fileName); //업로드된 파일 정보를 가져오기
			String oFileName = mFile.getOriginalFilename(); //진짜 파일명을 가져온다
			//log.info("oFileName : " + oFileName);
			
			//업로드 될 파일의 이름들을 저장
			fileList.add(oFileName);
			//log.info(fileList + "");
			
			//파일 업로드//////
			//파일 생성
			File file = new File("C:\\spring"+"\\"+oFileName); //실제 파일 경로 넣었음 pathname
			if(mFile.getSize() != 0) { //첨부파일이 있을 때
				file.createNewFile(); //첨부파일 업로드(파일 생성)
				log.info("파일 업로드 성공"); //이렇게하면지정한 곳에 파일이 올라와있다
			}
			
			
		}//while
		
		
		log.info("첨부파일 처리 끝");
		return fileList;
	}
	
}
