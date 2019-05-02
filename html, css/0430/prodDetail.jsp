<%@page import="kr.or.ddit.prod.vo.ProdVO"%>
<%@page import="kr.or.ddit.prod.service.ProdServiceImpl"%>
<%@page import="kr.or.ddit.prod.service.IProdService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	String idvalue = request.getParameter("id");

	IProdService service = ProdServiceImpl.getInstance();
	
	ProdVO vo = service.getProdDetail(idvalue);
%>

{
	"id" : "<%= vo.getProd_id() %>",
	"name" : "<%= vo.getProd_name() %>",
	"lgu" : "<%= vo.getProd_lgu() %>",
	"buyer" : "<%= vo.getProd_buyer() %>",
	"cost" : "<%= vo.getProd_cost() %>",
	"price" : "<%= vo.getProd_price() %>",
	"sale" : "<%= vo.getProd_sale() %>",
	"outline" : "<%= vo.getProd_outline() %>",
	"detail" : "<%= vo.getProd_detail() %>"
}