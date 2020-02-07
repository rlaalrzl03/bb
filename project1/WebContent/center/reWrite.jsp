
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
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Frozen Yogurt Shop</title>
	<link rel="stylesheet" href="../css/style.css" type="text/css">
	<link rel="stylesheet" href="../css/substyle.css" type="text/css">
	<link rel="stylesheet" type="text/css" href="../css/mobile.css">
	<script src="../js/mobile.js" type="text/javascript"></script>
	<script language="javascript"
	src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.3.1.min.js"></script>
	<style type="text/css">
	.m{
	height: 680px !important;
	}
	.m1{
	height: 700px !important;
	}
	</style>
</head>
<%

	String id =(String)session.getAttribute("id");
if(id == null){
    response.sendRedirect("../member/login.jsp");
    
}	

	request.setCharacterEncoding("UTF-8");
	
	int num = Integer.parseInt(request.getParameter("num"));
	int re_ref =Integer.parseInt(request.getParameter("re_ref"));
	int re_lev =Integer.parseInt(request.getParameter("re_lev"));
	int re_seq = Integer.parseInt(request.getParameter("re_seq"));
	%>



<body>
	<div id="page">
			
		<div id="header">
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
						<a href="../upload/Jaro.jsp">자료실</a>
						<ul class="primary">
							<li>
								<a href="../product.jsp">Product</a>
							</li>
						</ul>
					</li>
					<li class="menu">
						<a href="notice.jsp">게시판</a>
						<ul class="secondary">
							<li>
								<a href="../singlepost.jsp">Single post</a>
							</li>
						</ul>
					</li>
					<li>
						<a href="../gallery/Tumbnail.jsp">갤러리</a>
					</li>
				</ul>
			</div>
		</div>
		</div>
		<div id="body" class="home">
			<div class="header">
			
				<img src="images/bg-home.jpg" alt="">
				<div class="m1">
					<div class="m">
						<h1>계시판 답글쓰기</h1>
						<form action="reWritePro.jsp" method="post" name="fr">
					
						<input type = "hidden" name="num" value="<%=num %>">
						<input type = "hidden" name="re_ref" value="<%=re_ref %>">
						<input type = "hidden" name="re_lev" value="<%=re_lev %>">
						<input type = "hidden" name="re_seq" value="<%=re_seq %>">  
						
						<table border="1"   id = "notice" >
						
						<tr>
						<td>이름</td>
						<td><input type="text" name="name"></td>
						</tr>
						
						<tr>
						<td>비밀번호</td>
						<td><input type="password" name="passwd"></td>
						</tr>
						
						<tr>
						<td>제목</td>
						<td><input type="text" name="subject" value="[답글]"></td>
						</tr>
						
						<tr>
						<td>내용</td>
						<td><textarea name="content" rows="13" cols="40"></textarea></td>
						</tr> 
						
						</table>
						
						<div id = table_search>
						<input type ="submit" value="답글작성" class="btn">
						<input type ="reset" value="다시작성" class="btn">
						<input type ="button" value="목록보기" class="btn" onclick="location.href='notice.jsp'">
						</div>
						
						</form>
						
						
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
