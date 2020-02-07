<%@page import="project.projectDAO"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <%
request.setCharacterEncoding("utf-8");

String id = request.getParameter("id");
System.out.println(id);
projectDAO dao = new projectDAO();
int check = dao.idCheck(id);

out.println(check);
%>
