package thumb;

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
public class thumbDAO {
        
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
    public int deleteTuumb(int num, String passwd){
        
        int check = 0; //���� ����,�������� �Ǵܰ� 1�Ǵ� 0������ ����
        
        try{
            //DB����
            con = getConnection();
            //�Ű������� ���� ���� ������ �۹�ȣ�� �ش��ϴ� ��й�ȣ �˻�
            sql = "select passwd from thumb where num=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, num);
            //�˻�
            rs = pstmt.executeQuery();
            
            if(rs.next()){//�˻��� ���� ���� �ϸ�                
                if(passwd.equals(rs.getString("passwd"))){
                    check = 1;
                    //�Ű������� ���� ���� �۹�ȣ�� �ش� �ϴ� �ۻ��� 
                    sql = "delete from thumb where num=?";
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
    public thumbBean getThumb(int num){//content.jsp���� ȣ�� �� �޼ҵ� 
        
        thumbBean thumbbean = null;
        
        try {
            //Ŀ�ؼ�Ǯ�� ���� Ŀ�ؼǰ�ü ���(DB�� �̸� ������ ���� Connection��ü ���)
            con = getConnection();
            
            //�Ű������� ���� ���� �۹�ȣ�� �ش�Ǵ� �� �˻� SQL��
            sql = "select * from thumb where num=?";
            
            //select������ ������ ��ü ���
            pstmt = con.prepareStatement(sql);
            //?�� ���� �Ǵ� �۹�ȣ ����
            pstmt.setInt(1, num);
            //select ������ �˻��� �ϳ��� �������� ResultSet�ӽ�����ҿ� ���� �Ͽ� ��ȯ 
            rs = pstmt.executeQuery();
            
            if(rs.next()){//�˻��� ���� ���� �ϸ�
                
                thumbbean = new thumbBean();//�˻��� ������ rs���� �����ͼ� ������ �뵵
                //setter�޼ҵ带 Ȱ�� �ؼ� ������ �˻��� ������ ����
                thumbbean.setContent(rs.getString("content"));
                thumbbean.setDate(rs.getTimestamp("date"));
                thumbbean.setFile(rs.getString("file"));
                thumbbean.setIp(rs.getString("ip"));
                thumbbean.setName(rs.getString("name"));
                thumbbean.setNum(rs.getInt("num"));
                thumbbean.setPasswd(rs.getString("passwd"));
                thumbbean.setRe_lev(rs.getInt("re_lev"));
                thumbbean.setRe_ref(rs.getInt("re_ref"));
                thumbbean.setRe_seq(rs.getInt("re_seq"));
                thumbbean.setReadcount(rs.getInt("readcount"));
                thumbbean.setSubject(rs.getString("subject"));
               
            }//if��
        
        } catch (Exception e) {     
            e.printStackTrace();
        } finally {
            //�ڿ�����
            if(rs!=null)try{rs.close();}catch(Exception err){err.printStackTrace();}
            if(pstmt!=null)try{pstmt.close();}catch(Exception err){err.printStackTrace();}
            if(con!=null)try{con.close();}catch(Exception err){err.printStackTrace();}
        }
            
        return thumbbean;//BoardBean��ü ����
        
    }//�޼ҵ� ��
    
    
    //�۹�ȣ�� �Ű������� ���� �޾�.. �۹�ȣ�� �ش�Ǵ� ���� ��ȸ�� 1���� ��Ű�� �޼ҵ�
    public void updateReadCount(int num){ //content.jsp���� ȣ�� �ϴ� �޼ҵ� 
        
        try {
            //Ŀ�ؼ�Ǯ�� ���� Ŀ�ؼǰ�ü ���(DB�� �̸� ������ ���� Connection��ü ���)
            con = getConnection();
            
            sql = "update thumb set readcount=readcount+1 where num=?";
            
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
    public List<thumbBean>getThumbList(int startRow,int pageSize){
        
        List<thumbBean> thumbList = new ArrayList<thumbBean>();
        
        try{
            //Connection��ü ��� 
            con = getConnection();
            //SELECT���� ����� 
            sql = "select * from thumb order by num asc limit ?,?";
            //SELECT���� ������ PreparedStatement���� ��ü ���
            pstmt = con.prepareStatement(sql);
            //?�� ����
            pstmt.setInt(1, startRow);
            pstmt.setInt(2, pageSize);
            //SELECT���� ������ �˻� �� ��� �ޱ�
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
                thumbBean tBean = new thumbBean();
                //rs => BoardBean��ü�� �������� ����
                tBean.setContent(rs.getString("content"));
                tBean.setDate(rs.getTimestamp("date"));
                tBean.setFile(rs.getString("file"));
                tBean.setIp(rs.getString("ip"));
                tBean.setName(rs.getString("name"));
                tBean.setNum(rs.getInt("num"));
                tBean.setPasswd(rs.getString("passwd"));
                tBean.setRe_lev(rs.getInt("re_lev"));
                tBean.setRe_ref(rs.getInt("re_ref"));
                tBean.setRe_seq(rs.getInt("re_seq"));
                tBean.setReadcount(rs.getInt("readcount"));
                tBean.setSubject(rs.getString("subject"));
                System.out.println("DAO " + tBean.getFile());
                //BoardBean��ü => ArrayList�迭�� �߰�
                thumbList.add(tBean);

            }//while��           
            
        }catch(Exception err){
            System.out.println("getJaroList�޼ҵ� ���ο��� ���� : " + err);
        }finally {
            //�ڿ�����
            if(rs!=null)try{rs.close();}catch(Exception err){err.printStackTrace();}
            if(pstmt!=null)try{pstmt.close();}catch(Exception err){err.printStackTrace();}
            if(con!=null)try{con.close();}catch(Exception err){err.printStackTrace();}
        }
        
        return thumbList; //ArrayList��ȯ
    }
    
    
    
    //�Խ��ǿ� ����Ǿ� �ִ� ��ü �� ���� �˻� �޼ҵ�
    public int getThumbCount(){
        
        int count = 0;//�˻��� ��ü �� ������ ������ ���� 
        
        try {
            //Ŀ�ؼ�Ǯ�� ���� Ŀ�ؼ� ��ü ��� (DB���������� ���ϰ� �ִ� Connection���)
            con = getConnection();
            //sql SELECT-> ��ä �۰��� �˻�
            sql = "select count(*) from thumb";
            //SELECT�� ���� ��ü ���
            pstmt = con.prepareStatement(sql);
            //SELECT�� ���� �� �˻��� ��� ���
            rs = pstmt.executeQuery();
            
            if(rs.next()){
                count = rs.getInt(1);//�˻��� ���� ���� 
            }
        
        } catch (Exception e) {
            System.out.println("getthumbCount()�޼ҵ� ���� : " + e);
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
    public  void insertThumb(thumbBean tBean){        
        
        int num = 0; //�߰��� �۹�ȣ ���� �뵵    
        try{
            //Ŀ�ؼ�Ǯ�� ���� Ŀ�ؼ� ��ü ��� (DB���������� ���ϰ� �ִ� Connection���)
            con = getConnection();
            //���� �߰���..�۹�ȣ ���ϱ�
            //-> board���̺� ����Ǿ� �ִ� ���� ū�۹�ȣ ���
            //->���� ���� ��� : �۹�ȣ 1 �� ����
            //->���� ���� ��� : �ֱ� �۹�ȣ(��ȣ�� ����ū��) + 1 �� ����
            sql = "select max(num) from thumb";//���� ū�۹�ȣ �˻�
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            
            if(rs.next()){//�˻��� �����Ͱ� ���� �ϸ�?
                num = rs.getInt(1) + 1; //���� ���� ��� �ִ�۹�ȣ + 1
            }else{//�˻��� ���� ������?
                num = 1; //���� ���� ��� 
            }
            
            //insert���� �����
            sql = "insert into thumb "
                + "(num,name,passwd,subject,"
                + "content,file,re_ref,re_lev,"
                + "re_seq,readcount,date,ip)"
                + "values(?,?,?,?,?,?,?,?,?,?,?,?)";
            //insert������ ������ PreparedStatement��� 
            pstmt = con.prepareStatement(sql);
            //?���� �Ǵ� �߰��� ���� ����
            pstmt.setInt(1, num);//�߰��� ���۹�ȣ
            pstmt.setString(2, tBean.getName());//������ �߰��� �ۼ��� �̸�
            pstmt.setString(3, tBean.getPasswd());//�߰��� ������ ��й�ȣ
            pstmt.setString(4, tBean.getSubject());//�߰��� ������ ������
            pstmt.setString(5, tBean.getContent());//�߰��� ������ �۳���
            pstmt.setString(6, tBean.getFile());//�߰��� ���� �������� ���ε��� ���ϸ�
            pstmt.setInt(7, num);// num �ֱ۹�ȣ ���� == re_ref �׷��ȣ
            pstmt.setInt(8, 0);//�߰��� ������ �鿩���� ������ re_lev
            pstmt.setInt(9, 0);//�� ���� re_seq
            pstmt.setInt(10, 0);//�߰��� ���� ��ȸ�� readcount 0
            pstmt.setTimestamp(11, tBean.getDate());//�� �ۼ� ��¥
            pstmt.setString(12, tBean.getIp());//�۾������ IP�ּ� 
            
            //insert����
            pstmt.executeUpdate();
            
            
        }catch(Exception e){
            System.out.println("inserthumb�޼ҵ� ���ο��� ����:" + e);
        }finally {
            //�ڿ�����
            if(rs!=null)try{rs.close();}catch(Exception err){err.printStackTrace();}
            if(pstmt!=null)try{pstmt.close();}catch(Exception err){err.printStackTrace();}
            if(con!=null)try{con.close();}catch(Exception err){err.printStackTrace();}
        }       
    }//insertBoard�޼ҵ� �ݴ� �κ�
    
    public int updateThumb(thumbBean tBEan){  
        int check = 0;
        Connection con = null;
        PreparedStatement pstmt  = null;
        ResultSet rs = null;        
        String sql = "";    
        
        try {
            con = getConnection();
            sql ="select passwd from thumb where num=?";
            pstmt=con.prepareStatement(sql);
            pstmt.setInt(1, tBEan.getNum());
            rs= pstmt.executeQuery();
          
            if(rs.next()){
                if(tBEan.getPasswd().equals(rs.getString("passwd"))){
                    check = 1;
                    sql = "update thumb set name=?, subject=?, content=?, file =? where num=?";
                    pstmt = con.prepareStatement(sql);
                    pstmt.setString(1, tBEan.getName());
                    pstmt.setString(2, tBEan.getSubject());
                    pstmt.setString(3, tBEan.getContent());
                    pstmt.setString(4, tBEan.getFile());
                    pstmt.setInt(5, tBEan.getNum());
                    pstmt.executeUpdate();              
                }else{
                    check = 0;
                }
            }
        } catch (Exception e) {
            System.out.println("updatethumb�޼ҵ� ���� : " + e);
        }finally {
            //�ڿ�����
            if(rs!=null)try{rs.close();}catch(Exception err){err.printStackTrace();}
            if(pstmt!=null)try{pstmt.close();}catch(Exception err){err.printStackTrace();}
            if(con!=null)try{con.close();}catch(Exception err){err.printStackTrace();}              
        }
        return check;
    }
    

 
               
    
    public void reInsertJaro(thumbBean tBean){
      int num =0;
      
      try {
        con =getConnection();
        sql = "select max(num) from thumb";
        pstmt =con.prepareStatement(sql);
        rs = pstmt.executeQuery();
        
        if(rs.next()){
            num=rs.getInt(1)+1;
            
        }else{
            num=1;
        }
        sql="update thumb set re_seq=re_seq+1 where re_ref=? and re_seq>?";
        pstmt=con.prepareStatement(sql);
        pstmt.setInt(1, tBean.getRe_ref());
        pstmt.setInt(2, tBean.getRe_seq());
        pstmt.executeUpdate();
        
        sql="insert into thumb values(?,?,?,?,?,?,?,?,?,?,?,?)";
        pstmt=con.prepareStatement(sql);
        pstmt.setInt(1, num);
        pstmt.setString(2, tBean.getName());
        pstmt.setString(3, tBean.getPasswd());
        pstmt.setString(4, tBean.getSubject());
        pstmt.setString(5, tBean.getContent());
        pstmt.setString(6, tBean.getFile());
        pstmt.setInt(7, tBean.getRe_ref());
        pstmt.setInt(8, tBean.getRe_lev()+1);
        pstmt.setInt(9, tBean.getRe_seq()+1);
        pstmt.setInt(10, 0);
        pstmt.setTimestamp(11, tBean.getDate());
        pstmt.setString(12, tBean.getIp());
        
        pstmt.executeUpdate();
    } catch (Exception e) {
        System.out.println("reInsertthumb()����"+e);
        e.printStackTrace();
    }
    }

    
}//BoardDAO�ݴ� �κ�






