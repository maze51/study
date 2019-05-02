<%@page import="kr.or.ddit.buyer.vo.BuyerVO"%>
<%@page import="kr.or.ddit.buyer.service.BuyerServiceImpl"%>
<%@page import="kr.or.ddit.buyer.service.IBuyerService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<% 

	String idvalue = request.getParameter("id");
	
	IBuyerService service = BuyerServiceImpl.getInstance();
	
	BuyerVO vo = service.getDetail(idvalue);

%>

{
	"id" : "<%= vo.getBuyer_id() %>",
	"name" : "<%= vo.getBuyer_name() %>",
	"bank" : "<%= vo.getBuyer_bank() %>",
	"bankname" : "<%= vo.getBuyer_bankname() %>",
	"lgu" : "<%= vo.getBuyer_lgu() %>",
	"mail" : "<%= vo.getBuyer_mail() %>",
	"addr" : "<%= vo.getBuyer_add1() %>",
	"zip" : "<%= vo.getBuyer_zip() %>"
}
   