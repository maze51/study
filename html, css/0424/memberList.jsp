<%@page import="kr.or.ddit.member.vo.MemberVO"%>
<%@page import="java.util.List"%>
<%@page import="kr.or.ddit.member.service.MemberServiceImpl"%>
<%@page import="kr.or.ddit.member.service.IMemberService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
[
    <%
    	//여기는 controller - 자바 코드를 이용해서 crud를 처리
    	//처리된 결과로 json형태의 응답 데이터를 생성
    	
    	
    	//service객체가 필요
    	IMemberService service = MemberServiceImpl.getInstance();
    
    	List<MemberVO> list = service.getAllMember();
    	
    	for(int i=0; i<list.size();i++){
    		MemberVO vo = list.get(i);
    		if(i > 0) out.print(",");
    %>
    	{
    		"id"   : "<%= vo.getMem_id() %>",
    		"name" : "<%= vo.getMem_name() %>",
    		"mail" : "<%= vo.getMem_mail() %>",
    		"hp"   : "<%= vo.getMem_hp() %>"
    	}
    <%	
    	}
    	
    
    %>
    
]