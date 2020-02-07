<%@page import="project.projectDAO"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//요청값 얻기
	//1. login.jsp에서 입력한 id password request영역에서 꺼내오기
	request.setCharacterEncoding("UTF-8");

	String id = request.getParameter("id");
	String pw = request.getParameter("passwd");
	
	//2.입력한 ID와 PASSWORD가 DB에 존재 하는지 유무 체크
	projectDAO projectdao = new projectDAO();
	//유무체크를 위한 메소드 호출!
	//check = 1 -> 아이디, 비밀번호 가 DB에 존재 
	//check = 0 -> 아이디 맞음, 비밀번호 틀림
	//check = -1 -> 아이디 틀림 
	int check = projectdao.userCheck(id, pw);
	 
	if(check == 1){//DB에 저장되어 있는  아이디, 비밀번호가  입력한 아이디 , 비밀번호 같을떄..
		//로그인 화면에서 입력한 아이디를 Session내장객체 메모리에 저장
		session.setAttribute("id", id);
		//index.jsp로 이동(재요청 하여 이동)
		response.sendRedirect("../index.jsp");
	}else if(check == 0){//아이디 맞음 , 비밀번호 틀림
%>		
		<script>
			alert("비밀번호 틀림");
			history.go(-1); //이전 페이지로 이동 
		</script>
<%		
	}else{ //check == -1  아이디 틀림 -> 이전페이지(login.jsp)로 이동
%>	
		<script>
			alert("아이디 없음");
			history.go(-1); //이전 페이지로 이동 
		</script>
<%	
	}

%>




