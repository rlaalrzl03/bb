<%@page import="java.sql.Timestamp"%>
<%@page import="jaro.JaroBean"%>
<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@page import="jaro.JaroDAO"%>
<%@page import="board.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>updatePro.jsp</h1>
<%

request.setCharacterEncoding("UTF-8");
String pageNum = request.getParameter("pageNum");
String id = (String)session.getAttribute("id");
int check = 0;

if(id==null){
    response.sendRedirect("../member/login.jsp");
    
}
String savePath = request.getRealPath("/test"); // 저장할 디렉토리 (절대경로)
int sizeLimit = 10 * 1024 * 1024; // 10메가까지 제한 넘어서면 예외발생


MultipartRequest multi = 
        new MultipartRequest(request, savePath, sizeLimit, "UTF-8",
							 new DefaultFileRenamePolicy()); 

JaroBean jBean = new JaroBean();
jBean.setNum(Integer.parseInt(multi.getParameter("num")));      
jBean.setContent(multi.getParameter("content"));
jBean.setPasswd(multi.getParameter("passwd"));
jBean.setName(multi.getParameter("name"));
jBean.setSubject(multi.getParameter("subject"));
jBean.setFile(multi.getFilesystemName("file"));
 
jBean.setDate(new Timestamp(System.currentTimeMillis()));
jBean.setIp(request.getRemoteAddr());


JaroDAO  bdao = new JaroDAO();

  check = bdao.updateJaro(jBean);     

if(check ==1){
    %>
    <script type = "text/javascript">
   	 	alert("수정성공");
    	location.href="Jaro.jsp?pageNum=<%=pageNum%>";
    </script>
<%  
}else{
%>  
 	<script type = "text/javascript">
 		alert("비밀번호틀림");
  		history.back();
  	</script>
<% 
}
%>


</body>
</html>