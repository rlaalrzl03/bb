<%@page import="thumb.thumbBean"%>
<%@page import="thumb.thumbDAO"%>
<%@page import="javax.imageio.ImageIO"%>
<%@page import="java.io.File"%>
<%@page import="java.awt.Graphics2D"%>
<%@page import="java.awt.image.BufferedImage"%>
<%@page import="javax.media.jai.JAI"%>
<%@page import="javax.media.jai.RenderedOp"%>
<%@page import="java.awt.image.renderable.ParameterBlock"%>
<%@page import="java.util.Enumeration"%>
<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@page import="jaro.JaroBean"%>
<%@page import="jaro.JaroDAO"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="board.BoardDAO"%>
<%@page import="board.BoardBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>writePro.jsp</h1>
	<%
	String id = (String)session.getAttribute("id");
	
	if(id==null){
	    response.sendRedirect("../member/login.jsp");
	    
	}
	request.setCharacterEncoding("UTF-8"); 
	String imagePath = request.getRealPath("/image");
	String virtual = getServletContext().getRealPath("/image");
	System.out.println(imagePath);
	int size = 1*1024*1024 ;
	String filename="";
	System.out.print(virtual);
	try{
		MultipartRequest multi=	new MultipartRequest(request,
		  					  imagePath,
							  size,
							  "UTF-8",
							new DefaultFileRenamePolicy());
		
		
	/* 	 ParameterBlock pb=new ParameterBlock();
		pb.add(imagePath+"/"+filename);
		RenderedOp rOp=JAI.create("fileload",pb);
		
		BufferedImage bi= rOp.getAsBufferedImage();
		BufferedImage thumb=new BufferedImage(250,200,BufferedImage.TYPE_INT_RGB);
		
		Graphics2D g=thumb.createGraphics();
		g.drawImage(bi,0,0,250,200,null); */
		
		//File file=new File(imagePath+"/sm_"+filename);
		/* ImageIO.write(thumb,"jpg",file); */
	 
		thumbBean tBean = new thumbBean();
		tBean.setContent(multi.getParameter("content"));
		tBean.setPasswd(multi.getParameter("passwd"));
		tBean.setName(multi.getParameter("name"));
		tBean.setSubject(multi.getParameter("subject"));
		tBean.setFile(multi.getFilesystemName("file"));
		tBean.setDate(new Timestamp(System.currentTimeMillis()));
		tBean.setIp(request.getRemoteAddr());
		
		thumbDAO tdao= new thumbDAO();

		tdao.insertThumb(tBean);   
		
		
		
		
	}catch(Exception e){
	  
		e.printStackTrace();
	}
	
	
	response.sendRedirect("Thumbnail.jsp");
	
	
%>
	

 </body>
</html>