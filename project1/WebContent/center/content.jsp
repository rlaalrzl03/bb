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
height: 450px !important;

}
.m1{
height: 500px !important;
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
/*글 상세보기 페이지*/
	request.setCharacterEncoding("UTF-8");

int num = Integer.parseInt(request.getParameter("num")); 
String pageNum = request.getParameter("pageNum"); 

 BoardDAO dao = new BoardDAO();
 
 //조회수 1증가
 dao.updateReadCount(num);
 
 BoardBean boardBean = dao.getBoard(num); 
 
int DBnum = boardBean.getNum();
int DBReadcount = boardBean.getReadcount();
String DBName = boardBean.getName();
Timestamp DBDate = boardBean.getDate();
String DBSubject = boardBean.getSubject();
String DBContent = "";
if(boardBean.getContent() != null){
    DBContent = boardBean.getContent().replace("\r\n", "<br>/");
    
}
int DBRe_ref = boardBean.getRe_ref();
int DBRe_lev = boardBean.getRe_lev();
int DBRe_seq = boardBean.getRe_seq();

%>





<body>
	<div id="page">
			
		<div id="header">
			<%
			//session객체영역에 세션id값을 얻어...
			String id  = (String)session.getAttribute("id");
			
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
				<a href="index.html" class="logo"><img src="images/logo.png" alt=""></a>
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
						<a href="../gallery/Thumbnail.jsp">갤러리</a>
					</li>
				</ul>
			</div>
		</div>
		<div id="body" class="home">
			<div class="header">
			
				<img src="images/bg-home.jpg" alt="">
				<div class ="m1" >
					<div class= "m">
						<div >
						<table border ="1" id ="notice">
						<tr>
						<td>글번호</td>
						<td><%=DBnum %></td>
						<td>조회수</td>
						<td><%=DBReadcount %></td>
				
						</tr>
						
						<tr>
						<td>작성자</td>
						<td><%=DBName %></td>
						<td>작성일</td>
						<td><%=DBDate %></td>				
						</tr>
											
						<tr>
						<td>글제목</td>					
						<td colspan="3"><%=DBSubject %></td>			
						</tr>
						
						<tr>  
						<td>글내용</td>	
						<td colspan="3"><%=DBContent %></td>
						</tr>
						
						</table>
						
	<%
	
	
	if(id!=null){
	    %>
	   <input type="button" value="글수정" class="btn" 
			onclick="location.href='update.jsp?num=<%=DBnum%>&pageNum=<%=pageNum%>'">
			
			<input type="button" value="글삭제" class="btn"
			onclick="location.href='delete.jsp?num=<%=DBnum%>&pageNum=<%=pageNum%>'">
			
			<input type="button" value="답글쓰기" class="btn"
			onclick="location.href='reWrite.jsp?num=<%=DBnum%>&re_ref=<%=DBRe_ref%>&re_lev=<%=DBRe_lev%>&re_seq=<%=DBRe_seq%>'">
		<%			
			}
		%>
	
	
			
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
