package com.dao;

import com.model.Administrators;
import com.model.Department;
import com.model.Reservation_public;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
                }
            }
        }catch(SQLException se){
            return null;
        }
        return admin;
    }
    //��ӹ���Ա
    public boolean addAdmin(Administrators admin) throws DaoException{
        String sql ="insert into administrators values(?,?,?,?,?,?,?,?,?)";
        try(Connection dbconn = getConnection();
            PreparedStatement pstmt = dbconn.prepareStatement(sql)){
            pstmt.setString(1,admin.getAdminID());
            pstmt.setString(2,admin.getName());
            pstmt.setString(3,admin.getUsername());
            pstmt.setString(4,admin.getPassword());
            pstmt.setString(5,admin.getDepartmentID());
            pstmt.setString(6,admin.getPhone());
            pstmt.setString(7,admin.getRole());
            pstmt.setString(8,admin.getSocial());
            pstmt.setString(9,admin.getPub());
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
        String sql="SELECT * FROM administrators WHERE name LIKE ?";
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
                    admin.setPhone(rst.getString("phone"));
                    admin.setRole(rst.getString("role"));
                    admin.setSocial(rst.getString("social"));
                    admin.setPub(rst.getString("pub"));
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
        String sql="SELECT * FROM administrators";
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
                admin.setPhone(rst.getString("phone"));
                admin.setRole(rst.getString("role"));
                admin.setSocial(rst.getString("social"));
                admin.setPub(rst.getString("pub"));
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
                    admin.setPhone(rst.getString("phone"));
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
                "username=?,password=?," +
                "departmentid=?,phone=?,role=?" +
                "WHERE adminid=?;";
        try(
                Connection conn=getConnection();
                PreparedStatement pstmt=conn.prepareStatement(sql)
        ){
            pstmt.setString(1,admin.getName());
            pstmt.setString(2,admin.getUsername());
            pstmt.setString(3,admin.getPassword());
            pstmt.setString(4,admin.getDepartmentID());
            pstmt.setString(5,admin.getPhone());
            pstmt.setString(6,admin.getRole());
            pstmt.setString(7,admin.getAdminID());
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
        String sql="SELECT * FROM administrators WHERE name LIKE ? AND role='���Ź���Ա'";
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
                    admin.setPhone(rst.getString("phone"));
                    admin.setRole(rst.getString("role"));
                    admin.setSocial(rst.getString("social"));
                    admin.setPub(rst.getString("pub"));
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
        String sql="SELECT * FROM administrators WHERE role='���Ź���Ա'";
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
                admin.setPhone(rst.getString("phone"));
                admin.setRole(rst.getString("role"));
                admin.setSocial(rst.getString("social"));
                admin.setPub(rst.getString("pub"));
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
