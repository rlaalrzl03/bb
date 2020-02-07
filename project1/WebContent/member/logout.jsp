<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%
//세션값 초기화
session.invalidate();

//이동 "로그아웃" index.jsp
%>

 <script>
 alert("로그아웃");
 location.href="../index.jsp"
 
 
 </script>