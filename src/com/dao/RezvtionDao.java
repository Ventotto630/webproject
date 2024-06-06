package com.dao;

import com.model.Person;
import com.model.Reservation;
import com.model.Reservation_public;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RezvtionDao implements Basedao{
    public boolean addRezvtion(Reservation reservation) throws DaoException{
        String sql ="insert into rezvtion values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try(Connection dbconn = getConnection();
            PreparedStatement pstmt = dbconn.prepareStatement(sql)){
            pstmt.setString(1,reservation.getName());
            pstmt.setString(2,reservation.getPerid());
            pstmt.setString(3,reservation.getPhoneNumber());
            pstmt.setString(4,reservation.getSerid());
            pstmt.setString(5,reservation.getApplytime());
            pstmt.setString(6,reservation.getCampus());
            pstmt.setString(7,reservation.getIntime());
            pstmt.setString(8,reservation.getOuttime());
            pstmt.setString(9,reservation.getUnit());
            pstmt.setString(10,reservation.getVehicle());
            pstmt.setString(11,reservation.getVname());
            pstmt.setString(12,reservation.getFriend().getName());
            pstmt.setString(13,reservation.getFriend().getPerid());
            pstmt.setString(14,reservation.getFriend().getPhoneNumber());
            pstmt.setString(15,reservation.getQrcode());
            pstmt.executeUpdate();
            return true;
        }catch (SQLException ne){
            ne.printStackTrace();
            return false;
        }
    }
    public ArrayList<Reservation> find(Reservation reservation) throws DaoException{
        String sql="select * "
                +"from rezvtion where  1=1";
        ArrayList<Reservation> rezvlist = new ArrayList<Reservation>();

        if(!reservation.getName().equals("null"))
            sql+="and name like ?" ;
        if(!reservation.getPerid().equals("null"))
            sql+="and perid like ?" ;
        if(!reservation.getPhoneNumber().equals("null"))
            sql+="and phonenumber like ?" ;
        if(!reservation.getSerid().equals("null"))
            sql+="and serid like ?" ;
        if(!reservation.getApplytime().equals("null"))
            sql+="and applytime like ?" ;
        if(!reservation.getCampus().equals("null"))
            sql+="and campus like ?" ;
        if(!reservation.getIntime().equals("null"))
            sql+="and intime like ?" ;
        if(!reservation.getOuttime().equals("null"))
            sql+="and outtime like ?" ;
        if(!reservation.getUnit().equals("null"))
            sql+="and unit like ?" ;
        if(!reservation.getVehicle().equals("null"))
            sql+="and vehicle like ?" ;
        if(!reservation.getVname().equals("null"))
            sql+="and vname like ?" ;
        if(!reservation.getFriend().getName().equals("null"))
            sql+="and fri_name like ?" ;
        if(!reservation.getFriend().getPerid().equals("null"))
            sql+="and fri_perid like ?" ;
        if(!reservation.getFriend().getPhoneNumber().equals("null"))
            sql+="and fri_phonenumber like ?" ;


        try( Connection dbconn =getConnection();
             PreparedStatement pstmt = dbconn.prepareStatement(sql)){
            int t=0;
            if(!reservation.getName().equals("null")){
                t++;
                pstmt.setString(t,"%"+reservation.getName()+"%");
            }
            if(!reservation.getPerid().equals("null")){
                t++;
                pstmt.setString(t,"%"+reservation.getPerid()+"%");
            }
            if(!reservation.getPhoneNumber().equals("null")){
                t++;
                pstmt.setString(t,"%"+reservation.getPhoneNumber()+"%");
            }
            if(!reservation.getSerid().equals("null")){
                t++;
                pstmt.setString(t,"%"+reservation.getSerid()+"%");
            }
            if(!reservation.getApplytime().equals("null")){
                t++;
                pstmt.setString(t,"%"+reservation.getApplytime()+"%");
            }

            if(!reservation.getCampus().equals("null")){
                t++;
                pstmt.setString(t,"%"+reservation.getCampus()+"%");
            }

            if(!reservation.getIntime().equals("null")) {
                t++;
                pstmt.setString(t,"%"+reservation.getIntime()+"%");
            }
            if(!reservation.getOuttime().equals("null")) {
                t++;
                pstmt.setString(t,"%"+reservation.getOuttime()+"%");
            }
            if(!reservation.getUnit().equals("null")){
                t++;
                pstmt.setString(t,"%"+reservation.getUnit()+"%");
            }
            if(!reservation.getVehicle().equals("null")){
                t++;
                pstmt.setString(t,"%"+reservation.getVehicle()+"%");
            }
            if(!reservation.getVname().equals("null")){
                t++;
                pstmt.setString(t,"%"+reservation.getVname()+"%");
            }
            if(!reservation.getFriend().getName().equals("null")){
                t++;
                pstmt.setString(t,"%"+reservation.getFriend().getName()+"%");
            }
            if(!reservation.getFriend().getPerid().equals("null")){
                t++;
                pstmt.setString(t,"%"+reservation.getFriend().getPerid()+"%");
            }
            if(!reservation.getFriend().getPhoneNumber().equals("null")){
                t++;
                pstmt.setString(t,"%"+reservation.getFriend().getPhoneNumber()+"%");
            }


            try(ResultSet rst = pstmt.executeQuery()){
                while(rst.next()){
                    Reservation rezv = new Reservation();
                    rezv.setName(rst.getString("name"));
                    rezv.setPerid(rst.getString("perid"));
                    rezv.setPhoneNumber(rst.getString("phonenumber"));
                    rezv.setSerid(rst.getString("serid"));
                    rezv.setApplytime(rst.getString("applytime"));
                    rezv.setCampus(rst.getString("campus"));
                    rezv.setIntime(rst.getString("intime"));
                    rezv.setOuttime(rst.getString("outtime"));
                    rezv.setUnit(rst.getString("unit"));
                    rezv.setVehicle(rst.getString("vehicle"));
                    Person friend =new Person();
                    friend.setName(rst.getString("fri_name"));
                    friend.setName(rst.getString("fri_perid"));
                    friend.setName(rst.getString("fri_phonenumber"));
                    rezv.setFriend(friend);


                    rezvlist.add(rezv);
                }
            }
            return rezvlist;
        }catch (SQLException ne){
            ne.printStackTrace();
            return null;
        }
    }
    public int CountRezv(Reservation reservation) throws DaoException{
        String sql="select count (*) AS total "
                +"from rezvtion where  1=1";

        if(!reservation.getName().equals("null"))
            sql+="and name like ?" ;
        if(!reservation.getPerid().equals("null"))
            sql+="and perid like ?" ;
        if(!reservation.getPhoneNumber().equals("null"))
            sql+="and phoneNumber like ?" ;
        if(!reservation.getSerid().equals("null"))
            sql+="and serid like ?" ;
        if(!reservation.getApplytime().equals("null"))
            sql+="and applytime like ?" ;
        if(!reservation.getCampus().equals("null"))
            sql+="and campus like ?" ;
        if(!reservation.getIntime().equals("null"))
            sql+="and intime like ?" ;
        if(!reservation.getOuttime().equals("null"))
            sql+="and outtime like ?" ;
        if(!reservation.getUnit().equals("null"))
            sql+="and unit like ?" ;
        if(!reservation.getVehicle().equals("null"))
            sql+="and vehicle like ?" ;
        if(!reservation.getVname().equals("null"))
            sql+="and vname like ?" ;
        if(!reservation.getFriend().getName().equals("null"))
            sql+="and Fri_name like ?" ;
        if(!reservation.getFriend().getPerid().equals("null"))
            sql+="and Fri_perid like ?" ;
        if(!reservation.getFriend().getPhoneNumber().equals("null"))
            sql+="and Fri_phoneNumber like ?" ;


        try( Connection dbconn =getConnection();
             PreparedStatement pstmt = dbconn.prepareStatement(sql)){
            int t=0;
            if(!reservation.getName().equals("null")){
                t++;
                pstmt.setString(t,"%"+reservation.getName()+"%");
            }
            if(!reservation.getPerid().equals("null")){
                t++;
                pstmt.setString(t,"%"+reservation.getPerid()+"%");
            }
            if(!reservation.getPhoneNumber().equals("null")){
                t++;
                pstmt.setString(t,"%"+reservation.getPhoneNumber()+"%");
            }
            if(!reservation.getSerid().equals("null")){
                t++;
                pstmt.setString(t,"%"+reservation.getSerid()+"%");
            }
            if(!reservation.getApplytime().equals("null")){
                t++;
                pstmt.setString(t,"%"+reservation.getApplytime()+"%");
            }

            if(!reservation.getCampus().equals("null")){
                t++;
                pstmt.setString(t,"%"+reservation.getCampus()+"%");
            }

            if(!reservation.getIntime().equals("null")) {
                t++;
                pstmt.setString(t,"%"+reservation.getIntime()+"%");
            }
            if(!reservation.getOuttime().equals("null")) {
                t++;
                pstmt.setString(t,"%"+reservation.getOuttime()+"%");
            }
            if(!reservation.getUnit().equals("null")){
                t++;
                pstmt.setString(t,"%"+reservation.getUnit()+"%");
            }
            if(!reservation.getVehicle().equals("null")){
                t++;
                pstmt.setString(t,"%"+reservation.getVehicle()+"%");
            }
            if(!reservation.getVname().equals("null")){
                t++;
                pstmt.setString(t,"%"+reservation.getVname()+"%");
            }
            if(!reservation.getFriend().getName().equals("null")){
                t++;
                pstmt.setString(t,"%"+reservation.getFriend().getName()+"%");
            }
            if(!reservation.getFriend().getPerid().equals("null")){
                t++;
                pstmt.setString(t,"%"+reservation.getFriend().getPerid()+"%");
            }
            if(!reservation.getFriend().getPhoneNumber().equals("null")){
                t++;
                pstmt.setString(t,"%"+reservation.getFriend().getPhoneNumber()+"%");
            }
            int count = 0;

            try(ResultSet rst = pstmt.executeQuery()){
                if(rst.next()){
                    count = rst.getInt("total");
                }
            }
            return count;
        }catch (SQLException ne){
            ne.printStackTrace();
            return -1;
        }
    }
    public int CountPeople(Reservation reservation) throws DaoException{ //只是随行人员的查询
        String sql="select count (*) AS total "
                +"from rezvtion where  1=1 and fri_name!='null' ";

        if(!reservation.getName().equals("null"))
            sql+="and name like ?" ;
        if(!reservation.getPerid().equals("null"))
            sql+="and perid like ?" ;
        if(!reservation.getPhoneNumber().equals("null"))
            sql+="and phoneNumber like ?" ;
        if(!reservation.getSerid().equals("null"))
            sql+="and serid like ?" ;
        if(!reservation.getApplytime().equals("null"))
            sql+="and applytime like ?" ;
        if(!reservation.getCampus().equals("null"))
            sql+="and campus like ?" ;
        if(!reservation.getIntime().equals("null"))
            sql+="and intime like ?" ;
        if(!reservation.getOuttime().equals("null"))
            sql+="and outtime like ?" ;
        if(!reservation.getUnit().equals("null"))
            sql+="and unit like ?" ;
        if(!reservation.getVehicle().equals("null"))
            sql+="and vehicle like ?" ;
        if(!reservation.getVname().equals("null"))
            sql+="and vname like ?" ;
        if(!reservation.getFriend().getName().equals("null"))
            sql+="and Fri_name like ?" ;
        if(!reservation.getFriend().getPerid().equals("null"))
            sql+="and Fri_perid like ?" ;
        if(!reservation.getFriend().getPhoneNumber().equals("null"))
            sql+="and Fri_phoneNumber like ?" ;


        try( Connection dbconn =getConnection();
             PreparedStatement pstmt = dbconn.prepareStatement(sql)){
            int t=0;
            if(!reservation.getName().equals("null")){
                t++;
                pstmt.setString(t,"%"+reservation.getName()+"%");
            }
            if(!reservation.getPerid().equals("null")){
                t++;
                pstmt.setString(t,"%"+reservation.getPerid()+"%");
            }
            if(!reservation.getPhoneNumber().equals("null")){
                t++;
                pstmt.setString(t,"%"+reservation.getPhoneNumber()+"%");
            }
            if(!reservation.getSerid().equals("null")){
                t++;
                pstmt.setString(t,"%"+reservation.getSerid()+"%");
            }
            if(!reservation.getApplytime().equals("null")){
                t++;
                pstmt.setString(t,"%"+reservation.getApplytime()+"%");
            }

            if(!reservation.getCampus().equals("null")){
                t++;
                pstmt.setString(t,"%"+reservation.getCampus()+"%");
            }

            if(!reservation.getIntime().equals("null")) {
                t++;
                pstmt.setString(t,"%"+reservation.getIntime()+"%");
            }
            if(!reservation.getOuttime().equals("null")) {
                t++;
                pstmt.setString(t,"%"+reservation.getOuttime()+"%");
            }
            if(!reservation.getUnit().equals("null")){
                t++;
                pstmt.setString(t,"%"+reservation.getUnit()+"%");
            }
            if(!reservation.getVehicle().equals("null")){
                t++;
                pstmt.setString(t,"%"+reservation.getVehicle()+"%");
            }
            if(!reservation.getVname().equals("null")){
                t++;
                pstmt.setString(t,"%"+reservation.getVname()+"%");
            }
            if(!reservation.getFriend().getName().equals("null")){
                t++;
                pstmt.setString(t,"%"+reservation.getFriend().getName()+"%");
            }
            if(!reservation.getFriend().getPerid().equals("null")){
                t++;
                pstmt.setString(t,"%"+reservation.getFriend().getPerid()+"%");
            }
            if(!reservation.getFriend().getPhoneNumber().equals("null")){
                t++;
                pstmt.setString(t,"%"+reservation.getFriend().getPhoneNumber()+"%");
            }
            int count = 0;

            try(ResultSet rst = pstmt.executeQuery()){
                if(rst.next()){
                    count = rst.getInt("total");
                }
            }
            return count;
        }catch (SQLException ne){
            ne.printStackTrace();
            return -1;
        }
    }
    public  ArrayList<Reservation>findAllrezv()throws DaoException{
        String sql="select * "
                +"from rezvtion ";
        ArrayList<Reservation> rezvlist = new ArrayList<Reservation>();
        try( Connection dbconn =getConnection();
             PreparedStatement pstmt = dbconn.prepareStatement(sql);
             ResultSet rst = pstmt.executeQuery()){
            while(rst.next()){
                Reservation rezv = new Reservation();
                rezv.setName(rst.getString("name"));
                rezv.setPerid(rst.getString("perid"));
                rezv.setPhoneNumber(rst.getString("phoneNumber"));
                rezv.setSerid(rst.getString("serid"));
                rezv.setApplytime(rst.getString("applytime"));
                rezv.setCampus(rst.getString("campus"));
                rezv.setIntime(rst.getString("intime"));
                rezv.setOuttime(rst.getString("outtime"));
                rezv.setUnit(rst.getString("unit"));
                rezv.setVehicle(rst.getString("vehicle"));
                Person friend =new Person();
                friend.setName(rst.getString("Fri_name"));
                friend.setName(rst.getString("Fri_perid"));
                friend.setName(rst.getString("Fri_phoneNumber"));
                rezv.setFriend(friend);


                rezvlist.add(rezv);
            }
            return rezvlist;
        }catch (SQLException ne){
            ne.printStackTrace();
            return null;
        }
    }
    public Reservation findByserid(String serid) throws  DaoException{
        String sql="select * "
                +"from rezvtion where serid = ?";
        Reservation rezv = new Reservation();
        try( Connection dbconn =getConnection();
             PreparedStatement pstmt = dbconn.prepareStatement(sql);){
            pstmt.setString(1,serid);
            ResultSet rst = pstmt.executeQuery();
            while(rst.next()){

                rezv.setName(rst.getString("name"));
                rezv.setPerid(rst.getString("perid"));
                rezv.setPhoneNumber(rst.getString("phoneNumber"));
                rezv.setSerid(rst.getString("serid"));
                rezv.setApplytime(rst.getString("applytime"));
                rezv.setCampus(rst.getString("campus"));
                rezv.setIntime(rst.getString("intime"));
                rezv.setOuttime(rst.getString("outtime"));
                rezv.setUnit(rst.getString("unit"));
                rezv.setVehicle(rst.getString("vehicle"));
                Person friend =new Person();
                friend.setName(rst.getString("Fri_name"));
                friend.setName(rst.getString("Fri_perid"));
                friend.setName(rst.getString("Fri_phoneNumber"));
                rezv.setFriend(friend);
                rezv.setQrcode(rst.getString("qrcode"));

            }
            return rezv;
        }catch (SQLException ne){
            ne.printStackTrace();
            return null;
        }
    }
    public boolean updateQrcode(String qrcode,String serid) throws DaoException{
        String sql ="update rezvtion set qrcode=? where serid=?";
        try(Connection dbconn = getConnection();
            PreparedStatement pstmt = dbconn.prepareStatement(sql)){
            pstmt.setString(1,qrcode);
            pstmt.setString(2,serid);
            pstmt.executeUpdate();
            return true;
        }catch (SQLException ne){
            ne.printStackTrace();
            return false;
        }
    }  //审核后生成的二维码更新进数据库 用不到
}
