<%@page import="kr.or.ddit.member.service.MemberServiceImpl"%>
<%@page import="kr.or.ddit.member.service.IMemberService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    String idvalue = request.getParameter("id");
    
	//service객체 얻어오기
	IMemberService service = MemberServiceImpl.getInstance();
	
	//service메서드 호출 결과값을 받기
	String result = service.getMember(idvalue);
	
	//String result의 결과는 null또는 a001~x001
	//결과 result를 가지고 json데이터 생성
	
	if(result == null){
		// 사용 가능 아이디
%>		
	{
		"sw" : "OK",
		"id" : "<%= idvalue %>"
	}

<%	
	} else {
		// 사용 불가능 아이디
%>
	{
		"sw" : "NO",
		"id" : "<%= idvalue %>"
	}

<%
	}
	
%>  
