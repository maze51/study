<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
	div{
		width : 50%;
		height : auto;
		padding : 20px;
		background : lightgreen;
	}
</style>
</head>
<body>
 jsp : java server page <br>
 클라이언트에서 전송하면 필요한 데이터를 받아서 처리하는 페이지 <br>
 서블릿 클래스를 기반으로 하는 script다 <br>
 html/script코드와 java코드를 기술할 수 있다 <br>
 자바 코드를 기술할 때는 &lt;% %&gt; 기호 사이에 문장을 기술한다 <br>
 
 <%
 	String id = request.getParameter("id");
 	String pass = request.getParameter("pass");
 %>
 
 <div>
 <%= id %> 님 환영합니다<br>
 즐거운 하루 되세요
 </div>
</body>
</html>