
<%@page import="jaro.JaroBean"%>
<%@page import="jaro.JaroDAO"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="board.BoardBean"%>
<%@page import="java.util.List"%> 
<%@page import="java.text.SimpleDateFormat"%>

<%@page import="board.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<!-- Website template by freewebsitetemplates.com -->
<html>
<head>
<style type="text/css">
.m{
height: 800px !important;

}
.m1{
height: 850px !important;
}
</style>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Frozen Yogurt Shop</title>
	<link rel="stylesheet" href="../css/style.css" type="text/css">
	<link rel="stylesheet" href="../css/substyle.css" type="text/css">
	<link rel="stylesheet" type="text/css" href="../css/mobile.css">
	<script src="../js/mobile.js" type="text/javascript"></script>
	<script language="javascript"
	src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.3.1.min.js"></script>
	<script type="text/javascript">
	

	</script>
</head>
<%

	String id =(String)session.getAttribute("id");
if(id == null){
    response.sendRedirect("../member/login.jsp");
    
}	

	request.setCharacterEncoding("UTF-8");
	
	int num = Integer.parseInt(request.getParameter("num"));
	String pageNum = request.getParameter("pageNum");
	
	JaroDAO dao = new JaroDAO();
	
	JaroBean jaroBean = dao.getJaro(num); 
	
	int DBnum = jaroBean.getNum();
	String DBName = jaroBean.getName();
	String DBSubject = jaroBean.getSubject();
	String DBContent = "";
	String DBFile = jaroBean.getFile();
	if(jaroBean.getContent() != null){
	    DBContent = jaroBean.getContent().replace("\r\n", "<br/>");
	}
	
	
	%>



<body>
	<div id="page">
			
		
			<div id="header">
			<%
			//session객체영역에 세션id값을 얻어...
			
			
			//session객체 영역에 세션id값이 저장되어 있지 않다면?
			if(id == null){ //로그아웃된 상태의 화면을 보여 주자 
		%>		
			<div class="login_join">
				<a href="../member/login.jsp">로그인</a> |
				<a href="../member/join.jsp">회원가입</a>
			</div>
		<%		
			}else{//session객체 영역에 세션id값이 저장되어 있다면?
		%>	
			<div class="login_join">
				<%=id %>님 로그인중... |
				<a href= "../member/logout.jsp">로그아웃</a> |
				<a href = "../member/update.jsp">마이페이지</a>
			</div>
		<%			
			}
		%>
			<div>
				<a href="../index.jsp" class="logo"><img src="../images/logo.png" alt=""></a>
				<ul id="navigation">
					<li class="selected">
						<a href="../index.jsp">Home</a>
					</li>
					<li class="menu">
						<a href="Jaro.jsp">자료실</a>
						<ul class="primary">
							<li>
								<a href="../product.jsp">Product</a>
							</li>
						</ul>
					</li>
					<li class="menu">
						<a href="../center/notice.jsp">게시판</a>
						<ul class="secondary">
							<li>
								<a href="../singlepost.jsp">Single post</a>
							</li>
						</ul>
					</li>
					<li>
						<a href="../gallery/Thumbnail.jsp">갤러리</a>
					</li>
				</ul>
			</div>
		</div>
		<div id="body" class="home">
			<div class="header">
			
				<img src="images/bg-home.jpg" alt="">
				<div class="m1">
					<div class="m">
						<div>
						
						<h1>자료실 수정</h1>
						<form action="JaroUpdatePro.jsp?pageNum=<%=pageNum%>" method="post" name="fr" enctype="multipart/form-data" >
						<input type ="hidden" name="num" value="<%=num%>">
						<table border="1" id= "notice">
						<tr>
						<td>이름</td>
						<td><input type ="text" name="name" value="<%=DBName%>"></td>
						</tr>
						<tr>
						<td>비밀번호</td>
						<td><input type ="password" name="passwd"></td>
						</tr>
						<tr>
						<td>제목</td>
						<td><input type ="text" name="subject" value="<%=DBSubject%>"></td>
						</tr>
						<tr>
						<td>내용</td>
						<td><textarea name="content" rows="13" cols="40"><%=DBContent %></textarea></td>
						</tr>
						
						<tr>
						<td>파일 </td>
						<td><input type="text" value="<%=DBFile %>"><input type="file" name="file"></td>
						</tr>
						
						</table>
	
	  					  
			
							<input type="button" value="글수정" class="btn"
							onclick="location.href='delete.jsp?num=<%=DBnum%>&pageNum=<%=pageNum%>'">
			
						<input type="button" value="글삭제" class="btn">
						<input type ="button" value="글목록" class="btn" onclick="location.href='Jaro.jsp'">
						</form>
						
			
					</div>
				</div>
			</div>
			</div>
			<div class="body">
				<div>
					<div>
						<h1>NEW PRODUCT</h1>
						<h2>The Twist of Healthy Yogurt</h2>
						<p>This website template has been designed by freewebsitetemplates.com for you, for free. You can replace all this text with your own text.</p>
					</div>
					<img src="images/yogurt.jpg" alt="">
				</div>
			</div>
			<div class="footer">
				<div>
					<ul>
						<li>
							<a href="product.html" class="product"></a>
							<h1>PRODUCTS</h1>
						</li>
						<li>
							<a href="about.html" class="about"></a>
							<h1>OUR STORY</h1>
						</li>
						<li>
							<a href="product.html" class="flavor"></a>
							<h1>FLAVORS</h1>
						</li>
						<li>
							<a href="contact.html" class="contact"></a>
							<h1>OUR LOCATION</h1>
						</li>
					</ul>
				</div>
			</div>
		
		</div>
		<div id="footer">
			<div>
				<div class="connect">
					<a href="http://freewebsitetemplates.com/go/facebook/" class="facebook">facebook</a>
					<a href="http://freewebsitetemplates.com/go/twitter/" class="twitter">twitter</a>
					<a href="http://freewebsitetemplates.com/go/googleplus/" class="googleplus">googleplus</a>
					<a href="http://pinterest.com/fwtemplates/" class="pinterest">pinterest</a>
				</div>
				<p>&copy; 2023 Freeeze. All Rights Reserved.</p>
			</div>
		</div>
	
	</div>
	
</body>
</html>
