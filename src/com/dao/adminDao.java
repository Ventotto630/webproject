package com.dao;

import com.model.Administrators;
import com.model.Department;
import com.model.Reservation_public;
import com.utils.SM4;
import org.bouncycastle.util.encoders.Hex;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class adminDao implements Basedao{
    //��ѯ����Ա(���ڵ�¼)
    public Administrators findByUsername(String username)throws Exception{
        String sql="SELECT * FROM administrators WHERE username=?";
        Administrators admin=new Administrators();
        try(
                Connection conn=getConnection();
                PreparedStatement pstmt=conn.prepareStatement(sql)
        ){
            pstmt.setString(1,username);
            try(ResultSet rst=pstmt.executeQuery()){
                if(rst.next()){
                    admin.setAdminID(rst.getString("AdminID"));
                    admin.setName(rst.getString("name"));
                    admin.setUsername(rst.getString("username"));
                    admin.setPassword(rst.getString("password"));
                    admin.setDepartmentID(rst.getString("departmentID"));
                    admin.setPhone(rst.getString("phone"));
                    admin.setRole(rst.getString("role"));
                    admin.setSocial(rst.getString("social"));
                    admin.setPub(rst.getString("pub"));
                    admin.setPtime(rst.getString("ptime"));
                }
            }
        }catch(SQLException se){
            return null;
        }
        return admin;
    }
    //��ӹ���Ա
    public boolean addAdmin(Administrators admin) throws DaoException{
        String sql ="insert into administrators values(?,?,?,?,?,?,?,?,?,?)";
        try(Connection dbconn = getConnection();
            PreparedStatement pstmt = dbconn.prepareStatement(sql)){
            pstmt.setString(1,admin.getAdminID());
            pstmt.setString(2,admin.getName());
            pstmt.setString(3,admin.getUsername());
            pstmt.setString(4,admin.getPassword());
            pstmt.setString(5,admin.getDepartmentID());
            //�Ե绰���м���
            String phone = admin.getPhone();
            try {
                // ����ԭʼ����
                byte[] input = phone.getBytes();

                // ������Կ
                String keyHex = "0123456789ABCDEF0123456789ABCDEF";
                byte[] keyData = Hex.decode(keyHex);
                SecretKey key = new SecretKeySpec(keyData, "SM4");

                // �����ʼ������IV��
                String ivHex = "00000000000000000000000000000000";
                byte[] ivData = Hex.decode(ivHex);

                // ����
                SM4 sm4 = new SM4();
                byte[] encrypted = sm4.encrypt(input, key, ivData);
                phone = Hex.toHexString(encrypted);
            }catch (Exception e) {
                System.err.println("Error: " + e.getMessage());
            }

            pstmt.setString(6,phone);
            pstmt.setString(7,admin.getRole());
            pstmt.setString(8,admin.getSocial());
            pstmt.setString(9,admin.getPub());
            pstmt.setString(10,admin.getPtime());
            pstmt.executeUpdate();
            return true;
        }catch (SQLException ne){
            ne.printStackTrace();
            return false;
        }
    }
    //ɾ������Ա
    public boolean deleteAdmin(String id){
        String sql="DELETE FROM administrators WHERE adminid=?";
        try(
                Connection conn=getConnection();
                PreparedStatement pstmt=conn.prepareStatement(sql);
        ){
            pstmt.setString(1,id);
            pstmt.executeUpdate();
            return true;
        }catch(SQLException se){
            se.printStackTrace();
            return false;
        }
    }
    //�������ֲ��ҹ���Ա�����еģ�
    public ArrayList<Administrators> findByFuzzyName(String name)throws Exception{
        String sql="SELECT * FROM administrators WHERE name LIKE ? ORDER BY adminid";
        ArrayList<Administrators>adminList=new ArrayList<Administrators>();
        try(
                Connection conn=getConnection();
                PreparedStatement pstmt=conn.prepareStatement(sql)
        ){
            pstmt.setString(1,"%"+name+"%");
            try(ResultSet rst=pstmt.executeQuery()){
                while(rst.next()){
                    Administrators admin=new Administrators();
                    admin.setAdminID(rst.getString("adminid"));
                    admin.setName(rst.getString("name"));
                    admin.setUsername(rst.getString("username"));
                    admin.setPassword(rst.getString("password"));
                    admin.setDepartmentID(rst.getString("departmentid"));

                    //�Ե绰�������sm4����
                    String phonenumber=rst.getString("phone");
                    StringBuffer phonenumber_gai=new StringBuffer();
                    try{
                        // ������Կ
                        String keyHex = "0123456789ABCDEF0123456789ABCDEF";
                        byte[] keyData = Hex.decode(keyHex);
                        SecretKey key = new SecretKeySpec(keyData, "SM4");

                        // �����ʼ������IV��
                        String ivHex = "00000000000000000000000000000000";
                        byte[] ivData = Hex.decode(ivHex);

                        byte[] encryptedFromHex = Hex.decode(phonenumber);
                        SM4 sm4 = new SM4();
                        // ����
                        byte[] decrypted = sm4.decrypt(encryptedFromHex, key, ivData);
                        phonenumber = new String(decrypted);
                        phonenumber_gai.append(phonenumber.substring(0,3));
                        phonenumber_gai.append("****");
                        phonenumber_gai.append(phonenumber.substring(7,11));
                    }catch (Exception e) {
                        System.err.println("Error: " + e.getMessage());
                    }
                    admin.setPhone(phonenumber_gai.toString());

                    admin.setRole(rst.getString("role"));
                    admin.setSocial(rst.getString("social"));
                    admin.setPub(rst.getString("pub"));
                    admin.setPtime(rst.getString("ptime"));
                    adminList.add(admin);
                }
            }
        }catch(SQLException se){
            return null;
        }
        return adminList;
    }
    //�������й���Ա
    public ArrayList<Administrators>findAllAdmin()throws Exception{
        ArrayList<Administrators>adminList= new ArrayList<>();
        String sql="SELECT * FROM administrators ORDER BY adminid";
        try(
                Connection conn=getConnection();
                PreparedStatement pstmt=conn.prepareStatement(sql);
                ResultSet rst=pstmt.executeQuery()
        ){
            while(rst.next()){
                Administrators admin=new Administrators();
                admin.setAdminID(rst.getString("adminid"));
                admin.setName(rst.getString("name"));
                admin.setUsername(rst.getString("username"));
                admin.setPassword(rst.getString("password"));
                admin.setDepartmentID(rst.getString("departmentid"));

                //�Ե绰�������sm4����
                String phonenumber=rst.getString("phone");
                StringBuffer phonenumber_gai=new StringBuffer();
                try{
                    // ������Կ
                    String keyHex = "0123456789ABCDEF0123456789ABCDEF";
                    byte[] keyData = Hex.decode(keyHex);
                    SecretKey key = new SecretKeySpec(keyData, "SM4");

                    // �����ʼ������IV��
                    String ivHex = "00000000000000000000000000000000";
                    byte[] ivData = Hex.decode(ivHex);

                    byte[] encryptedFromHex = Hex.decode(phonenumber);
                    SM4 sm4 = new SM4();
                    // ����
                    byte[] decrypted = sm4.decrypt(encryptedFromHex, key, ivData);
                    phonenumber = new String(decrypted);
                    phonenumber_gai.append(phonenumber.substring(0,3));
                    phonenumber_gai.append("****");
                    phonenumber_gai.append(phonenumber.substring(7,11));
                }catch (Exception e) {
                    System.err.println("Error: " + e.getMessage());
                }
                admin.setPhone(phonenumber_gai.toString());

                admin.setRole(rst.getString("role"));
                admin.setSocial(rst.getString("social"));
                admin.setPub(rst.getString("pub"));
                admin.setPtime(rst.getString("ptime"));
                adminList.add(admin);
            }
            return adminList;
        }catch(SQLException se){
            return null;
        }
    }
    //����id���ҹ���Ա�������޸ĺ�ɾ����
    public Administrators findById(String adminid)throws Exception{
        String sql="SELECT * FROM administrators WHERE adminid=?";
        Administrators admin=new Administrators();
        try(
                Connection conn=getConnection();
                PreparedStatement pstmt=conn.prepareStatement(sql)
        ){
            pstmt.setString(1,adminid);
            try(ResultSet rst=pstmt.executeQuery()){
                if(rst.next()){
                    admin.setAdminID(rst.getString("AdminID"));
                    admin.setName(rst.getString("name"));
                    admin.setUsername(rst.getString("username"));
                    admin.setPassword(rst.getString("password"));
                    admin.setDepartmentID(rst.getString("departmentID"));

                    //�Ե绰�������sm4����
                    String phonenumber=rst.getString("phone");
                    StringBuffer phonenumber_gai=new StringBuffer();
                    try{
                        // ������Կ
                        String keyHex = "0123456789ABCDEF0123456789ABCDEF";
                        byte[] keyData = Hex.decode(keyHex);
                        SecretKey key = new SecretKeySpec(keyData, "SM4");

                        // �����ʼ������IV��
                        String ivHex = "00000000000000000000000000000000";
                        byte[] ivData = Hex.decode(ivHex);

                        byte[] encryptedFromHex = Hex.decode(phonenumber);
                        SM4 sm4 = new SM4();
                        // ����
                        byte[] decrypted = sm4.decrypt(encryptedFromHex, key, ivData);
                        phonenumber = new String(decrypted);
                        phonenumber_gai.append(phonenumber.substring(0,11));
                    }catch (Exception e) {
                        System.err.println("Error: " + e.getMessage());
                    }
                    admin.setPhone(phonenumber_gai.toString());

                    admin.setRole(rst.getString("role"));
                    admin.setSocial(rst.getString("social"));
                    admin.setPub(rst.getString("pub"));
                }
            }
        }catch(SQLException se){
            return null;
        }
        return admin;
    }
    //�޸Ĺ���Ա
    public boolean modifyAdmin(Administrators admin){
        String sql="UPDATE administrators SET name=?," +
                "username=?," +
                "departmentid=?,phone=?,role=?" +
                "WHERE adminid=?;";
        try(
                Connection conn=getConnection();
                PreparedStatement pstmt=conn.prepareStatement(sql)
        ){
            pstmt.setString(1,admin.getName());
            pstmt.setString(2,admin.getUsername());
            pstmt.setString(3,admin.getDepartmentID());
            //�Ե绰���м���
            String phone = admin.getPhone();
            try {
                // ����ԭʼ����
                byte[] input = phone.getBytes();

                // ������Կ
                String keyHex = "0123456789ABCDEF0123456789ABCDEF";
                byte[] keyData = Hex.decode(keyHex);
                SecretKey key = new SecretKeySpec(keyData, "SM4");

                // �����ʼ������IV��
                String ivHex = "00000000000000000000000000000000";
                byte[] ivData = Hex.decode(ivHex);

                // ����
                SM4 sm4 = new SM4();
                byte[] encrypted = sm4.encrypt(input, key, ivData);
                phone = Hex.toHexString(encrypted);
            }catch (Exception e) {
                System.err.println("Error: " + e.getMessage());
            }
            pstmt.setString(4,phone);
            pstmt.setString(5,admin.getRole());
            pstmt.setString(6,admin.getAdminID());
            pstmt.executeUpdate();
            return true;
        }catch(SQLException se){
            se.printStackTrace();
            return false;
        }
    }
    public boolean changepass(String password,String adminid){
        LocalDate date = LocalDate.now(); // get the current date
        String sql="UPDATE administrators SET password=?,ptime=? WHERE adminid=?;";
        try(
                Connection conn=getConnection();
                PreparedStatement pstmt=conn.prepareStatement(sql)
        ){
            pstmt.setString(1,password);
            pstmt.setString(2, String.valueOf(date));
            pstmt.setString(3,adminid);
            pstmt.executeUpdate();
            return true;
        }catch(SQLException se){
            se.printStackTrace();
            return false;
        }
    }
    //��������Բ��Ź���Ա�ģ�ѧУ����Ա�����Ź���Ա��

    //��Ӳ��Ź���Ա����ӹ���Աһ������������ʱ����Ա���Ͳ��ܸģ�
    //ɾ�����Ź���ԱҲ��ɾ������Աһ��������servlet�������ж�ɾ����id�Ƿ�Ϊ���Ź���Ա��

    //�������ֲ��ҹ���Ա��ֻ�в��Ź���Ա��
    public ArrayList<Administrators> findDByFuzzyName(String name)throws Exception{
        String sql="SELECT * FROM administrators WHERE name LIKE ? AND role='���Ź���Ա' ORDER BY adminid";
        ArrayList<Administrators>adminList=new ArrayList<Administrators>();
        try(
                Connection conn=getConnection();
                PreparedStatement pstmt=conn.prepareStatement(sql)
        ){
            pstmt.setString(1,"%"+name+"%");
            try(ResultSet rst=pstmt.executeQuery()){
                while(rst.next()){
                    Administrators admin=new Administrators();
                    admin.setAdminID(rst.getString("adminid"));
                    admin.setName(rst.getString("name"));
                    admin.setUsername(rst.getString("username"));
                    admin.setPassword(rst.getString("password"));
                    admin.setDepartmentID(rst.getString("departmentid"));
                    //�Ե绰�������sm4����
                    String phonenumber=rst.getString("phone");
                    StringBuffer phonenumber_gai=new StringBuffer();
                    try{
                        // ������Կ
                        String keyHex = "0123456789ABCDEF0123456789ABCDEF";
                        byte[] keyData = Hex.decode(keyHex);
                        SecretKey key = new SecretKeySpec(keyData, "SM4");

                        // �����ʼ������IV��
                        String ivHex = "00000000000000000000000000000000";
                        byte[] ivData = Hex.decode(ivHex);

                        byte[] encryptedFromHex = Hex.decode(phonenumber);
                        SM4 sm4 = new SM4();
                        // ����
                        byte[] decrypted = sm4.decrypt(encryptedFromHex, key, ivData);
                        phonenumber = new String(decrypted);
                        phonenumber_gai.append(phonenumber.substring(0,3));
                        phonenumber_gai.append("****");
                        phonenumber_gai.append(phonenumber.substring(7,11));
                    }catch (Exception e) {
                        System.err.println("Error: " + e.getMessage());
                    }
                    admin.setPhone(phonenumber_gai.toString());
                    admin.setRole(rst.getString("role"));
                    admin.setSocial(rst.getString("social"));
                    admin.setPub(rst.getString("pub"));
                    admin.setPtime(rst.getString("ptime"));
                    adminList.add(admin);
                }
            }
        }catch(SQLException se){
            return null;
        }
        return adminList;
    }

    //�������в��Ź���Ա
    public ArrayList<Administrators>findAllDAdmin()throws Exception{
        ArrayList<Administrators>adminList= new ArrayList<>();
        String sql="SELECT * FROM administrators WHERE role='���Ź���Ա' ORDER BY adminid";
        try(
                Connection conn=getConnection();
                PreparedStatement pstmt=conn.prepareStatement(sql);
                ResultSet rst=pstmt.executeQuery()
        ){
            while(rst.next()){
                Administrators admin=new Administrators();
                admin.setAdminID(rst.getString("adminid"));
                admin.setName(rst.getString("name"));
                admin.setUsername(rst.getString("username"));
                admin.setPassword(rst.getString("password"));
                admin.setDepartmentID(rst.getString("departmentid"));
                //�Ե绰�������sm4����
                String phonenumber=rst.getString("phone");
                StringBuffer phonenumber_gai=new StringBuffer();
                try{
                    // ������Կ
                    String keyHex = "0123456789ABCDEF0123456789ABCDEF";
                    byte[] keyData = Hex.decode(keyHex);
                    SecretKey key = new SecretKeySpec(keyData, "SM4");

                    // �����ʼ������IV��
                    String ivHex = "00000000000000000000000000000000";
                    byte[] ivData = Hex.decode(ivHex);

                    byte[] encryptedFromHex = Hex.decode(phonenumber);
                    SM4 sm4 = new SM4();
                    // ����
                    byte[] decrypted = sm4.decrypt(encryptedFromHex, key, ivData);
                    phonenumber = new String(decrypted);
                    phonenumber_gai.append(phonenumber.substring(0,3));
                    phonenumber_gai.append("****");
                    phonenumber_gai.append(phonenumber.substring(7,11));
                }catch (Exception e) {
                    System.err.println("Error: " + e.getMessage());
                }
                admin.setPhone(phonenumber_gai.toString());
                admin.setRole(rst.getString("role"));
                admin.setSocial(rst.getString("social"));
                admin.setPub(rst.getString("pub"));
                admin.setPtime(rst.getString("ptime"));
                adminList.add(admin);
            }
            return adminList;
        }catch(SQLException se){
            return null;
        }
    }
    //���Ź���Ա��Ȩ�����ԤԼ
    public boolean authSocialAdmin(String adminID){
        String sql="UPDATE administrators SET social='1' WHERE adminid=?;";
        try(
                Connection conn=getConnection();
                PreparedStatement pstmt=conn.prepareStatement(sql)
        ){
            pstmt.setString(1,adminID);
            pstmt.executeUpdate();
            return true;
        }catch(SQLException se){
            se.printStackTrace();
            return false;
        }
    }
    //���Ź���Ա��Ȩ������ԤԼ���������Բ鿴�Լ����ţ������Բ鿴���У�
    public boolean authPubAdmin(String adminID){
        String sql="UPDATE administrators SET pub='1' WHERE adminid=?;";
        try(
                Connection conn=getConnection();
                PreparedStatement pstmt=conn.prepareStatement(sql)
        ){
            pstmt.setString(1,adminID);
            pstmt.executeUpdate();
            return true;
        }catch(SQLException se){
            se.printStackTrace();
            return false;
        }
    }
    //���Ź���Ա��Ȩ��ȡ�����ԤԼ
    public boolean noauthSocialAdmin(String adminID){
        String sql="UPDATE administrators SET social=null WHERE adminid=?;";
        try(
                Connection conn=getConnection();
                PreparedStatement pstmt=conn.prepareStatement(sql)
        ){
            pstmt.setString(1,adminID);
            pstmt.executeUpdate();
            return true;
        }catch(SQLException se){
            se.printStackTrace();
            return false;
        }
    }
    //���Ź���Ա��Ȩ��ȡ������ԤԼ���������Բ鿴�Լ����ţ������Բ鿴���У�
    public boolean noauthPubAdmin(String adminID){
        String sql="UPDATE administrators SET pub=null WHERE adminid=?;";
        try(
                Connection conn=getConnection();
                PreparedStatement pstmt=conn.prepareStatement(sql)
        ){
            pstmt.setString(1,adminID);
            pstmt.executeUpdate();
            return true;
        }catch(SQLException se){
            se.printStackTrace();
            return false;
        }
    }
}
