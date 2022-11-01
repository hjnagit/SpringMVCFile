<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.1/dist/jquery.min.js"></script>
<script type="text/javascript">
	var cnt = 1;

	function addFile(){
		  //버튼 클릭시 동작 -> 파일 입력창 div에 추가
		  //alert("클릭");
		  $("#div_file").append('<br><input type="file" name="file'+cnt+'" />');
		  cnt++;
	}
	
	$(document).ready(function(){
		//alert(" JQuery 준비 ");
	  
	});

</script>
</head>
<body>
  <h1>uploadForm.jsp</h1>
  
  <fieldset>
	  <form action="./upload" method="post" enctype="multipart/form-data">
	     아이디 : <input type="text" name="id"><br>
	     이메일 : <input type="text" name="email"><br>
	     
	     <hr>
	     <!-- <input type="file" name="file"> -->
	     <input type="button" value="파일 업로드 추가" onclick="addFile();"><br>
	     
	     <div id="div_file">
	     	
	     
	     </div>
	  
	  	<input type="submit" value="업로드">
	  </form>
  </fieldset>
  
  
  
  
  
</body>
</html>