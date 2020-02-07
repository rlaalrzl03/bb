<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@page import="jaro.JaroBean"%>
<%@page import="jaro.JaroDAO"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="board.BoardDAO"%>
<%@page import="board.BoardBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>writePro.jsp</h1>
	<%
	String id = (String)session.getAttribute("id");
	
	if(id==null){
	    response.sendRedirect("../member/login.jsp");
	    
	}
	request.setCharacterEncoding("UTF-8");
	String savePath = getServletContext().getRealPath("/test");
			int sizeLimit = 10 * 1024 * 1024; // 10메가까지 제한 넘어서면 예외발생
	
	
	MultipartRequest multi = 
	        new MultipartRequest(request, savePath, sizeLimit, "UTF-8",
								 new DefaultFileRenamePolicy());
	
	JaroBean jBean = new JaroBean();
	jBean.setContent(multi.getParameter("content"));
	jBean.setPasswd(multi.getParameter("passwd"));
	jBean.setName(multi.getParameter("name"));
	jBean.setSubject(multi.getParameter("subject"));
	jBean.setFile(multi.getFilesystemName("file"));
	
	
	jBean.setDate(new Timestamp(System.currentTimeMillis()));
	jBean.setIp(request.getRemoteAddr());


	JaroDAO jdao= new JaroDAO();

	jdao.insertJaro(jBean); 
	
	response.sendRedirect("Jaro.jsp");
%>



</body>
</html>