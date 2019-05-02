<%@page import="kr.or.ddit.prod.vo.ProdVO"%>
<%@page import="java.util.List"%>
<%@page import="kr.or.ddit.prod.service.ProdServiceImpl"%>
<%@page import="kr.or.ddit.prod.service.IProdService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
 
	<%
		//클라이언트의 요청으로부터 gu값을 받는다
		String lprod_gu = request.getParameter("gu");
		
		//service객체 얻어오기
		IProdService service = ProdServiceImpl.getInstance();
		
		//service메서드 호출해서 List객체로 리턴받기
		List<ProdVO> list = service.getProdName(lprod_gu);
		
		if(list.size() > 0) {
	%>
	{	<%--json시작 --%>
		"sw" : "OK",
		"data" : [
	<%
		
		//리턴받은 list객체로 json데이터 생성하기
		for(int i=0;i<list.size();i++){
			ProdVO vo = list.get(i);
			if(i > 0) out.print(",");
	%>
		{
			"id"   : "<%= vo.getProd_id() %>",
			"name" : "<%= vo.getProd_name() %>"
		}
	<% 
		} // for
	%>
	  ]
		} <%--json끝 --%>
	<%	
	} else {// if
	%>
	{ "sw" : "NO" }
	<%
	} // else
	%>
