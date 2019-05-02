<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//여기는 클라이언트에서 보내오는 데이터를 받아서 처리하는 곳
	//CRUD처리(db에 넣고, 수정하고, 검색하고, 삭제하는 역할)가 들어갈 예정
	//처리 결과를 응답데이터로 생성한다
%>
<%-- CRUD 처리 결과로 JSON 객체의 응답데이터를 생성 --%>
{
	"name" : "홍길동",
	"addr" : "대전",
	"tel"  : "010-1123-1234",
	"email": "aa@korea123.com"
}