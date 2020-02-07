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
//DB�� ������ ��� �۾� �ϴ� Ŭ���� 
public class JaroDAO {
		
	Connection con = null;
	PreparedStatement pstmt  = null;
	ResultSet rs = null;		
	String sql = "";	
	
	
	//jspbeginner�����ͺ��̽��� ������ �δ� �޼ҵ�
	private Connection getConnection() throws Exception {		
		Connection con = null;
		Context init = new InitialContext();
		//Ŀ�ؼ�Ǯ ���
		DataSource ds = 
				(DataSource)init.lookup("java:comp/env/jdbc/jspbeginner");
		//Ŀ�ؼ�Ǯ�� ���� Ŀ�ؼǰ�ü(DB�� �̸� ���� �Ǿ� �ִ� ������ �˸��� ��ü) ��������
		con = ds.getConnection();
		
		return con;
	}	
	
	//������ �۹�ȣ�� �����ұ��� ��й�ȣ�� �Ű������� ���� �޾�.. ���� DELETE���� �ϴ� �޼ҵ�
	public int deleteJaro(int num, String passwd){
		
		int check = 0; //���� ����,�������� �Ǵܰ� 1�Ǵ� 0������ ����
		
		try{
			//DB����
			con = getConnection();
			//�Ű������� ���� ���� ������ �۹�ȣ�� �ش��ϴ� ��й�ȣ �˻�
			sql = "select passwd from jaro where num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			//�˻�
			rs = pstmt.executeQuery();
			
			if(rs.next()){//�˻��� ���� ���� �ϸ�				
				if(passwd.equals(rs.getString("passwd"))){
					check = 1;
					//�Ű������� ���� ���� �۹�ȣ�� �ش� �ϴ� �ۻ��� 
					sql = "delete from jaro where num=?";
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, num);
					//DELETE����
					pstmt.executeUpdate();
				}else{//�Է��� ��й�ȣ�� DB�� ���� ���� ������
					check = 0;
				}
			}	
		}catch(Exception err){
			err.printStackTrace();
		}finally {
			//�ڿ�����
			if(rs!=null)try{rs.close();}catch(Exception err){err.printStackTrace();}
			if(pstmt!=null)try{pstmt.close();}catch(Exception err){err.printStackTrace();}
			if(con!=null)try{con.close();}catch(Exception err){err.printStackTrace();}	
		}
		return check; //��й�ȣ ��ġ ���� 1�Ǵ� 0�� ���� 
	}
	
	
	//�۹�ȣ�� �Ű������� ���� �޾�.. �۹�ȣ�� �ش��ϴ� �ϳ��� �� �˻��ؼ� ��ȯ
	public JaroBean getJaro(int num){//content.jsp���� ȣ�� �� �޼ҵ� 
		
		JaroBean jaroBean = null;
		
		try {
			//Ŀ�ؼ�Ǯ�� ���� Ŀ�ؼǰ�ü ���(DB�� �̸� ������ ���� Connection��ü ���)
			con = getConnection();
			
			//�Ű������� ���� ���� �۹�ȣ�� �ش�Ǵ� �� �˻� SQL��
			sql = "select * from jaro where num=?";
			
			//select������ ������ ��ü ���
			pstmt = con.prepareStatement(sql);
			//?�� ���� �Ǵ� �۹�ȣ ����
			pstmt.setInt(1, num);
			//select ������ �˻��� �ϳ��� �������� ResultSet�ӽ�����ҿ� ���� �Ͽ� ��ȯ 
			rs = pstmt.executeQuery();
			
			if(rs.next()){//�˻��� ���� ���� �ϸ�
				
				jaroBean = new JaroBean();//�˻��� ������ rs���� �����ͼ� ������ �뵵
				//setter�޼ҵ带 Ȱ�� �ؼ� ������ �˻��� ������ ����
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
			}//if��
		
		} catch (Exception e) {		
			e.printStackTrace();
		} finally {
			//�ڿ�����
			if(rs!=null)try{rs.close();}catch(Exception err){err.printStackTrace();}
			if(pstmt!=null)try{pstmt.close();}catch(Exception err){err.printStackTrace();}
			if(con!=null)try{con.close();}catch(Exception err){err.printStackTrace();}
		}
			
		return jaroBean;//BoardBean��ü ����
		
	}//�޼ҵ� ��
	
	
	//�۹�ȣ�� �Ű������� ���� �޾�.. �۹�ȣ�� �ش�Ǵ� ���� ��ȸ�� 1���� ��Ű�� �޼ҵ�
	public void updateReadCount(int num){ //content.jsp���� ȣ�� �ϴ� �޼ҵ� 
		
		try {
			//Ŀ�ؼ�Ǯ�� ���� Ŀ�ؼǰ�ü ���(DB�� �̸� ������ ���� Connection��ü ���)
			con = getConnection();
			
			sql = "update jaro set readcount=readcount+1 where num=?";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, num);
			
			pstmt.executeUpdate();
		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//�ڿ�����
			if(rs!=null)try{rs.close();}catch(Exception err){err.printStackTrace();}
			if(pstmt!=null)try{pstmt.close();}catch(Exception err){err.printStackTrace();}
			if(con!=null)try{con.close();}catch(Exception err){err.printStackTrace();}
		}
			
	}
	
	
	
	
	//�������� ���� ������ ù��°�� ������ ���۱۹�ȣ, ���������� ������ �۰����� �Ű������� ���� �޾�.
	//SELECT�˻��� ����� ArrayList�� ������ ���� �ϴ� �޼ҵ� 
	public List<JaroBean>getJaroList(int startRow,int pageSize){
		
		List<JaroBean> JaroList = new ArrayList<JaroBean>();
		
		try{
			//Connection��ü ��� 
			con = getConnection();
			//SELECT���� ����� 
			sql = "select * from jaro order by re_ref desc, re_seq asc limit ?,?";
			//SELECT���� ������ PreparedStatement���� ��ü ���
			pstmt = con.prepareStatement(sql);
			//?�� ����
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, pageSize);
			//SELECT���� ������ �˻� �� ��� �ޱ�
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				JaroBean jBean = new JaroBean();
				//rs => BoardBean��ü�� �������� ����
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
					
				//BoardBean��ü => ArrayList�迭�� �߰�
				JaroList.add(jBean);

			}//while��			
			
		}catch(Exception err){
			System.out.println("getJaroList�޼ҵ� ���ο��� ���� : " + err);
		}finally {
			//�ڿ�����
			if(rs!=null)try{rs.close();}catch(Exception err){err.printStackTrace();}
			if(pstmt!=null)try{pstmt.close();}catch(Exception err){err.printStackTrace();}
			if(con!=null)try{con.close();}catch(Exception err){err.printStackTrace();}
		}
		
		return JaroList; //ArrayList��ȯ
	}
	
	
	
	//�Խ��ǿ� ����Ǿ� �ִ� ��ü �� ���� �˻� �޼ҵ�
	public int getJaroCount(){
		
		int count = 0;//�˻��� ��ü �� ������ ������ ���� 
		
		try {
			//Ŀ�ؼ�Ǯ�� ���� Ŀ�ؼ� ��ü ��� (DB���������� ���ϰ� �ִ� Connection���)
			con = getConnection();
			//sql SELECT-> ��ä �۰��� �˻�
			sql = "select count(*) from jaro";
			//SELECT�� ���� ��ü ���
			pstmt = con.prepareStatement(sql);
			//SELECT�� ���� �� �˻��� ��� ���
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				count = rs.getInt(1);//�˻��� ���� ���� 
			}
		
		} catch (Exception e) {
			System.out.println("getJaroCount()�޼ҵ� ���� : " + e);
		} finally {
			//�ڿ�����
			if(rs!=null)try{rs.close();}catch(Exception err){err.printStackTrace();}
			if(pstmt!=null)try{pstmt.close();}catch(Exception err){err.printStackTrace();}
			if(con!=null)try{con.close();}catch(Exception err){err.printStackTrace();}
		}
		
		return count;
	}
	
	
	//�Խ��� ���� �߰�(�ֱ�)
	//-> writePro.jsp���� insertBoard()�޼ҵ� ȣ���.. 
	//   ������ BoardBean��ü�� �̿��Ͽ� insert SQL���� ������.
	public  void insertJaro(JaroBean jBean){		
		
		int num = 0; //�߰��� �۹�ȣ ���� �뵵	
		try{
			//Ŀ�ؼ�Ǯ�� ���� Ŀ�ؼ� ��ü ��� (DB���������� ���ϰ� �ִ� Connection���)
			con = getConnection();
			//���� �߰���..�۹�ȣ ���ϱ�
			//-> board���̺� ����Ǿ� �ִ� ���� ū�۹�ȣ ���
			//->���� ���� ��� : �۹�ȣ 1 �� ����
			//->���� ���� ��� : �ֱ� �۹�ȣ(��ȣ�� ����ū��) + 1 �� ����
			sql = "select max(num) from jaro";//���� ū�۹�ȣ �˻�
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()){//�˻��� �����Ͱ� ���� �ϸ�?
				num = rs.getInt(1) + 1; //���� ���� ��� �ִ�۹�ȣ + 1
			}else{//�˻��� ���� ������?
				num = 1; //���� ���� ��� 
			}
			
			//insert���� �����
			sql = "insert into jaro "
				+ "(num,name,passwd,subject,"
				+ "content,file,re_ref,re_lev,"
				+ "re_seq,readcount,date,ip)"
				+ "values(?,?,?,?,?,?,?,?,?,?,?,?)";
			//insert������ ������ PreparedStatement��� 
			pstmt = con.prepareStatement(sql);
			//?���� �Ǵ� �߰��� ���� ����
			pstmt.setInt(1, num);//�߰��� ���۹�ȣ
			pstmt.setString(2, jBean.getName());//������ �߰��� �ۼ��� �̸�
			pstmt.setString(3, jBean.getPasswd());//�߰��� ������ ��й�ȣ
			pstmt.setString(4, jBean.getSubject());//�߰��� ������ ������
			pstmt.setString(5, jBean.getContent());//�߰��� ������ �۳���
			pstmt.setString(6, jBean.getFile());//�߰��� ���� �������� ���ε��� ���ϸ�
			pstmt.setInt(7, num);// num �ֱ۹�ȣ ���� == re_ref �׷��ȣ
			pstmt.setInt(8, 0);//�߰��� ������ �鿩���� ������ re_lev
			pstmt.setInt(9, 0);//�� ���� re_seq
			pstmt.setInt(10, 0);//�߰��� ���� ��ȸ�� readcount 0
			pstmt.setTimestamp(11, jBean.getDate());//�� �ۼ� ��¥
			pstmt.setString(12, jBean.getIp());//�۾������ IP�ּ� 
			
			//insert����
			pstmt.executeUpdate();
			
			
		}catch(Exception e){
			System.out.println("inserJaro�޼ҵ� ���ο��� ����:" + e);
		}finally {
			//�ڿ�����
			if(rs!=null)try{rs.close();}catch(Exception err){err.printStackTrace();}
			if(pstmt!=null)try{pstmt.close();}catch(Exception err){err.printStackTrace();}
			if(con!=null)try{con.close();}catch(Exception err){err.printStackTrace();}
		}		
	}//insertBoard�޼ҵ� �ݴ� �κ�
	
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
			System.out.println("updateBoard�޼ҵ� ���� : " + e);
		}finally {
			//�ڿ�����
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
        System.out.println("reInsertJaro()����"+e);
        e.printStackTrace();
    }
	}

	
}//BoardDAO�ݴ� �κ�






