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
    //查询管理员(用于登录)
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
    //添加管理员
    public boolean addAdmin(Administrators admin) throws DaoException{
        String sql ="insert into administrators values(?,?,?,?,?,?,?,?,?,?)";
        try(Connection dbconn = getConnection();
            PreparedStatement pstmt = dbconn.prepareStatement(sql)){
            pstmt.setString(1,admin.getAdminID());
            pstmt.setString(2,admin.getName());
            pstmt.setString(3,admin.getUsername());
            pstmt.setString(4,admin.getPassword());
            pstmt.setString(5,admin.getDepartmentID());
            //对电话进行加密
            String phone = admin.getPhone();
            try {
                // 定义原始数据
                byte[] input = phone.getBytes();

                // 生成密钥
                String keyHex = "0123456789ABCDEF0123456789ABCDEF";
                byte[] keyData = Hex.decode(keyHex);
                SecretKey key = new SecretKeySpec(keyData, "SM4");

                // 定义初始向量（IV）
                String ivHex = "00000000000000000000000000000000";
                byte[] ivData = Hex.decode(ivHex);

                // 加密
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
    //删除管理员
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
    //根据名字查找管理员（所有的）
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

                    //对电话号码进行sm4解密
                    String phonenumber=rst.getString("phone");
                    StringBuffer phonenumber_gai=new StringBuffer();
                    try{
                        // 生成密钥
                        String keyHex = "0123456789ABCDEF0123456789ABCDEF";
                        byte[] keyData = Hex.decode(keyHex);
                        SecretKey key = new SecretKeySpec(keyData, "SM4");

                        // 定义初始向量（IV）
                        String ivHex = "00000000000000000000000000000000";
                        byte[] ivData = Hex.decode(ivHex);

                        byte[] encryptedFromHex = Hex.decode(phonenumber);
                        SM4 sm4 = new SM4();
                        // 解密
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
    //查找所有管理员
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

                //对电话号码进行sm4解密
                String phonenumber=rst.getString("phone");
                StringBuffer phonenumber_gai=new StringBuffer();
                try{
                    // 生成密钥
                    String keyHex = "0123456789ABCDEF0123456789ABCDEF";
                    byte[] keyData = Hex.decode(keyHex);
                    SecretKey key = new SecretKeySpec(keyData, "SM4");

                    // 定义初始向量（IV）
                    String ivHex = "00000000000000000000000000000000";
                    byte[] ivData = Hex.decode(ivHex);

                    byte[] encryptedFromHex = Hex.decode(phonenumber);
                    SM4 sm4 = new SM4();
                    // 解密
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
    //根据id查找管理员（用于修改和删除）
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

                    //对电话号码进行sm4解密
                    String phonenumber=rst.getString("phone");
                    StringBuffer phonenumber_gai=new StringBuffer();
                    try{
                        // 生成密钥
                        String keyHex = "0123456789ABCDEF0123456789ABCDEF";
                        byte[] keyData = Hex.decode(keyHex);
                        SecretKey key = new SecretKeySpec(keyData, "SM4");

                        // 定义初始向量（IV）
                        String ivHex = "00000000000000000000000000000000";
                        byte[] ivData = Hex.decode(ivHex);

                        byte[] encryptedFromHex = Hex.decode(phonenumber);
                        SM4 sm4 = new SM4();
                        // 解密
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
    //修改管理员
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
            //对电话进行加密
            String phone = admin.getPhone();
            try {
                // 定义原始数据
                byte[] input = phone.getBytes();

                // 生成密钥
                String keyHex = "0123456789ABCDEF0123456789ABCDEF";
                byte[] keyData = Hex.decode(keyHex);
                SecretKey key = new SecretKeySpec(keyData, "SM4");

                // 定义初始向量（IV）
                String ivHex = "00000000000000000000000000000000";
                byte[] ivData = Hex.decode(ivHex);

                // 加密
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
    //下面是针对部门管理员的（学校管理员管理部门管理员）

    //添加部门管理员和添加管理员一样（就是填表格时管理员类型不能改）
    //删除部门管理员也和删除管理员一样（就是servlet里面先判断删除的id是否为部门管理员）

    //根据名字查找管理员（只有部门管理员）
    public ArrayList<Administrators> findDByFuzzyName(String name)throws Exception{
        String sql="SELECT * FROM administrators WHERE name LIKE ? AND role='部门管理员' ORDER BY adminid";
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
                    //对电话号码进行sm4解密
                    String phonenumber=rst.getString("phone");
                    StringBuffer phonenumber_gai=new StringBuffer();
                    try{
                        // 生成密钥
                        String keyHex = "0123456789ABCDEF0123456789ABCDEF";
                        byte[] keyData = Hex.decode(keyHex);
                        SecretKey key = new SecretKeySpec(keyData, "SM4");

                        // 定义初始向量（IV）
                        String ivHex = "00000000000000000000000000000000";
                        byte[] ivData = Hex.decode(ivHex);

                        byte[] encryptedFromHex = Hex.decode(phonenumber);
                        SM4 sm4 = new SM4();
                        // 解密
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

    //查找所有部门管理员
    public ArrayList<Administrators>findAllDAdmin()throws Exception{
        ArrayList<Administrators>adminList= new ArrayList<>();
        String sql="SELECT * FROM administrators WHERE role='部门管理员' ORDER BY adminid";
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
                //对电话号码进行sm4解密
                String phonenumber=rst.getString("phone");
                StringBuffer phonenumber_gai=new StringBuffer();
                try{
                    // 生成密钥
                    String keyHex = "0123456789ABCDEF0123456789ABCDEF";
                    byte[] keyData = Hex.decode(keyHex);
                    SecretKey key = new SecretKeySpec(keyData, "SM4");

                    // 定义初始向量（IV）
                    String ivHex = "00000000000000000000000000000000";
                    byte[] ivData = Hex.decode(ivHex);

                    byte[] encryptedFromHex = Hex.decode(phonenumber);
                    SM4 sm4 = new SM4();
                    // 解密
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
    //部门管理员授权，社会预约
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
    //部门管理员授权，公务预约（不仅可以查看自己部门，还可以查看所有）
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
    //部门管理员授权，取消社会预约
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
    //部门管理员授权，取消公务预约（不仅可以查看自己部门，还可以查看所有）
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
