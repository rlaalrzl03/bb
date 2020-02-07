package project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import board.BoardBean;



public class projectDAO {

private Connection getConnection() throws Exception {
        
        Connection con = null;
        Context init = new InitialContext();
        //而ㅻ꽖�뀡�� �뼸湲�
        DataSource ds = 
                (DataSource)init.lookup("java:comp/env/jdbc/jspbeginner");
        //而ㅻ꽖�뀡��濡� 遺��꽣 而ㅻ꽖�뀡媛앹껜(DB�� 誘몃━ �뿰寃� �릺�뼱 �엳�뒗 �젒�냽�쓣 �븣由щ뒗 媛앹껜) 鍮뚮젮�삤湲�
        con = ds.getConnection();
        
        return con;
    }

public projectBean select(String id){
    
    Connection con = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    projectBean projectbean = new projectBean(); 
   
    String sql = "";
    try {
        //1. DB�젒�냽 媛앹껜 �뼸湲� (而ㅻ꽖�뀡��濡� 遺��꽣 而ㅻ꽖�뀡 媛앹껜 �뼸湲�)
        con = getConnection();
        
        //2. SQL(SELECT)留뚮뱾湲� -> 留ㅺ컻蹂��닔濡� �쟾�떖 諛쏅뒗 id�뿉 �빐�떦�븯�뒗 �젅肄붾뱶 寃��깋
        sql = "select * from project where id=?";
        
        //3. SQL�떎�뻾�븷 媛앹껜 PreparedStatement �뼸湲�
        pstmt = con.prepareStatement(sql);
        
        //4. ?�뿉 ���쓳 �릺�뒗 媛� �꽕�젙
        pstmt.setString(1, id);
        
        //5.SELECT援щЦ �떎�뻾�썑 洹멸껐怨쇰�� ResultSet�뿉 ���옣�썑 �뼸湲�
        rs = pstmt.executeQuery();
        
        if(rs.next()){
            
            projectbean.setName(rs.getString("id"));
            projectbean.setAddress(rs.getString("address"));
            projectbean.setEmail(rs.getString("email"));
            projectbean.setName(rs.getString("name"));
            projectbean.setPasswd(rs.getString("passwd"));
            projectbean.setTel(rs.getString("tel"));
            
            
            
        }
            

    }  
        catch (Exception e) {
        System.out.println("select硫붿냼�뱶 �궡遺��뿉�꽌 �삤瑜� : " + e);
    } finally {
        try {
            //�옄�썝�빐�젣
            if(rs != null) rs.close();
            if(pstmt != null) pstmt.close();
            if(con != null) con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    } 
    
    return projectbean;
    
}
    
public int userCheck(String id,String passwd){
    int check = -1; // 1 -> �븘�씠�뵒 留욎쓬, 鍮꾨�踰덊샇 留욎쓬
                    // 0 -> �븘�씠�뵒 留욎쓬, 鍮꾨�踰덊샇 ��由�
                    // -1 -> �븘�씠�뵒 ��由�
    
    Connection con = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    
    String sql = "";
    
    try {
        //1. DB�젒�냽 媛앹껜 �뼸湲� (而ㅻ꽖�뀡��濡� 遺��꽣 而ㅻ꽖�뀡 媛앹껜 �뼸湲�)
        con = getConnection();
        
        //2. SQL(SELECT)留뚮뱾湲� -> 留ㅺ컻蹂��닔濡� �쟾�떖 諛쏅뒗 id�뿉 �빐�떦�븯�뒗 �젅肄붾뱶 寃��깋
        sql = "select * from project where id=?";
        
        //3. SQL�떎�뻾�븷 媛앹껜 PreparedStatement �뼸湲�
        pstmt = con.prepareStatement(sql);
        
        //4. ?�뿉 ���쓳 �릺�뒗 媛� �꽕�젙
        pstmt.setString(1, id);
        
        //5.SELECT援щЦ �떎�뻾�썑 洹멸껐怨쇰�� ResultSet�뿉 ���옣�썑 �뼸湲�
        rs = pstmt.executeQuery();
        
        if(rs.next()){//濡쒓렇�씤 �븯湲� �쐞�빐 �엯�젰�븳 id媛� 議댁옱 �븯怨�....
            
            //濡쒓렇�씤�떆.. �엯�젰�븳 鍮꾨�踰덊샇�� DB�뿉 ���옣�릺�뼱 �엳�뒗 寃��깋�븳 鍮꾨�踰덊샇媛� 媛숈쑝硫�..
            if(passwd.equals(rs.getString("passwd"))){
                
                check = 1; //�븘�씠�뵒 留욎쓬, 鍮꾨�踰덊샇 留욎쓬  �뙋蹂꾧컪 1���옣
                
            }else{//id�뒗 留욎쑝�굹.. 鍮꾨�踰덊샇媛� �떎瑜대떎硫�..
                
                check = 0;
            }
            
        }else{//id媛� DB�뿉 議댁옱 �븯吏� �븡�뒗�떎.
            check = -1;  
        }

    } catch (Exception e) {
        System.out.println("userCheck硫붿냼�뱶 �궡遺��뿉�꽌 �삤瑜� : " + e);
    } finally {
        try {
            //�옄�썝�빐�젣
            if(rs != null) rs.close();
            if(pstmt != null) pstmt.close();
            if(con != null) con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    return check; // 1 �삉�뒗 0 �삉�뒗 -1 瑜� 由ы꽩 //loginPro.jsp濡� 由ы꽩

}//userCheck 硫붿냼�뱶 �떕�뒗 湲고샇

public int idCheck(String id){
    
    int check = 0;
    Connection con = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    String sql="";
    try {
        //1. DB�뿰寃� (而ㅻ꽖�뀡�� 濡쒕��꽣 而ㅻ꽖�뀡 �뼸湲�)
        con = getConnection();
        
        //2. SQL臾� 留뚮뱾湲�(SELECT)-> 留ㅺ컻蹂��닔濡� �쟾�떖諛쏆� �엯�젰�븳 �븘�씠�뵒�뿉 �빐�떦�븯�뒗 �젅肄붾뱶 寃��깋
        sql = "select * from project where id=?";
        
        //3.SQL援щЦ�쓣 �떎�뻾�븷 PreparedStatement媛앹껜 �뼸湲�
        //?湲고샇�뿉 ���쓳�릺�뒗 SELECT媛믪쓣 �젣�쇅�븳 �굹癒몄� �쟾泥� SELECT臾몄옣�쓣
        //PreparedStatement媛앹껜�뿉 �떞�븘 諛섑솚 諛쏄린
        pstmt = con.prepareStatement(sql);
        
        //4. ?湲고샇�뿉 ���쓳 �릺�뒗 媛� �꽕�젙
        pstmt.setString(1, id);
        
        //5.PreparedStatement媛앹껜�쓽 executeQuery()硫붿냼�뱶瑜� �샇異쒗븯�뿬...
        //寃��깋!!!! 寃��깋�썑 洹멸껐怨쇰�� ResultSet�뿉 �떞�븘 諛섑솚
        rs = pstmt.executeQuery();
        
        //6. �슦由ш� �엯�젰�븳 �븘�씠�뵒�뿉 �빐�떦�븯�뒗 �젅肄붾뱶媛� 寃��깋 �릺�뿀�떎硫�?(id媛� 議댁옱 �븯硫�,id媛�以묐났�릺�뿀�떎硫�)
        if(rs.next()){
            check = 1;
        }else{//�엯�젰�븳 �븘�씠�뵒�뿉 �빐�떦�븯�뒗 �쉶�썝�젅肄붾뱶媛� 寃��깋 �릺吏� �븡�쑝硫�?
              //(id媛� 以묐났 �릺吏� �븡�븯�떎硫�)
            check = 0;
        }
    } catch (Exception e) {
        System.out.println("idCheck()硫붿냼�뱶�뿉�꽌 �삤瑜� : " + e);
    } finally {
        
        try {
            //�옄�썝�빐�젣
            if(rs != null) rs.close();
            if(pstmt != null) pstmt.close();
            if(con != null) con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }//finally
    
    //7. check蹂��닔媛� 由ы꽩
    return check; //1�삉�뒗 0�쓣 由ы꽩
    
}//idCheck()硫붿냼�뱶 �떕�뒗 湲고샇 
public void insertproject (projectBean projectbean){
    
    Connection con = null;
    PreparedStatement pstmt = null;
    //insert援щЦ�쓣 留뚮뱾�뼱 ���옣�븷 蹂��닔
    String sql = "";
    
    try {
        //1. DB�뿰寃� (而ㅻ꽖�뀡�� 濡쒕��꽣 而ㅻ꽖�뀡 �뼸湲�)
        con = getConnection();
        
        //2.SQL援щЦ 留뚮뱾湲� (INSERT)
        sql = "insert into project (id,passwd,name,reg_date,email,address,tel)"
                + "values(?,?,?,?,?,?,?)";
            
            //3.SQL援щЦ�쓣 �떎�뻾�븷 PreparedStatement媛앹껜 �뼸湲�
            //?湲고샇�뿉 ���쓳�릺�뒗 insert�븷媛믪쓣 �젣�쇅�븳 �굹癒몄� �쟾泥� insert臾몄옣�쓣 PreparedStatement媛앹껜�뿉 �떞�븘
            //諛섑솚 諛쏄린
            pstmt = con.prepareStatement(sql);
            
            //4. ?湲고샇�뿉 ���쓳�릺�뒗 insert�븷 媛믪쓣  �꽕�젙
            pstmt.setString(1, projectbean.getId());
            pstmt.setString(2, projectbean.getPasswd());
            pstmt.setString(3, projectbean.getName());
            pstmt.setTimestamp(4, projectbean.getReg_date());
            pstmt.setString(5, projectbean.getEmail());
            pstmt.setString(6, projectbean.getAddress());
            pstmt.setString(7, projectbean.getTel());
          
            
        
       
        
        //5. PreparedStatement�뿉 �꽕�젙�맂 insert�쟾泥� 臾몄옣�쓣 DB�뿉 �쟾�넚 �븯�뿬 �떎�뻾
        pstmt.executeUpdate();
        
    } catch (Exception e) {
        System.out.println("insertproject硫붿냼�뱶 �궡遺��뿉�꽌 �삤瑜� : " + e);
    } finally {
        //6. �옄�썝�빐�젣
        
        try {
            if(pstmt != null){//�궗�슜 �븯怨� �엳�쑝硫�
                pstmt.close();
            }
            if(con != null){//�궗�슜 �븯怨� �엳�쑝硫�
                con.close();
            }
        } catch (SQLException e) {
            
            e.printStackTrace();
        }
    }//finally �떕�뒗 遺�遺�    
}//insertMember硫붿냼�뱶 �떕�뒗 遺�遺�    

    public void update(projectBean projectbean){
        
        Connection con = null;
        PreparedStatement pstmt = null;
        //insert援щЦ�쓣 留뚮뱾�뼱 ���옣�븷 蹂��닔
        String sql = "";
        
        try {
            //1. DB�뿰寃� (而ㅻ꽖�뀡�� 濡쒕��꽣 而ㅻ꽖�뀡 �뼸湲�)
            con = getConnection();
            
            //2.SQL援щЦ 留뚮뱾湲� (INSERT)
            sql = "update project set passwd=?,name=?,email=?,address=?,tel=? where id=?";
                //3.SQL援щЦ�쓣 �떎�뻾�븷 PreparedStatement媛앹껜 �뼸湲�
                //?湲고샇�뿉 ���쓳�릺�뒗 insert�븷媛믪쓣 �젣�쇅�븳 �굹癒몄� �쟾泥� insert臾몄옣�쓣 PreparedStatement媛앹껜�뿉 �떞�븘
                //諛섑솚 諛쏄린
                pstmt = con.prepareStatement(sql);
                
                //4. ?湲고샇�뿉 ���쓳�릺�뒗 insert�븷 媛믪쓣  �꽕�젙
                pstmt.setString(1, projectbean.getPasswd());
                pstmt.setString(2, projectbean.getName());
                pstmt.setString(3, projectbean.getEmail());
                pstmt.setString(4, projectbean.getAddress());
                pstmt.setString(5, projectbean.getTel());
                pstmt.setString(6, projectbean.getId());
             
            //5. PreparedStatement�뿉 �꽕�젙�맂 insert�쟾泥� 臾몄옣�쓣 DB�뿉 �쟾�넚 �븯�뿬 �떎�뻾
            pstmt.executeUpdate();
            
        } catch (Exception e) {
            System.out.println("update硫붿냼�뱶 �궡遺��뿉�꽌 �삤瑜� : " + e);
        } finally {
            //6. �옄�썝�빐�젣
            
            try {
                if(pstmt != null){//�궗�슜 �븯怨� �엳�쑝硫�
                    pstmt.close();
                }
                if(con != null){//�궗�슜 �븯怨� �엳�쑝硫�
                    con.close();
                }
            } catch (SQLException e) {
                
                e.printStackTrace();
            }
        }//finally

     
        
       
    }

    public int insertemail(String email){
        int check=0;   
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        projectBean projectbean = new projectBean(); 
       
        String sql = "";
        try {
            //1. DB�젒�냽 媛앹껜 �뼸湲� (而ㅻ꽖�뀡��濡� 遺��꽣 而ㅻ꽖�뀡 媛앹껜 �뼸湲�)
            con = getConnection();
            
            //2. SQL(SELECT)留뚮뱾湲� -> 留ㅺ컻蹂��닔濡� �쟾�떖 諛쏅뒗 id�뿉 �빐�떦�븯�뒗 �젅肄붾뱶 寃��깋
            sql = "select * from project where email= ?";
            
            //3. SQL�떎�뻾�븷 媛앹껜 PreparedStatement �뼸湲�
            pstmt = con.prepareStatement(sql);
            
            //4. ?�뿉 ���쓳 �릺�뒗 媛� �꽕�젙
            pstmt.setString(1, email);
            
            //5.SELECT援щЦ �떎�뻾�썑 洹멸껐怨쇰�� ResultSet�뿉 ���옣�썑 �뼸湲�
             rs = pstmt.executeQuery();
            
            if(rs.next()){
                check=1;
            }else{
                check =0;
            }
                

        }  
            catch (Exception e) {
            System.out.println("select硫붿냼�뱶 �궡遺��뿉�꽌 �삤瑜� : " + e);
        } finally {
            try {
                //�옄�썝�빐�젣
                if(rs != null) rs.close();
                if(pstmt != null) pstmt.close();
                if(con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            
        } 
        
        return check;
        
    }
    
    
    public int emailCheck(String email){
        int check=0;   
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
       
        String sql = "";
        try {
            //1. DB�젒�냽 媛앹껜 �뼸湲� (而ㅻ꽖�뀡��濡� 遺��꽣 而ㅻ꽖�뀡 媛앹껜 �뼸湲�)
            con = getConnection();
            
            //2. SQL(SELECT)留뚮뱾湲� -> 留ㅺ컻蹂��닔濡� �쟾�떖 諛쏅뒗 id�뿉 �빐�떦�븯�뒗 �젅肄붾뱶 寃��깋
            sql = "select * from project where email= ?";
            
            //3. SQL�떎�뻾�븷 媛앹껜 PreparedStatement �뼸湲�
            pstmt = con.prepareStatement(sql);
            
            //4. ?�뿉 ���쓳 �릺�뒗 媛� �꽕�젙
            pstmt.setString(1, email);
            
            //5.SELECT援щЦ �떎�뻾�썑 洹멸껐怨쇰�� ResultSet�뿉 ���옣�썑 �뼸湲�
             rs = pstmt.executeQuery();
            
            if(rs.next()){
                check=1;
            }else{
                check =0;
            }
                

        }  
            catch (Exception e) {
            System.out.println("select硫붿냼�뱶 �궡遺��뿉�꽌 �삤瑜� : " + e);
        } finally {
            try {
                //�옄�썝�빐�젣
                if(rs != null) rs.close();
                if(pstmt != null) pstmt.close();
                if(con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            
        } 
        
        return check;
        
    }
    
    public String randomNum() {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 6; i++) {
            int n = (int)(Math.random() * 10);
            sb.append(n);
        }
        return sb.toString();
    }
    
    public int sendEmail(String email, String authNum) {
        String from = "wangani55@naver.com";
        String subject = "硫붿씪�뀒�뒪�듃";
        String content = "[" + authNum + "]";
        
        Properties p = new Properties();

        p.put("mail.smtp.host", "smtp.naver.com"); 

        p.put("mail.smtp.port", "465");
        p.put("mail.smtp.starttls.enable", "true");
        p.put("mail.smtp.auth", "true");
        p.put("mail.smtp.debug", "true");
        p.put("mail.smtp.socketFactory.port", "465");
        p.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        p.put("mail.smtp.socketFactory.fallback", "false");
        

        try {
            Authenticator auth = new SMTPAuthenticator();
            Session ses = Session.getInstance(p, auth);

            ses.setDebug(true);

            MimeMessage msg = new MimeMessage(ses); 
            msg.setSubject(subject); 

            Address fromAddr = new InternetAddress(from);
            msg.setFrom(fromAddr); 

            Address toAddr = new InternetAddress(email);
            msg.addRecipient(Message.RecipientType.TO, toAddr); 

            msg.setContent(content, "text/html;charset=UTF-8"); 

            Transport.send(msg); 
            
            return 1;
            
        } catch (Exception e) {
            System.out.println("sendEmail()硫붿냼�뱶�삤瑜�: " + e);
            return 0;
        }
    }
    
    

}
