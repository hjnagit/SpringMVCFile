<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
   <h1>WEB-INF/views/itwill.jsp</h1>
   
   JSP : <%=request.getParameter("msg") %> <br>
   EL(param) : ${param.msg } <br>
   EL : ${msg } <br>
   <hr>
   
   

</body>
</html>