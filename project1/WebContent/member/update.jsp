<%@page import="project.projectBean"%>
<%@page import="project.projectDAO"%>
<%@page import="com.mysql.jdbc.UpdatableResultSet"%>
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
	<script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.3.1.min.js"></script>
	
</head>
<body>
	<%
		String id = (String) session.getAttribute("id");
		
		projectDAO projectdao = new projectDAO();
		
		projectBean projectBean = projectdao.select(id);
		
		String passwd = projectBean.getPasswd();
		String address = projectBean.getAddress();
		String email = projectBean.getEmail();
		String name = projectBean.getName();
		String tel =  projectBean.getTel();
		
	
	%>


	<div id="page">
			
		<div id="header">
			<div class="login_join">
					<a href = "../member/login.jsp">로그인</a> |
					<a href = "../member/join.jsp">회원가입</a> 
					
				</div>
			<div>
				<a href="../index.jsp" class="logo"><img src="../images/logo.png" alt=""></a>
				<ul id="navigation">
					<li class="selected">
						<a href="../index.jsp">Home</a>
					</li>
					<li class="menu">
						<a href="../about.jsp">About</a>
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
						<a href="../contact.jsp">Contact</a>
					</li>
				</ul>
			</div>
		</div>
		<div id="body" class="home">
			<div class="header">
			
				<img src="images/bg-home.jpg" alt="">
				
				
					<div class="memberlogin">
						
			
							<div >
									<form action="updatePro.jsp" id="join" method="post">
								<fieldset>
								아이디:<input type="text" name="id" id="user_id" value ="<%=id %>" readonly="readonly"><br>	<br>
							     비밀번호:<input type="password" name="passwd" id="user_pw"value="<%=passwd%>"><br><br>     
								   이름:<input type="text" name="name" id="user_name" value="<%=name%>"><br><br>
								이메일:<input type="text" name="email" id= "user_email" value="<%=email%>"><br><br>		
								
								</fieldset>
								<fieldset>
								주소:<input type="text" name="address" value="<%=address%>"><br><br>
								휴대전화번호:<input type="text" name="tel" value="<%=tel%>"><br><br>
								</fieldset>
								
								<div id="buttons">
							<input type="submit" value="회원정보수정" class="submit"> 
							<input type="reset" value="다시입력" class="cancel">
								
							</form>
				
				
					
				
			</form>
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
							<a href="../product.html" class="product"></a>
							<h1>PRODUCTS</h1>
						</li>
						<li>
							<a href="../about.html" class="about"></a>
							<h1>OUR STORY</h1>
						</li>
						<li>
							<a href="../product.html" class="flavor"></a>
							<h1>FLAVORS</h1>
						</li>
						<li>
							<a href="../contact.html" class="contact"></a>
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
	</div>
</body>
</html>
