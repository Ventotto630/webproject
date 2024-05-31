package com.dao;

import com.model.Reservation_public;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RezvtionpublicDao implements Basedao{
    public boolean addRezvtion(Reservation_public reservation) throws DaoException{
        String sql ="insert into Rezvtionpub values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try(Connection dbconn = getConnection();
            PreparedStatement pstmt = dbconn.prepareStatement(sql)){
            pstmt.setString(1,reservation.getName());
            pstmt.setString(2,reservation.getPerid());
            pstmt.setString(3,reservation.getPhoneNumber());
            pstmt.setString(4,reservation.getApplytime());
            pstmt.setString(5,reservation.getCampus());
            pstmt.setString(6,reservation.getIntime());
            pstmt.setString(7,reservation.getOuttime());
            pstmt.setString(8,reservation.getUnit());
            pstmt.setString(9,reservation.getVehicle());
            pstmt.setString(10,reservation.getVname());
            pstmt.setString(11,reservation.getFriend().getName());
            pstmt.setString(12,reservation.getFriend().getPerid());
            pstmt.setString(13,reservation.getFriend().getPhoneNumber());
            pstmt.setString(14,reservation.getVisitunit());
            pstmt.setString(15,reservation.getReceptionist());
            pstmt.setString(16,reservation.getReason());
            pstmt.setString(17,reservation.getStatus());
            pstmt.executeUpdate();
            return true;
        }catch (SQLException ne){
            ne.printStackTrace();
            return false;
        }
    }
    public ArrayList<Reservation_public> find(Reservation_public reservation) throws DaoException{
        String sql="select * "
                +"from Rezvtionpub where  =";
        ArrayList<User> userlist = new ArrayList<User>();
        try( Connection dbconn =getConnection();
             PreparedStatement pstmt = dbconn.prepareStatement(sql)){
            pstmt.setString(1,"%"+name+"%");
            try(ResultSet rst = pstmt.executeQuery()){
                while(rst.next()){
                    User user = new User();
                    user.setUname(rst.getString("uname"));
                    user.setU_id(rst.getString("u_id"));
                    user.setPasswd(rst.getString("passwd"));
                    user.setDepart(rst.getString("depart"));
                    user.setPhone(rst.getString("phone"));
                    userlist.add(user);
                }
            }
            return userlist;
        }catch (SQLException ne){
            ne.printStackTrace();
            return null;
        }
    }
    public  ArrayList<User>findAlluser()throws DaoException{
        String sql="select uname,u_id,passwd,depart,phone "
                +"from users ";
        ArrayList<User> userlist = new ArrayList<User>();
        try( Connection dbconn =getConnection();
             PreparedStatement pstmt = dbconn.prepareStatement(sql);
             ResultSet rst = pstmt.executeQuery()){
            while(rst.next()){
                User user = new User();
                user.setUname(rst.getString("uname"));
                user.setU_id(rst.getString("u_id"));
                user.setPasswd(rst.getString("passwd"));
                user.setDepart(rst.getString("depart"));
                user.setPhone(rst.getString("phone"));
                userlist.add(user);
            }
            return userlist;
        }catch (SQLException ne){
            ne.printStackTrace();
            return null;
        }
    }
    public boolean updateUser(User user) throws DaoException{
        String sql ="update users set u_id=?,passwd=?,depart=?,phone=? where uname=?";
        try(Connection dbconn = getConnection();
            PreparedStatement pstmt = dbconn.prepareStatement(sql)){
            pstmt.setString(1,user.getU_id());
            pstmt.setString(2,user.getPasswd());
            pstmt.setString(3,user.getDepart());
            pstmt.setString(4,user.getPhone());
            pstmt.setString(5,user.getUname());
            pstmt.executeUpdate();
            return true;
        }catch (SQLException ne){
            ne.printStackTrace();
            return false;
        }
    }
    public boolean deleteUser(String uname) throws DaoException{
        String sql ="delete from users where uname=?";
        try(Connection dbconn = getConnection();
            PreparedStatement pstmt = dbconn.prepareStatement(sql)){
            pstmt.setString(1,uname);
            pstmt.executeUpdate();
            return true;
        }catch (SQLException ne){
            ne.printStackTrace();
            return false;
        }
    }
}
