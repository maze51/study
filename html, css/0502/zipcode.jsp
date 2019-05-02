<%@page import="kr.or.ddit.member.service.MemberServiceImpl"%>
<%@page import="kr.or.ddit.member.service.IMemberService"%>
<%@page import="kr.or.ddit.member.vo.ZipVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<%
	request.setCharacterEncoding("UTF-8");
	
	String dong = request.getParameter("dong");
	
	IMemberService service = MemberServiceImpl.getInstance();
	
	List<ZipVO> list = service.getZipcode(dong);
	
	if(list.size() > 0) {
%>		
		{
			"sw" : "OK",
			"data" : [
<%		
		
			for(int i=0;i<list.size();i++){
				ZipVO vo = list.get(i);
				if(i > 0) out.print(",");
%>				
			{
				"zipcode" : "<%= vo.getZipcode() %>",
				"sido" : "<%= vo.getSido() %>",
				"gugun" : "<%= vo.getGugun() %>",
				"dong" : "<%= vo.getDong() %>",
				"bunji" : "<%= vo.getBunji() %>"
			}	
<%				
			} // for
%>
		]
			}
<%
	} else {
%>		
	{ "sw" : "NO" }
	<%
	} // else
%>