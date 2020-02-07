
<%@page import="java.sql.Timestamp"%>
<%@page import="project.projectBean"%>
<%@page import="project.projectDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
request.setCharacterEncoding("UTF-8");

%>
<jsp:useBean id="projectbean" class="project.projectBean"></jsp:useBean>
<jsp:setProperty property="*" name="projectbean"/>	

<%

projectbean.setReg_date(new Timestamp(System.currentTimeMillis()));
projectDAO projectdao= new projectDAO();
projectdao.insertproject(projectbean);
%>
<script>
	alert("회원가입에 성공하였습니다.");
	location.href="login.jsp";
</script>




