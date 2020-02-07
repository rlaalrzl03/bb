
<%@page import="board.BoardDAO"%>
<%@page import="java.sql.Timestamp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>reWritePro.jsp</h1>
<%
request.setCharacterEncoding("UTF-8");

%>
<jsp:useBean id="bBean" class="board.BoardBean"></jsp:useBean>
<jsp:setProperty property="*" name="bBean"/>
<%
bBean.setDate(new Timestamp(System.currentTimeMillis()));
bBean.setIp(request.getRemoteAddr());

BoardDAO bdao = new BoardDAO();
bdao.reInsertBoard(bBean); 
response.sendRedirect("notice.jsp");


%>

</body>
</html>