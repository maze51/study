<%@page import="kr.or.ddit.buyer.vo.BuyerVO"%>
<%@page import="java.util.List"%>
<%@page import="kr.or.ddit.buyer.service.BuyerServiceImpl"%>
<%@page import="kr.or.ddit.buyer.service.IBuyerService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
[
	<%
		IBuyerService service = BuyerServiceImpl.getInstance();
	
		List<BuyerVO> list = service.getNameList();
		
		for(int i=0;i<list.size();i++){
			BuyerVO vo = list.get(i);
			if(i > 0) out.print(",");
	%>
		<%-- json 데이터 생성하기. 클라이언트로 보내진다 --%>
		{
			"id"   : "<%= vo.getBuyer_id() %>",
		    "name" : "<%= vo.getBuyer_name() %>"
		}
	<% 
		}
	%>

]