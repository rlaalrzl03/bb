<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<!-- Website template by freewebsitetemplates.com -->
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>contact - Frozen Yogurt Shop</title>
	<link rel="stylesheet" href="css/style.css" type="text/css">
	<link rel="stylesheet" type="text/css" href="css/mobile.css">
	<script src="js/mobile.js" type="text/javascript"></script>
</head>
<body>
	<div id="page">
		<div id="header">
			<div>
				<a href="index.jsp" class="logo"><img src="images/logo.png" alt=""></a>
				<ul id="navigation">
					<li>
						<a href="index.jsp">Home</a>
					</li>
					<li class="menu">
						<a href="about.jsp">About</a>
						<ul class="primary">
							<li>
								<a href="product.jsp">Product</a>
							</li>
						</ul>
					</li>
					<li class="menu">
						<a href="center/notice.jsp">계시판</a>
						<ul class="secondary">
							<li>
								<a href="singlepost.jsp">Single post</a>
							</li>
						</ul>
					</li>
					<li class="selected">
						<a href="contact.jsp">Contact</a>
					</li>
				</ul>
			</div>
		</div>
		<div id="body" class="contact">
			<div class="header">
				<div>
					<h1>Contact</h1>
				</div>
			</div>
			<div class="body">
				<div>
					<div>
						<img src="images/check-in.png" alt="">
						<h1>UNIT 0123 , ABC BUILDING, BUSSINESS PARK</h1>
						<p>If you're having problems editing this website template, then don't hesitate to ask for help on the Forums.</p>
					</div>
				</div>
			</div>
			<div class="footer">
				<div class="contact">
					<h1>INQUIRY FORM</h1>
					<form action="index.html">
						<input type="text" name="Name" value="Name" onblur="this.value=!this.value?'Name':this.value;" onfocus="this.select()" onclick="this.value='';">
						<input type="text" name="Email" value="Email" onblur="this.value=!this.value?'Email':this.value;" onfocus="this.select()" onclick="this.value='';">
						<input type="text" name="Subject" value="Subject" onblur="this.value=!this.value?'Subject':this.value;" onfocus="this.select()" onclick="this.value='';">
						<textarea name="meassage" cols="50" rows="7">Share your thoughts</textarea>
						<input type="submit" value="Send" id="submit">
					</form>
				</div>
				<div class="section">
					<h1>WE’D LOVE TO HEAR FROM YOU.</h1>
					<p>If you're having problems editing this website template, then don't hesitate to ask for help on the Forums.</p>
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
