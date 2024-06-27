package com.dao;

import com.model.Department;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DepartDao implements Basedao{
    //ÃÌº”≤ø√≈
    public boolean addDepart(Department depart){
        String sql="INSERT INTO department VALUES(?,?,?)";
        try(
                Connection conn=getConnection();
                PreparedStatement pstmt=conn.prepareStatement(sql)
        ){
            pstmt.setString(3,depart.getName());
            pstmt.setString(2,depart.getType());
            pstmt.setString(1,depart.getId());
            pstmt.executeUpdate();
            return true;
        }catch(SQLException se){
            se.printStackTrace();
            return false;
        }
    }
    public boolean deleteDepart(String id){
        String sql="DELETE FROM department WHERE id=?";
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
    public ArrayList<Department> findAllDepart(int currentPage, int pageSize)throws Exception {
        ArrayList<Department> departList = new ArrayList<>();
        String sql = "SELECT * FROM department ORDER BY id LIMIT ? OFFSET ?";
        try (
                Connection conn = getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql);) {
            pstmt.setInt(1, pageSize);
            pstmt.setInt(2, (currentPage - 1) * pageSize);
            try (ResultSet rst = pstmt.executeQuery()
            ) {
                while (rst.next()) {
                    Department depart = new Department();
                    depart.setName(rst.getString("name"));
                    depart.setId(rst.getString("id"));
                    depart.setType(rst.getString("type"));
                    departList.add(depart);
                }
                return departList;
            } catch (SQLException se) {
                return null;
            }
        }
    }
    public Integer findAllDepartNumber()throws Exception {
        Integer num = 0;
        String sql = "SELECT * FROM department";
        try (
                Connection conn = getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql);) {
            try (ResultSet rst = pstmt.executeQuery()
            ) {
                while (rst.next()) {
                    num++;
                }
                return num;
            } catch (SQLException se) {
                return null;
            }
        }
    }
    public ArrayList<Department> findByFuzzyName(String name,int currentPage, int pageSize)throws Exception{
        String sql="SELECT * FROM department WHERE name LIKE ? ORDER BY id LIMIT ? OFFSET ?";
        ArrayList<Department>departList=new ArrayList<>();
        try(
                Connection conn=getConnection();
                PreparedStatement pstmt=conn.prepareStatement(sql)
        ){
            pstmt.setString(1,"%"+name+"%");
            pstmt.setInt(2, pageSize);
            pstmt.setInt(3, (currentPage - 1) * pageSize);
            try(ResultSet rst=pstmt.executeQuery()){
                while(rst.next()){
                    Department depart=new Department();
                    depart.setName(rst.getString("name"));
                    depart.setId(rst.getString("id"));
                    depart.setType(rst.getString("type"));
                    departList.add(depart);
                }
            }
        }catch(SQLException se){
            return null;
        }
        return departList;
    }
    public Integer findByFuzzyNameNumber(String name)throws Exception{
        String sql="SELECT * FROM department WHERE name LIKE ?";
        Integer num=0;
        try(
                Connection conn=getConnection();
                PreparedStatement pstmt=conn.prepareStatement(sql)
        ){
            pstmt.setString(1,"%"+name+"%");
            try(ResultSet rst=pstmt.executeQuery()){
                while(rst.next()){
                   num++;
                }
            }
        }catch(SQLException se){
            return null;
        }
        return num;
    }
    public boolean modifyDepart(Department depart){
        String sql="UPDATE department SET type=?,name=? " +
                "WHERE id=?;";
        try(
                Connection conn=getConnection();
                PreparedStatement pstmt=conn.prepareStatement(sql)
        ){
            pstmt.setString(1,depart.getType());
            pstmt.setString(2,depart.getName());
            pstmt.setString(3,depart.getId());
            pstmt.executeUpdate();
            return true;
        }catch(SQLException se){
            se.printStackTrace();
            return false;
        }
    }
    public Department findById(String id)throws Exception{
        String sql="SELECT * FROM department WHERE id=?";
        Department depart=new Department();
        try(
                Connection conn=getConnection();
                PreparedStatement pstmt=conn.prepareStatement(sql)
        ){
            pstmt.setString(1,id);
            try(ResultSet rst=pstmt.executeQuery()){
                if(rst.next()){
                    depart.setName(rst.getString("name"));
                    depart.setId(rst.getString("id"));
                    depart.setType(rst.getString("type"));
                }
            }
        }catch(SQLException se){
            return null;
        }
        return depart;
    }
}
