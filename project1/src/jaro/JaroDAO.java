package jaro;

import java.sql.Connection; 
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;




//DAO
//DB에 관련한 모든 작업 하는 클래스 
public class JaroDAO {
		
	Connection con = null;
	PreparedStatement pstmt  = null;
	ResultSet rs = null;		
	String sql = "";	
	
	
	//jspbeginner데이터베이스와 연결을 맺는 메소드
	private Connection getConnection() throws Exception {		
		Connection con = null;
		Context init = new InitialContext();
		//커넥션풀 얻기
		DataSource ds = 
				(DataSource)init.lookup("java:comp/env/jdbc/jspbeginner");
		//커넥션풀로 부터 커넥션객체(DB와 미리 연결 되어 있는 접속을 알리는 객체) 빌려오기
		con = ds.getConnection();
		
		return con;
	}	
	
	//삭제할 글번호와 삭제할글의 비밀번호를 매개변수로 전달 받아.. 글을 DELETE삭제 하는 메소드
	public int deleteJaro(int num, String passwd){
		
		int check = 0; //삭제 성공,삭제실패 판단값 1또는 0저장할 변수
		
		try{
			//DB연결
			con = getConnection();
			//매개변수로 전달 받은 삭제할 글번호에 해당하는 비밀번호 검색
			sql = "select passwd from jaro where num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			//검색
			rs = pstmt.executeQuery();
			
			if(rs.next()){//검색한 글이 존재 하면				
				if(passwd.equals(rs.getString("passwd"))){
					check = 1;
					//매개변수로 전달 받은 글번호에 해당 하는 글삭제 
					sql = "delete from jaro where num=?";
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, num);
					//DELETE실행
					pstmt.executeUpdate();
				}else{//입력한 비밀번호가 DB에 존재 하지 않으면
					check = 0;
				}
			}	
		}catch(Exception err){
			err.printStackTrace();
		}finally {
			//자원해제
			if(rs!=null)try{rs.close();}catch(Exception err){err.printStackTrace();}
			if(pstmt!=null)try{pstmt.close();}catch(Exception err){err.printStackTrace();}
			if(con!=null)try{con.close();}catch(Exception err){err.printStackTrace();}	
		}
		return check; //비밀번호 일치 유무 1또는 0을 리턴 
	}
	
	
	//글번호를 매개변수로 전달 받아.. 글번호에 해당하는 하나의 글 검색해서 반환
	public JaroBean getJaro(int num){//content.jsp에서 호출 한 메소드 
		
		JaroBean jaroBean = null;
		
		try {
			//커넥션풀로 부터 커넥션객체 얻기(DB와 미리 연결을 맺은 Connection객체 얻기)
			con = getConnection();
			
			//매개변수로 전달 받은 글번호에 해당되는 글 검색 SQL문
			sql = "select * from jaro where num=?";
			
			//select구문을 실행할 객체 얻기
			pstmt = con.prepareStatement(sql);
			//?에 대응 되는 글번호 설정
			pstmt.setInt(1, num);
			//select 실행후 검색된 하나의 글정보를 ResultSet임시저장소에 저장 하여 반환 
			rs = pstmt.executeQuery();
			
			if(rs.next()){//검색된 글이 존재 하면
				
				jaroBean = new JaroBean();//검색한 정보를 rs에서 꺼내와서 저장할 용도
				//setter메소드를 활용 해서 변수에 검색한 값들을 저장
				jaroBean.setContent(rs.getString("content"));
				jaroBean.setDate(rs.getTimestamp("date"));
				jaroBean.setFile(rs.getString("file"));
				jaroBean.setIp(rs.getString("ip"));
				jaroBean.setName(rs.getString("name"));
				jaroBean.setNum(rs.getInt("num"));
				jaroBean.setPasswd(rs.getString("passwd"));
				jaroBean.setRe_lev(rs.getInt("re_lev"));
				jaroBean.setRe_ref(rs.getInt("re_ref"));
				jaroBean.setRe_seq(rs.getInt("re_seq"));
				jaroBean.setReadcount(rs.getInt("readcount"));
				jaroBean.setSubject(rs.getString("subject"));
			}//if끝
		
		} catch (Exception e) {		
			e.printStackTrace();
		} finally {
			//자원해제
			if(rs!=null)try{rs.close();}catch(Exception err){err.printStackTrace();}
			if(pstmt!=null)try{pstmt.close();}catch(Exception err){err.printStackTrace();}
			if(con!=null)try{con.close();}catch(Exception err){err.printStackTrace();}
		}
			
		return jaroBean;//BoardBean객체 리턴
		
	}//메소드 끝
	
	
	//글번호를 매개변수로 전달 받아.. 글번호에 해당되는 글의 조회수 1증가 시키는 메소드
	public void updateReadCount(int num){ //content.jsp에서 호출 하는 메소드 
		
		try {
			//커넥션풀로 부터 커넥션객체 얻기(DB와 미리 연결을 맺은 Connection객체 얻기)
			con = getConnection();
			
			sql = "update jaro set readcount=readcount+1 where num=?";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, num);
			
			pstmt.executeUpdate();
		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//자원해제
			if(rs!=null)try{rs.close();}catch(Exception err){err.printStackTrace();}
			if(pstmt!=null)try{pstmt.close();}catch(Exception err){err.printStackTrace();}
			if(con!=null)try{con.close();}catch(Exception err){err.printStackTrace();}
		}
			
	}
	
	
	
	
	//각페이지 마다 맨위에 첫번째로 보여질 시작글번호, 한페이지당 보여줄 글개수를 매개변수로 전달 받아.
	//SELECT검색한 결과를 ArrayList에 저장후 리턴 하는 메소드 
	public List<JaroBean>getJaroList(int startRow,int pageSize){
		
		List<JaroBean> JaroList = new ArrayList<JaroBean>();
		
		try{
			//Connection객체 얻기 
			con = getConnection();
			//SELECT구문 만들기 
			sql = "select * from jaro order by re_ref desc, re_seq asc limit ?,?";
			//SELECT구문 실행할 PreparedStatement실행 객체 얻기
			pstmt = con.prepareStatement(sql);
			//?값 설정
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, pageSize);
			//SELECT구문 실행후 검색 한 결과 받기
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				JaroBean jBean = new JaroBean();
				//rs => BoardBean객체의 각변수에 저장
				jBean.setContent(rs.getString("content"));
				jBean.setDate(rs.getTimestamp("date"));
				jBean.setFile(rs.getString("file"));
				jBean.setIp(rs.getString("ip"));
				jBean.setName(rs.getString("name"));
				jBean.setNum(rs.getInt("num"));
				jBean.setPasswd(rs.getString("passwd"));
				jBean.setRe_lev(rs.getInt("re_lev"));
				jBean.setRe_ref(rs.getInt("re_ref"));
				jBean.setRe_seq(rs.getInt("re_seq"));
				jBean.setReadcount(rs.getInt("readcount"));
				jBean.setSubject(rs.getString("subject"));
					
				//BoardBean객체 => ArrayList배열에 추가
				JaroList.add(jBean);

			}//while문			
			
		}catch(Exception err){
			System.out.println("getJaroList메소드 내부에서 오류 : " + err);
		}finally {
			//자원해제
			if(rs!=null)try{rs.close();}catch(Exception err){err.printStackTrace();}
			if(pstmt!=null)try{pstmt.close();}catch(Exception err){err.printStackTrace();}
			if(con!=null)try{con.close();}catch(Exception err){err.printStackTrace();}
		}
		
		return JaroList; //ArrayList반환
	}
	
	
	
	//게시판에 저장되어 있는 전체 글 개수 검색 메소드
	public int getJaroCount(){
		
		int count = 0;//검색한 전체 글 갯수를 저장할 변수 
		
		try {
			//커넥션풀로 부터 커넥션 객체 얻기 (DB접속정보를 지니고 있는 Connection얻기)
			con = getConnection();
			//sql SELECT-> 전채 글개수 검색
			sql = "select count(*) from jaro";
			//SELECT문 실행 객체 얻기
			pstmt = con.prepareStatement(sql);
			//SELECT문 실행 후 검색한 결과 얻기
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				count = rs.getInt(1);//검색한 글의 개수 
			}
		
		} catch (Exception e) {
			System.out.println("getJaroCount()메소드 오류 : " + e);
		} finally {
			//자원해제
			if(rs!=null)try{rs.close();}catch(Exception err){err.printStackTrace();}
			if(pstmt!=null)try{pstmt.close();}catch(Exception err){err.printStackTrace();}
			if(con!=null)try{con.close();}catch(Exception err){err.printStackTrace();}
		}
		
		return count;
	}
	
	
	//게시판 새글 추가(주글)
	//-> writePro.jsp에서 insertBoard()메소드 호출시.. 
	//   전달한 BoardBean객체를 이용하여 insert SQL문을 만들자.
	public  void insertJaro(JaroBean jBean){		
		
		int num = 0; //추가할 글번호 저장 용도	
		try{
			//커넥션풀로 부터 커넥션 객체 얻기 (DB접속정보를 지니고 있는 Connection얻기)
			con = getConnection();
			//새글 추가시..글번호 구하기
			//-> board테이블에 저장되어 있는 가장 큰글번호 얻기
			//->글이 없을 경우 : 글번호 1 로 지정
			//->글이 있을 경우 : 최근 글번호(번호가 가장큰값) + 1 로 지정
			sql = "select max(num) from jaro";//가장 큰글번호 검색
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()){//검색한 데이터가 존재 하면?
				num = rs.getInt(1) + 1; //글이 있을 경우 최대글번호 + 1
			}else{//검색이 되지 않으면?
				num = 1; //글이 없을 경우 
			}
			
			//insert구문 만들기
			sql = "insert into jaro "
				+ "(num,name,passwd,subject,"
				+ "content,file,re_ref,re_lev,"
				+ "re_seq,readcount,date,ip)"
				+ "values(?,?,?,?,?,?,?,?,?,?,?,?)";
			//insert구문을 실행할 PreparedStatement얻기 
			pstmt = con.prepareStatement(sql);
			//?대응 되는 추가할 값을 설정
			pstmt.setInt(1, num);//추가할 새글번호
			pstmt.setString(2, jBean.getName());//새글을 추가한 작성자 이름
			pstmt.setString(3, jBean.getPasswd());//추가할 새글의 비밀번호
			pstmt.setString(4, jBean.getSubject());//추가할 새글의 글제목
			pstmt.setString(5, jBean.getContent());//추가할 새글의 글내용
			pstmt.setString(6, jBean.getFile());//추가할 새글 데이터중 업로드할 파일명
			pstmt.setInt(7, num);// num 주글번호 기준 == re_ref 그룹번호
			pstmt.setInt(8, 0);//추가할 새글의 들여쓰기 정도값 re_lev
			pstmt.setInt(9, 0);//글 순서 re_seq
			pstmt.setInt(10, 0);//추가할 글의 조회수 readcount 0
			pstmt.setTimestamp(11, jBean.getDate());//글 작성 날짜
			pstmt.setString(12, jBean.getIp());//글쓴사람의 IP주소 
			
			//insert실행
			pstmt.executeUpdate();
			
			
		}catch(Exception e){
			System.out.println("inserJaro메소드 내부에서 오류:" + e);
		}finally {
			//자원해제
			if(rs!=null)try{rs.close();}catch(Exception err){err.printStackTrace();}
			if(pstmt!=null)try{pstmt.close();}catch(Exception err){err.printStackTrace();}
			if(con!=null)try{con.close();}catch(Exception err){err.printStackTrace();}
		}		
	}//insertBoard메소드 닫는 부분
	
	public int updateJaro(JaroBean jBEan){  
	    int check = 0;
	    Connection con = null;
	    PreparedStatement pstmt  = null;
	    ResultSet rs = null;        
	    String sql = "";    
	    
	    try {
            con = getConnection();
            sql ="select passwd from jaro where num=?";
            pstmt=con.prepareStatement(sql);
            pstmt.setInt(1, jBEan.getNum());
            rs= pstmt.executeQuery();
          
            if(rs.next()){
            	if(jBEan.getPasswd().equals(rs.getString("passwd"))){
					check = 1;
					sql = "update jaro set name=?, subject=?, content=?, file =? where num=?";
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, jBEan.getName());
					pstmt.setString(2, jBEan.getSubject());
					pstmt.setString(3, jBEan.getContent());
					pstmt.setString(4, jBEan.getFile());
					pstmt.setInt(5, jBEan.getNum());
					pstmt.executeUpdate();				
				}else{
					check = 0;
				}
			}
		} catch (Exception e) {
			System.out.println("updateBoard메소드 오류 : " + e);
		}finally {
			//자원해제
			if(rs!=null)try{rs.close();}catch(Exception err){err.printStackTrace();}
			if(pstmt!=null)try{pstmt.close();}catch(Exception err){err.printStackTrace();}
			if(con!=null)try{con.close();}catch(Exception err){err.printStackTrace();}				
		}
		return check;
	}
	

 
               
	
	public void reInsertJaro(JaroBean jBean){
	  int num =0;
	  
	  try {
        con =getConnection();
        sql = "select max(num) from jaro";
        pstmt =con.prepareStatement(sql);
        rs = pstmt.executeQuery();
        
        if(rs.next()){
            num=rs.getInt(1)+1;
            
        }else{
            num=1;
        }
        sql="update jaro set re_seq=re_seq+1 where re_ref=? and re_seq>?";
        pstmt=con.prepareStatement(sql);
        pstmt.setInt(1, jBean.getRe_ref());
        pstmt.setInt(2, jBean.getRe_seq());
        pstmt.executeUpdate();
        
        sql="insert into jaro values(?,?,?,?,?,?,?,?,?,?,?,?)";
        pstmt=con.prepareStatement(sql);
        pstmt.setInt(1, num);
        pstmt.setString(2, jBean.getName());
        pstmt.setString(3, jBean.getPasswd());
        pstmt.setString(4, jBean.getSubject());
        pstmt.setString(5, jBean.getContent());
        pstmt.setString(6, jBean.getFile());
        pstmt.setInt(7, jBean.getRe_ref());
        pstmt.setInt(8, jBean.getRe_lev()+1);
        pstmt.setInt(9, jBean.getRe_seq()+1);
        pstmt.setInt(10, 0);
        pstmt.setTimestamp(11, jBean.getDate());
        pstmt.setString(12, jBean.getIp());
        
        pstmt.executeUpdate();
    } catch (Exception e) {
        System.out.println("reInsertJaro()오류"+e);
        e.printStackTrace();
    }
	}

	
}//BoardDAO닫는 부분






