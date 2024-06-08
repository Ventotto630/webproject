package com.dao;

import com.model.Administrators;
import com.model.Department;
import com.model.Reservation_public;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class adminDao implements Basedao{
    //查询管理员
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
                }
            }
        }catch(SQLException se){
            return null;
        }
        return admin;
    }
    //添加管理员
    public boolean addAdmin(Administrators admin) throws DaoException{
        String sql ="insert into administrators values(?,?,?,?,?,?,?)";
        try(Connection dbconn = getConnection();
            PreparedStatement pstmt = dbconn.prepareStatement(sql)){
            pstmt.setString(1,admin.getAdminID());
            pstmt.setString(2,admin.getName());
            pstmt.setString(3,admin.getUsername());
            pstmt.setString(4,admin.getPassword());
            pstmt.setString(5,admin.getDepartmentID());
            pstmt.setString(6,admin.getPhone());
            pstmt.setString(7,admin.getRole());
            pstmt.executeUpdate();
            return true;
        }catch (SQLException ne){
            ne.printStackTrace();
            return false;
        }
    }

}
