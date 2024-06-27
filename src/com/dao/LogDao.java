package com.dao;
import com.model.Auditlog;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LogDao implements Basedao{
    public boolean addLog(Auditlog log)throws DaoException{
        String sql="INSERT INTO log(uname,operation,description) VALUES(?,?,?)";
        try(
                Connection conn=getConnection();
                PreparedStatement pstmt=conn.prepareStatement(sql)
        ){
            pstmt.setString(1,log.getUname());
            pstmt.setString(2,log.getOperation());
            pstmt.setString(3,log.getDescription());
            pstmt.executeUpdate();
            return true;
        }catch(SQLException se){
            se.printStackTrace();
            return false;
        }
    }
    public ArrayList<Auditlog> find(Auditlog log,int currentPage, int pageSize)throws DaoException{
        String sql="select * "
                +"from log where  1=1";
        ArrayList<Auditlog> logs= new ArrayList<>();
        if(!log.getUname().equals("")){
            sql+="and uname like ?" ;
        }
        if(!log.getOperation().equals("")){
            sql+="and operation like ?" ;
        }
        sql+="LIMIT ? OFFSET ?";

        try( Connection dbconn =getConnection();
             PreparedStatement pstmt = dbconn.prepareStatement(sql)) {
            int t = 0;

            if(!log.getUname().equals("")){
                t++;
                pstmt.setString(t,"%"+log.getUname()+"%");
            }
            if(!log.getOperation().equals("")){
                t++;
                pstmt.setString(t,"%"+log.getOperation()+"%");
            }
            pstmt.setInt(++t, pageSize);
            pstmt.setInt(++t, (currentPage - 1) * pageSize);
            try(ResultSet rst = pstmt.executeQuery()) {
                while (rst.next()) {
                    Auditlog log1=new Auditlog();
                    log1.setUname(rst.getString("uname"));
                    log1.setOperation(rst.getString("operation"));
                    log1.setDescription(rst.getString("description"));
                    log1.setTime(rst.getString("timestamp"));
                    logs.add(log1);
                }
            }
            return logs;
        }catch (SQLException ne){
            ne.printStackTrace();
            return null;
        }
    }
    public Integer findNumber(Auditlog log)throws DaoException{
        String sql="select * "
                +"from log where  1=1";
        Integer num=0;
        if(!log.getUname().equals("")){
            sql+="and uname like ?" ;
        }
        if(!log.getOperation().equals("")){
            sql+="and operation like ?" ;
        }

        try( Connection dbconn =getConnection();
             PreparedStatement pstmt = dbconn.prepareStatement(sql)) {
            int t = 0;

            if(!log.getUname().equals("")){
                t++;
                pstmt.setString(t,"%"+log.getUname()+"%");
            }
            if(!log.getOperation().equals("")){
                t++;
                pstmt.setString(t,"%"+log.getOperation()+"%");
            }
            try(ResultSet rst = pstmt.executeQuery()) {
                while (rst.next()) {
                    num++;
                }
            }
            return num;
        }catch (SQLException ne){
            ne.printStackTrace();
            return null;
        }
    }
    public ArrayList<Auditlog> findAlllogs(int currentPage, int pageSize) throws DaoException, SQLException {
        String sql = "select * "
                + "from log LIMIT ? OFFSET ?";
        ArrayList<Auditlog> logs = new ArrayList<>();
        try (Connection dbconn = getConnection();
             PreparedStatement pstmt = dbconn.prepareStatement(sql);) {
            pstmt.setInt(1, pageSize);
            pstmt.setInt(2, (currentPage - 1) * pageSize);
            try (ResultSet rst = pstmt.executeQuery()) {
                while (rst.next()) {
                    Auditlog log1 = new Auditlog();
                    log1.setUname(rst.getString("uname"));
                    log1.setOperation(rst.getString("operation"));
                    log1.setDescription(rst.getString("description"));
                    log1.setTime(rst.getString("timestamp"));
                    logs.add(log1);
                }
                return logs;
            } catch (SQLException ne) {
                ne.printStackTrace();
                return null;
            }
        }
    }
    public Integer findAlllogsNumber() throws DaoException, SQLException {
        String sql = "select * "
                + "from log";
        Integer num = 0;
        try (Connection dbconn = getConnection();
             PreparedStatement pstmt = dbconn.prepareStatement(sql);) {
            try (ResultSet rst = pstmt.executeQuery()) {
                while (rst.next()) {
                    num++;
                }
                return num;
            } catch (SQLException ne) {
                ne.printStackTrace();
                return null;
            }
        }
    }
}
