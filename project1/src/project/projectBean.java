package project;

import java.sql.Timestamp;


public class projectBean {
    
    //����-> private, member���̺��� �÷��̸��� �ڷ��� ���� �ϰ�
    private String id;          //���̵�
    private String passwd;      //��й�ȣ
    private String name;        //�̸�
    private Timestamp reg_date; //���Գ�¥
    private int age;            //����
    private String gender;     //����
    private String email;     //�̸���
    private String address;  //�ּ�
    private String tel;      //��ȭ��ȣ
   
        
    //getter,setter�޼ҵ� 
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getPasswd() {
        return passwd;
    }
    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Timestamp getReg_date() {
        return reg_date;
    }
    public void setReg_date(Timestamp reg_date) {
        this.reg_date = reg_date;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getTel() {
        return tel;
    }
    public void setTel(String tel) {
        this.tel = tel;
    }
    

}
