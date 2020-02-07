<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<!-- Website template by freewebsitetemplates.com -->

<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Frozen Yogurt Shop</title>
	<link rel="stylesheet" href="css/style.css" type="text/css">
	<link rel="stylesheet" href="css/substyle.css" type="text/css">
	<link rel="stylesheet" type="text/css" href="css/mobile.css">
	<script src="js/mobile.js" type="text/javascript"></script>
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.min.js"></script>
	
	<script>
	 $(document).ready(function(){
	      $('.bxslider').bxSlider({
	          mode:"fade",    
	          speed:500,
	          pause: 1000,
	          pager:false,
	          moveSlides:1,  
	        
	          maxSlides:3,    
	          slideMargin:3, 
	          auto:true,         
	          controls:false    
	      });
	    });
	 
	</script>
	
	 <style type="text/css">
#body.home div.header{
	max-height: 550px !important; 

	
}
#slide{

margin-left: 300px !important;
height: 500px !important;
width:400px !important;
padding-top: 20px !important;
margin-bottom: 20px !important;


}	

#body.home div.header img{
left: 0 !important;
position: none !important;
}
	
tbody{
width: 1060px !important;
}


	</style>
	 
	
</head>
<body>




	<div id="page">
<%



String realPath = request.getRealPath("/test");


%>
			
		<div id="header">
			<%
			//session객체영역에 세션id값을 얻어...
			String id  = (String)session.getAttribute("id");
			
			//session객체 영역에 세션id값이 저장되어 있지 않다면?
			if(id == null){ //로그아웃된 상태의 화면을 보여 주자 
		%>		
			<div class="login_join">
				<a href="member/login.jsp">로그인</a> |
				<a href="member/join.jsp">회원가입</a>
			</div>
		<%		
			}else{//session객체 영역에 세션id값이 저장되어 있다면?
		%>	
			<div class="login_join">
				<%=id %>님 로그인중... |
				<a href= "member/logout.jsp">로그아웃</a> |
				<a href = "member/update.jsp">마이페이지</a>
			</div>
		<%			
			}
		%>
			<div>
				<a href="index.jsp" class="logo"><img src="images/logo.png" alt=""></a>
				<ul id="navigation">
					<li class="selected">
						<a href="index.jsp">Home</a>
					</li>
					<li class="menu">
						<a href="upload/Jaro.jsp">자료실</a>
						<ul class="primary">
							<li>
								<a href="product.jsp">Product</a>
							</li>
						</ul>
					</li>
					<li class="menu">
						<a href="center/notice.jsp">게시판</a>
						<ul class="secondary">
							<li>
								<a href="singlepost.jsp">Single post</a>
							</li>
						</ul>
					</li>
					<li>
						<a href="gallery/Thumbnail.jsp">갤러리
						</a>
					</li>
				</ul>
			</div>
		</div>
		<div id="body" class="home">
			<div class="header">
			
				 
				<div class="bxslider">
					<div><img  id="slide" src="slide/1.png"></div>
					<div><img  id="slide" src="slide/2.png"></div>
					<div><img id="slide" src="slide/3.png"></div>
					<div><img id="slide" src="slide/4.png"></div>
					<div><img id="slide" src="slide/5.png"></div>
					<div><img id="slide" src="slide/6.png"></div>
					<div><img id="slide"  src="slide/7.png"></div>
					<div><img id="slide"  src="slide/8.jpg"></div>
					<div><img id="slide"  src="slide/9.png"></div>
					<div><img id="slide"  src="slide/10.png"></div>
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
							<a href="product.jsp" class="product"></a>
							<h1>PRODUCTS</h1>
						</li>
						<li>
							<a href="about.jsp" class="about"></a>
							<h1>OUR STORY</h1>
						</li>
						<li>
							<a href="product.jsp" class="flavor"></a>
							<h1>FLAVORS</h1>
						</li>
						<li>
							<a href="contact.jsp" class="contact"></a>
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
    