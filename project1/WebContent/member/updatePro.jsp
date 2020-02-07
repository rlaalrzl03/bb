<%@page import="project.projectDAO"%>
<%@page import="java.sql.Timestamp"%>
<%
request.setCharacterEncoding("UTF-8");

%>
<jsp:useBean id="projectbean" class="project.projectBean"></jsp:useBean>
<jsp:setProperty property="*" name="projectbean"/>	

<%

projectbean.setReg_date(new Timestamp(System.currentTimeMillis()));
projectDAO projectdao= new projectDAO(); 
projectdao.update(projectbean);
response.sendRedirect("../index.jsp");

%>