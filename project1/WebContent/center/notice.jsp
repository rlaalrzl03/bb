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
	<script type="text/javascript">
	

	</script>
</head>
<% 
BoardDAO boardDAO = new BoardDAO();
	//전체글의 개수를보기
	int count = boardDAO.getBoardCount();
	System.out.print(count); 

	//하나의 화면 마다 보여줄 글의 개수
	int pageSize = 10;
	
	//현재보여질 페이지를 가져오기
	String pageNum = request.getParameter("pageNum");
	
	if(pageNum == null){
	    pageNum = "1";
	}
	
	//현재보여질페이지번호
	int currentPage = Integer.parseInt(pageNum);
	
	//각페에지의 맨위에 보여질 숫자
	int startRow = (currentPage-1)*pageSize;
	
	//계시판 글객체 BoardBean를 저장하기위한용도
	List<BoardBean> list = null;
	
	if(count>0){
	    
	    list = boardDAO.getBoradList(startRow ,pageSize);  
	    
	}
	SimpleDateFormat sdf = new SimpleDateFormat("yyy.MM.dd");
	
	
	
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
						<a href="../gallery/Thumbnail.jsp">갤러리</a>
					</li>
				</ul>
			</div>
		</div>
		<div id="body" class="home">
			<div class="header">
			
				<img src="images/bg-home.jpg" alt="">
				</div>
				<div>
					<div class="m">
						<div>
						<h2> 게시판[전체글개수 :<%=count %>]</h2>
						<!-- onsubmit="return checkz()" enctype="text/plain" -->
				<table border ="1" id ="notice">
				
				<tr>
					<td class="tno">No.</td>
					<td class="ttitle">Title</td>
					<td class="twrite">Writer</td>
					<td class="tdate">Date</td>
					<td class="tread">Read</td>
				</tr>
				<%
				if (count>0){
				    for(int i = 0; i<list.size(); i++){
				        BoardBean bean = list.get(i);
				    %>    
				        <tr onclick="location.href='content.jsp?num=<%=bean.getNum()%>&pageNum=<%=pageNum%>'">
						<td><%=bean.getNum() %></td>
						<td class="left">
						<%
						int wid =0;
						if(bean.getRe_lev()>0){
						    wid= bean.getRe_lev() *10;
						 %>   
						 <img src="../images/center/level.gif" width="<%=wid%>" height="15">
						 <img src="../images/center/re.gif">
						<% 
						}
						
						%>
						<%=bean.getSubject() %></td>
						<td><%=bean.getName() %></td>
						<td><%=sdf.format(bean.getDate()) %></td>
						<td><%=bean.getReadcount() %></td>
						
						</tr>
					<% 	
				        
				    }//for끝
				    
				}else{//게시판에 글개수가존재하지않으면 (글이없다는뜻)
				    %>
				    <tr>
				    <td colspan="5">계시판글 없음</td>
				    </tr>
				    <%
				}
				%>
				
				</table>
				
				<%
				
				if(id!=null){
				%>
				<div id = noticeButton>
				<input type = "button" value="글쓰기" class="btn" onclick="location.href='write.jsp'">
				</div>
				<%
				}
				%>
				
				<div id = "noticeButton" >
				
				<input type = "text" >
				<input type = "button" value="검색">
							
				
				
				</div>
					
					<%
				if(count > 0){
					//전체 페이지수 구하기 
					//예)  글 20개 -> 한페이지당 보여줄 글수 10개 => 2개의 페이지
					//예)  글 25개 -> 한페이지당 보여줄 글수 10개 => 3개의 페이지
				//전체 페이지수 = 
				//전체글수/한페이지에 보여줄 글수+(전체글수를 한페이지에 보여줄 글수로 나눈 나머지값)
					int pageCount = count/pageSize + (count%pageSize==0?0:1);
				
				//한화면에 보여질 페이지 수 설정
					int pageBlock = 2;
				
				//시작 페이지 번호 구하기
				// 1~10 => 1    11~20 => 11  21~30 => 21
				//( (아래쪾에 클릭한 페이지번호 / 한화면에 보여줄 페이지수) - 
				//	(아래쪽에 클릭한 페이지 번호를 한화면에 보여줄 페이지수로 나눈 나머지값) )
				// * 하나의 화면에 보여줄 페이지수 + 1
				int startPage = ((currentPage/pageBlock)-(currentPage%pageBlock==0?1:0)) * pageBlock + 1;
				
				//끝페이지 번호 구하기 
				// 1~10 => 10   11~20 =>20  21~30 => 30
				//시작페이지번호 + 현재블럭(한화면)에 보여줄 페이지수 - 1
				int endPage = startPage + pageBlock - 1;
				
				//끝페이지 번호가 전체페이지수보다 클때...
				if(endPage > pageCount){
					//끝페이지 번호를 전체페이지수로 저장
					endPage = pageCount;
				}
				//[이전] 시작페이지번호가 한화면에 보여줄 페이지수보다 클때..
				if(startPage > pageBlock){
				%>
					<a href="notice.jsp?pageNum=<%=startPage-pageBlock%>">[이전]</a>
				<%
				}
				// [1] [2]...... 페이지번호 나타내기
				for(int i=startPage; i<=endPage; i++){
				%>
					<a href="notice.jsp?pageNum=<%=i%>">[<%=i%>]</a>
				<%	
				}
				//[다음] 끝페이지 번호가 전체페이지 수보다 작을떄..
				if(endPage < pageCount){
				%>	
					<a href="notice.jsp?pageNum=<%=startPage+pageBlock%>">[다음]</a>		
				<%
				}
			}
			%>
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
					<img src="../images/yogurt.jpg" alt="">
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
    

    	
			