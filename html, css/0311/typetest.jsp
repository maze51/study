<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>jsp : java server page</h1> <br>
	
	<div>
	클라이언트에서 form양식을 제출/전송하면 <br>
	form양식에 입력된 데이터를 받아서 처리하는 역할이다.<br>
	자바 코드와 html 코드를 혼용해서 사용할 수 있다.<br>
	자바 코드는 &lt;% %&gt; 사이에 기술한다.<br>
	
	</div>
	
	<%
	//자바코드를 기술하는 영역
	//클라이언트가 입력한 데이터를 여기서 가져오는 코드가 필요
	//클라이언트 form의 name속성을 이용
	String userId = request.getParameter("id");
	String userName = request.getParameter("name");
	
	String userPrice = request.getParameter("price");
	String userGender = request.getParameter("gender");
	
	out.print(userId+"<br>"); // jsp에는 System이 내장되어 있다
	out.print(userName+"<br>");
	out.print(userPrice+"<br>");
	out.print(userGender+"<br>");
		
	%>
</body>
</html>