<%@page import="kr.or.ddit.lprod.vo.LprodVO"%>
<%@page import="java.util.List"%>
<%@page import="kr.or.ddit.lprod.service.LprodServiceImpl"%>
<%@page import="kr.or.ddit.lprod.service.ILprodService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
[
    <%
    	//여기는 controller - 자바 코드를 이용해서 crud를 처리
    	//처리된 결과로 json형태의 응답 데이터를 생성
    	
    	
    	//service객체가 필요
    	ILprodService service = LprodServiceImpl.getInstance();
    
    	List<LprodVO> list = service.getAllLprod();
    	
    	for(int i=0; i<list.size();i++){
    		LprodVO vo = list.get(i);
    		if(i > 0) out.print(",");
    %>
    	{
    		"id"   : "<%= vo.getLprod_id() %>",
    		"gu"   : "<%= vo.getLprod_gu() %>",
    		"nm"   : "<%= vo.getLprod_nm() %>"
    	}
    <%	
    	}
    	
    
    %>
    
]