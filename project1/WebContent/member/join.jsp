<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!doctype html>
<!-- Website template by freewebsitetemplates.com -->
<html>
<head>
<style type="text/css">
.m{
height: 630px !important;

}
.m1{
height: 650px !important;
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
	<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	
	<script type="text/javascript">
	var email_Check = false;
	var id_Check=false;
	function checkz() {
		var getCheck = RegExp(/^[a-zA-Z0-9]{4,12}$/);
		var getName = RegExp(/^[가-힣]+$/);
		var getMail = RegExp(/^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/);
		
		//아이디 공백 확인
		if ($("#user_id").val() == "") {
			alert("아이디를 입력해주세요");
			$("#user_id").focus();
			return false;
		}

		//이름의 유효성 검사
		if (!getCheck.test($("#user_id").val())) {
			alert("아이디 형식이 옳바르지않습니다.");
			$("#user_id").val("");
			$("#user_id").focus();
			return false;
			
		} 
		
		if(id_Check!=true) {
			alert("아이디중복검사가  완료되지 않았습니다. 중복검사 후 다시 시도해주세요.");
			$("#user_id").val("");
			$("#user_id").focus();
			return false;
		}
		
		
		//비밀번호
		if (!getCheck.test($("#user_pw").val())) {
			alert("패스워드형식이 올바르지않습니다.");
			$("#user_pw").val("");
			$("#user_pw").focus();
			return false;
		}

		//아이디랑 비밀번호랑 같은지
		if ($("#user_id").val() == ($("#user_pw").val())) {
			alert("아이디와 비밀번호가 같습니다.");
			$("#user_pw").val("");
			$("#user_pw").focus();
			return false;
		}

		//비밀번호 똑같은지
		if ($("#user_pw").val() != ($("#user_pw2").val())) {
			alert("비밀번호가 틀렸습니다.");
			$("#user_pw").val("");
			$("#user_pw").val("");
			$("#user_pw").focus();
			return false;
		}
		
		//이름 유효성
		if (!getName.test($("#user_name").val())) {
			alert("이름형식이 맞지 않습니다.");
			$("#user_name").val("");
			$("#user_name").focus();
			return false;
		}
		
		if(email_Check !=true) {
			alert("이메일 인증이 완료되지 않았습니다. 이메일 인증후 다시 시도해주세요.");
			$("#user_email").val("");
			$("#user_email").focus();
			return false;
		}
		//이메일 공백 확인
		if ($("#user_email").val() == "") {
			alert("이메일을 입력해주세요");
			$("#user_email").focus();
			return false;
		}

		//이메일 유효성 검사
	
		if ($("#user_email").val() != ($("#user_email2").val())) {
			alert("이메일이 일치하지않습니다.")
			$("#user_email").val("");
			$("#user_email").focus();
			return false;
		}
		
		
		
	}
	
	
	function winopen() {
		//id를 입력 했는지 체크
		//입력한 ID값을 얻어...빈공백인지 파악
		if(document.fr.id.value == ""){//아이디를 입력 하지 않았다면...
			alert("아이디를 입력 하세요.");
			//아이디 입력란에 포커스 깜빡
			document.fr.id.focus();
			return;
			
		}else{ 
			
			$.ajax({
				type: "get",
				url: "join_IDCheck.jsp",
				data: ({id: $("#user_id").val()}),
				success: function(data) {
					if(data !=1) {
						$("#idCheck").css("color", "green");
						$("#idCheck").text("사용가능한 아이디입니다.");
						id_Check = true;
					} else {
						$("#idCheck").css("color", "red");
						$("#idCheck").text("이미 사용중인 아이디입니다.");
						id_Check = false;
					}
				}
			});
			
			
		}
		
		//새창 열때... 우리가 입력한 ID를 전달 할수 있도록
		
		
	}
	

	 function sendEmail() {

		
			window.open("authMail.jsp?email=" + document.fr.email.value, "인증 페이지", "width=430,height=200");
	}
	 
	function sample4_execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var roadAddr = data.roadAddress; // 도로명 주소 변수
                var extraRoadAddr = ''; // 참고 항목 변수

                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                    extraRoadAddr += data.bname;
                }
                // 건물명이 있고, 공동주택일 경우 추가한다.
                if(data.buildingName !== '' && data.apartment === 'Y'){
                   extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                if(extraRoadAddr !== ''){
                    extraRoadAddr = ' (' + extraRoadAddr + ')';
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                
                document.getElementById("sample4_roadAddress").value = roadAddr;
                
                
                // 참고항목 문자열이 있을 경우 해당 필드에 넣는다.
                
                var guideTextBox = document.getElementById("guide");
                // 사용자가 '선택 안함'을 클릭한 경우, 예상 주소라는 표시를 해준다.
                if(data.autoRoadAddress) {
                    var expRoadAddr = data.autoRoadAddress + extraRoadAddr;
                    guideTextBox.innerHTML = '(예상 도로명 주소 : ' + expRoadAddr + ')';
                    guideTextBox.style.display = 'block';

             
                } else {
                    guideTextBox.innerHTML = '';
                    guideTextBox.style.display = 'none';
                }
            }
        }).open();
    }
	

	</script>
	
	
</head>
<body>
	<div id="page">
			
		<div id="header">
			<div class="login_join">
					<a href = "login.jsp">로그인</a>
					<a href = "join.jsp">회원가입</a>
				</div>
			<div>
				<a href="../index.jsp" class="logo"><img src="../images/logo.png" alt=""></a>
				<ul id="navigation">
					<li class="selected">
						<a href="../index.jsp">Home</a>
					</li>
					<li class="menu">
						<a href="../uploade/Jaro.jsp">자료실</a>
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
					<div class="memberjoin">
						<div class="m">
						<h2> 회원가입</h2>
						 
							<form action = "joinpro.jsp" method = "post" onsubmit="return checkz();" name="fr">
								<fieldset>
								아이디:<input type="text" name="id" id="user_id">
								<input type="button" value="ID중복체크" class="dup" onclick="winopen();"><br>
								<span id ="idCheck"></span><br>
							     비밀번호:<input type="password" name="passwd" id="user_pw"><br><br>
						      비밀번호확인:<input type="password2" name="passwd2" id="user_pw2"><br><br>
								   이름:<input type="text" name="name" id="user_name"><br><br>								
								이메일:<input type="text" name="email" id= "user_email">
								<input type="button" value="이메일 확인" onclick="sendEmail();"><br><br>		
								이메일확인:<input type="text" name="email2" id= "user_email2"><br><br>
								</fieldset>
								<fieldset>										
								주소: <input type="text" id="sample4_roadAddress" placeholder="도로명주소" name="address"style="width:200px;" >
								<input type="button" value="주소검색" onclick="sample4_execDaumPostcode();"><br><br>		
							
									<span id="guide" style="color:#999;display:none"></span>
								
								휴대전화번호:<input type="text" name="tel"><br><br>
								</fieldset>
								
								<input type ="submit" value="회원가입">
								<input type ="reset" value="다시입력">
								
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
	
	<input type="button" onclick="test();" value="텍스트">
</body>
</html>
