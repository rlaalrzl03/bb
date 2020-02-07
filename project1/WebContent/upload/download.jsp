<%@page import="java.io.BufferedInputStream"%>
<%@page import="java.io.BufferedOutputStream"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
   request.setCharacterEncoding("UTF-8");
   String projectName = getServletContext().getRealPath("/test");
	projectName += "\\";
   String fileName = request.getParameter("filename");
 
   
   response.setContentType("Application/x-msdownload");
   
   response.setHeader("Content-Disposition","attachment;filename=" + fileName);
   
   File file = new File(projectName+new String(fileName.getBytes("8859_1"),"UTF-8"));
   
   byte[] data = new byte[1024];
   if(file.isFile()){
      try{
      BufferedInputStream input = new BufferedInputStream(new FileInputStream(file));
      BufferedOutputStream output = new BufferedOutputStream(response.getOutputStream());
      
      int read;
      while((read = input.read(data)) != -1){
         output.write(data,0,read);
      }
      output.flush();
      output.close();
      input.close();
      }
      catch (Exception e){
         e.printStackTrace();
      }
   }
%>