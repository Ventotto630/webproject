package com.dao;

import com.model.Person;
import com.model.Reservation;
import com.model.Reservation_public;
import com.utils.SM4;
import org.bouncycastle.util.encoders.Hex;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RezvtionDao implements Basedao{
    public boolean addRezvtion(Reservation reservation) throws DaoException{
        String sql ="insert into rezvtion values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
        String serid = reservation.getSerid();
        int n = Integer.parseInt(reservation.getFri_number());
        ArrayList<Person> friends =reservation.getFriend();
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
            pstmt.setString(12,reservation.getFri_number());
            pstmt.setString(13,reservation.getQrcode());
//
//
//            pstmt.setString(12,reservation.getFriend().getName());
//            pstmt.setString(13,reservation.getFriend().getPerid());
//            pstmt.setString(14,reservation.getFriend().getPhoneNumber());

            pstmt.executeUpdate();
            //return true;
        }catch (SQLException ne){
            ne.printStackTrace();
            return false;
        }
        if(n!=0){
            for(Person friend:friends){
                sql ="insert into rezv_friend (serid,fri_name,fri_perid,fri_phonenumber) values(?,?,?,?)";
                try(Connection dbconn = getConnection();
                    PreparedStatement pstmt = dbconn.prepareStatement(sql)) {
                    pstmt.setString(1, serid);
                    pstmt.setString(2, friend.getName());
                    pstmt.setString(3, friend.getPerid());
                    pstmt.setString(4, friend.getPhoneNumber());
                    pstmt.executeUpdate();
                }catch (SQLException ne){
                    ne.printStackTrace();
                    return false;
                }
            }

        }
        return true;
    }
    public ArrayList<Reservation> findmyRezv(String name,String perid,String phoneNumber) throws DaoException{
        String sql="select * "
                +"from rezvtion where  name=? and perid=? and phonenumber=?";
        ArrayList<Reservation> rezvlist = new ArrayList<Reservation>();

        try( Connection dbconn =getConnection();
             PreparedStatement pstmt = dbconn.prepareStatement(sql)){
           pstmt.setString(1,name);
           pstmt.setString(2,perid);
           pstmt.setString(3,phoneNumber);


            try(ResultSet rst = pstmt.executeQuery()){
                while(rst.next()){
                    Reservation rezv = new Reservation();
                    rezv.setName(rst.getString("name"));
                    String per_id=rst.getString("perid");
                    StringBuffer perid_gai =new StringBuffer();
                    try{
                        // 生成密钥
                        String keyHex = "0123456789ABCDEF0123456789ABCDEF";
                        byte[] keyData = Hex.decode(keyHex);
                        SecretKey key = new SecretKeySpec(keyData, "SM4");

                        // 定义初始向量（IV）
                        String ivHex = "00000000000000000000000000000000";
                        byte[] ivData = Hex.decode(ivHex);

                        byte[] encryptedFromHex = Hex.decode(per_id);
                        SM4 sm4 = new SM4();
// 解密
                        byte[] decrypted = sm4.decrypt(encryptedFromHex, key, ivData);
                        per_id = new String(decrypted);

                        perid_gai.append(per_id.substring(0, 6));
                        perid_gai.append("********");
                        perid_gai.append(per_id.substring(14,18));

                    }catch (Exception e) {
                        System.err.println("Error: " + e.getMessage());
                    }
                    rezv.setPerid(perid_gai.toString());

                    String phonenumber=rst.getString("phonenumber");
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
                    rezv.setPhoneNumber(phonenumber_gai.toString());

                    rezv.setSerid(rst.getString("serid"));
                    rezv.setApplytime(rst.getString("applytime"));
                    rezv.setCampus(rst.getString("campus"));
                    rezv.setIntime(rst.getString("intime"));
                    rezv.setOuttime(rst.getString("outtime"));
                    rezv.setUnit(rst.getString("unit"));
                    rezv.setVehicle(rst.getString("vehicle"));
                    rezv.setVname(rst.getString("vname"));

                    String serid =rst.getString("serid");
                    String Fri_number=rst.getString("fri_number");
                    rezv.setFri_number(Fri_number);
                    if (!Fri_number.equals("0")){
                        String sql2="select * "
                                +"from rezv_friend where  serid=?";
                        ArrayList<Person> friends = new ArrayList<>();
                        try(PreparedStatement pstmt1 = dbconn.prepareStatement(sql2);){
                            pstmt1.setString(1,serid);
                            ResultSet rst1 = pstmt1.executeQuery();
                            while(rst1.next()){
                                Person friend=new Person();
                                friend.setName(rst1.getString("fri_name"));

                                String fri_perid=rst1.getString("fri_perid");
                                StringBuffer fri_perid_gai=new StringBuffer();
                                try{
                                    // 生成密钥
                                    String keyHex = "0123456789ABCDEF0123456789ABCDEF";
                                    byte[] keyData = Hex.decode(keyHex);
                                    SecretKey key = new SecretKeySpec(keyData, "SM4");

                                    // 定义初始向量（IV）
                                    String ivHex = "00000000000000000000000000000000";
                                    byte[] ivData = Hex.decode(ivHex);

                                    byte[] encryptedFromHex = Hex.decode(fri_perid);
                                    SM4 sm4 = new SM4();
// 解密
                                    byte[] decrypted = sm4.decrypt(encryptedFromHex, key, ivData);
                                    fri_perid = new String(decrypted);
                                    fri_perid_gai.append(fri_perid.substring(0,6));
                                    fri_perid_gai.append("********");
                                    fri_perid_gai.append(fri_perid.substring(14,18));
                                }catch (Exception e) {
                                    System.err.println("Error: " + e.getMessage());
                                }
                                friend.setPerid(fri_perid_gai.toString());

                                String fri_phone=rst1.getString("fri_phonenumber");
                                StringBuffer fri_phone_gai=new StringBuffer();
                                try{
                                    // 生成密钥
                                    String keyHex = "0123456789ABCDEF0123456789ABCDEF";
                                    byte[] keyData = Hex.decode(keyHex);
                                    SecretKey key = new SecretKeySpec(keyData, "SM4");

                                    // 定义初始向量（IV）
                                    String ivHex = "00000000000000000000000000000000";
                                    byte[] ivData = Hex.decode(ivHex);

                                    byte[] encryptedFromHex = Hex.decode(fri_phone);
                                    SM4 sm4 = new SM4();
// 解密
                                    byte[] decrypted = sm4.decrypt(encryptedFromHex, key, ivData);
                                    fri_phone = new String(decrypted);
                                    fri_phone_gai.append(fri_phone.substring(0,3));
                                    fri_phone_gai.append("****");
                                    fri_phone_gai.append(fri_phone.substring(7,11));
                                }catch (Exception e) {
                                    System.err.println("Error: " + e.getMessage());
                                }
                                friend.setPhoneNumber(fri_phone_gai.toString());

                                friends.add(friend);
                            }
                        }

                        rezv.setFriend(friends);
                    }


                    rezvlist.add(rezv);
                }
            }
            return rezvlist;
        }catch (SQLException ne){
            ne.printStackTrace();
            return null;
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



            try(ResultSet rst = pstmt.executeQuery()){
                while(rst.next()){
                    Reservation rezv = new Reservation();
                    rezv.setName(rst.getString("name"));

                    String per_id=rst.getString("perid");
                    StringBuffer perid_gai =new StringBuffer();
                    try{
                        // 生成密钥
                        String keyHex = "0123456789ABCDEF0123456789ABCDEF";
                        byte[] keyData = Hex.decode(keyHex);
                        SecretKey key = new SecretKeySpec(keyData, "SM4");

                        // 定义初始向量（IV）
                        String ivHex = "00000000000000000000000000000000";
                        byte[] ivData = Hex.decode(ivHex);

                        byte[] encryptedFromHex = Hex.decode(per_id);
                        SM4 sm4 = new SM4();
// 解密
                        byte[] decrypted = sm4.decrypt(encryptedFromHex, key, ivData);
                        per_id = new String(decrypted);

                        perid_gai.append(per_id.substring(0, 6));
                        perid_gai.append("********");
                        perid_gai.append(per_id.substring(14,18));

                    }catch (Exception e) {
                        System.err.println("Error: " + e.getMessage());
                    }
                    rezv.setPerid(perid_gai.toString());

                    String phonenumber=rst.getString("phonenumber");
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
                    rezv.setPhoneNumber(phonenumber_gai.toString());

                    rezv.setSerid(rst.getString("serid"));
                    rezv.setApplytime(rst.getString("applytime"));
                    rezv.setCampus(rst.getString("campus"));
                    rezv.setIntime(rst.getString("intime"));
                    rezv.setOuttime(rst.getString("outtime"));
                    rezv.setUnit(rst.getString("unit"));
                    rezv.setVehicle(rst.getString("vehicle"));
                    rezv.setVname(rst.getString("vname"));

                    String serid =rst.getString("serid");
                    String Fri_number=rst.getString("fri_number");
                    rezv.setFri_number(Fri_number);
                    if (!Fri_number.equals("0")){
                        String sql2="select * "
                                +"from rezv_friend where  serid=?";
                        ArrayList<Person> friends = new ArrayList<>();
                        try(PreparedStatement pstmt1 = dbconn.prepareStatement(sql2);){
                            pstmt1.setString(1,serid);
                            ResultSet rst1 = pstmt1.executeQuery();
                            while(rst1.next()){
                                Person friend=new Person();
                                friend.setName(rst1.getString("fri_name"));

                                String fri_perid=rst1.getString("fri_perid");
                                StringBuffer fri_perid_gai=new StringBuffer();
                                try{
                                    // 生成密钥
                                    String keyHex = "0123456789ABCDEF0123456789ABCDEF";
                                    byte[] keyData = Hex.decode(keyHex);
                                    SecretKey key = new SecretKeySpec(keyData, "SM4");

                                    // 定义初始向量（IV）
                                    String ivHex = "00000000000000000000000000000000";
                                    byte[] ivData = Hex.decode(ivHex);

                                    byte[] encryptedFromHex = Hex.decode(fri_perid);
                                    SM4 sm4 = new SM4();
// 解密
                                    byte[] decrypted = sm4.decrypt(encryptedFromHex, key, ivData);
                                    fri_perid = new String(decrypted);
                                    fri_perid_gai.append(fri_perid.substring(0,6));
                                    fri_perid_gai.append("********");
                                    fri_perid_gai.append(fri_perid.substring(14,18));
                                }catch (Exception e) {
                                    System.err.println("Error: " + e.getMessage());
                                }
                                friend.setPerid(fri_perid_gai.toString());

                                String fri_phone=rst1.getString("fri_phonenumber");
                                StringBuffer fri_phone_gai=new StringBuffer();
                                try{
                                    // 生成密钥
                                    String keyHex = "0123456789ABCDEF0123456789ABCDEF";
                                    byte[] keyData = Hex.decode(keyHex);
                                    SecretKey key = new SecretKeySpec(keyData, "SM4");

                                    // 定义初始向量（IV）
                                    String ivHex = "00000000000000000000000000000000";
                                    byte[] ivData = Hex.decode(ivHex);

                                    byte[] encryptedFromHex = Hex.decode(fri_phone);
                                    SM4 sm4 = new SM4();
// 解密
                                    byte[] decrypted = sm4.decrypt(encryptedFromHex, key, ivData);
                                    fri_phone = new String(decrypted);
                                    fri_phone_gai.append(fri_phone.substring(0,3));
                                    fri_phone_gai.append("****");
                                    fri_phone_gai.append(fri_phone.substring(7,11));
                                }catch (Exception e) {
                                    System.err.println("Error: " + e.getMessage());
                                }
                                friend.setPhoneNumber(fri_phone_gai.toString());

                                friends.add(friend);
                            }
                        }

                        rezv.setFriend(friends);
                    }

                    rezvlist.add(rezv);
                }
            }
            return rezvlist;
        }catch (SQLException ne){
            ne.printStackTrace();
            return null;
        }
    }
    public int CountRezv(String intime) throws DaoException{
        String sql="select count (*) AS total "
                +"from rezvtion where  intime like ?";


        try( Connection dbconn =getConnection();
             PreparedStatement pstmt = dbconn.prepareStatement(sql)){
           pstmt.setString(1,intime+"%");

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
    public int CountPeople(String intime) throws DaoException{ //只是随行人员的查询
        String sql="select fri_number  "
                +"from rezvtion where  intime like ? ";

        try( Connection dbconn =getConnection();
             PreparedStatement pstmt = dbconn.prepareStatement(sql)){
            pstmt.setString(1,intime+"%");
            int count = 0;

            try(ResultSet rst = pstmt.executeQuery()){
                while (rst.next()) {
                    String Fri_number = rst.getString("fri_number");
                    count+=Integer.parseInt(Fri_number);
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

                String per_id=rst.getString("perid");
                StringBuffer perid_gai =new StringBuffer();
                try{
                    // 生成密钥
                    String keyHex = "0123456789ABCDEF0123456789ABCDEF";
                    byte[] keyData = Hex.decode(keyHex);
                    SecretKey key = new SecretKeySpec(keyData, "SM4");

                    // 定义初始向量（IV）
                    String ivHex = "00000000000000000000000000000000";
                    byte[] ivData = Hex.decode(ivHex);

                    byte[] encryptedFromHex = Hex.decode(per_id);
                    SM4 sm4 = new SM4();
// 解密
                    byte[] decrypted = sm4.decrypt(encryptedFromHex, key, ivData);
                    per_id = new String(decrypted);

                    perid_gai.append(per_id.substring(0, 6));
                    perid_gai.append("********");
                    perid_gai.append(per_id.substring(14,18));

                }catch (Exception e) {
                    System.err.println("Error: " + e.getMessage());
                }
                rezv.setPerid(perid_gai.toString());

                String phonenumber=rst.getString("phonenumber");
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
                rezv.setPhoneNumber(phonenumber_gai.toString());

                rezv.setSerid(rst.getString("serid"));
                rezv.setApplytime(rst.getString("applytime"));
                rezv.setCampus(rst.getString("campus"));
                rezv.setIntime(rst.getString("intime"));
                rezv.setOuttime(rst.getString("outtime"));
                rezv.setUnit(rst.getString("unit"));
                rezv.setVehicle(rst.getString("vehicle"));
                rezv.setVname(rst.getString("vname"));

                String serid =rst.getString("serid");
                String Fri_number=rst.getString("fri_number");
                rezv.setFri_number(Fri_number);
                if (!Fri_number.equals("0")){
                    String sql2="select * "
                            +"from rezv_friend where  serid=?";
                    ArrayList<Person> friends = new ArrayList<>();
                    try(PreparedStatement pstmt1 = dbconn.prepareStatement(sql2);){
                        pstmt1.setString(1,serid);
                        ResultSet rst1 = pstmt1.executeQuery();
                        while(rst1.next()){
                            Person friend=new Person();
                            friend.setName(rst1.getString("fri_name"));

                            String fri_perid=rst1.getString("fri_perid");
                            StringBuffer fri_perid_gai=new StringBuffer();
                            try{
                                // 生成密钥
                                String keyHex = "0123456789ABCDEF0123456789ABCDEF";
                                byte[] keyData = Hex.decode(keyHex);
                                SecretKey key = new SecretKeySpec(keyData, "SM4");

                                // 定义初始向量（IV）
                                String ivHex = "00000000000000000000000000000000";
                                byte[] ivData = Hex.decode(ivHex);

                                byte[] encryptedFromHex = Hex.decode(fri_perid);
                                SM4 sm4 = new SM4();
// 解密
                                byte[] decrypted = sm4.decrypt(encryptedFromHex, key, ivData);
                                fri_perid = new String(decrypted);
                                fri_perid_gai.append(fri_perid.substring(0,6));
                                fri_perid_gai.append("********");
                                fri_perid_gai.append(fri_perid.substring(14,18));
                            }catch (Exception e) {
                                System.err.println("Error: " + e.getMessage());
                            }
                            friend.setPerid(fri_perid_gai.toString());

                            String fri_phone=rst1.getString("fri_phonenumber");
                            StringBuffer fri_phone_gai=new StringBuffer();
                            try{
                                // 生成密钥
                                String keyHex = "0123456789ABCDEF0123456789ABCDEF";
                                byte[] keyData = Hex.decode(keyHex);
                                SecretKey key = new SecretKeySpec(keyData, "SM4");

                                // 定义初始向量（IV）
                                String ivHex = "00000000000000000000000000000000";
                                byte[] ivData = Hex.decode(ivHex);

                                byte[] encryptedFromHex = Hex.decode(fri_phone);
                                SM4 sm4 = new SM4();
// 解密
                                byte[] decrypted = sm4.decrypt(encryptedFromHex, key, ivData);
                                fri_phone = new String(decrypted);
                                fri_phone_gai.append(fri_phone.substring(0,3));
                                fri_phone_gai.append("****");
                                fri_phone_gai.append(fri_phone.substring(7,11));
                            }catch (Exception e) {
                                System.err.println("Error: " + e.getMessage());
                            }
                            friend.setPhoneNumber(fri_phone_gai.toString());

                            friends.add(friend);
                        }
                    }

                    rezv.setFriend(friends);
                }


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
                rezv.setVname(rst.getString("vname"));
                rezv.setFri_number(rst.getString("fri_number"));
                rezv.setQrcode(rst.getString("qrcode"));
                int n= Integer.parseInt(rst.getString("fri_number"));
                if(n!=0){
                    String sql2="select * "
                            +"from rezv_friend where serid = ?";

                    ArrayList<Person> friends=new ArrayList<>();
                    try(PreparedStatement pstmt1 = dbconn.prepareStatement(sql2);){
                        pstmt1.setString(1,serid);
                        ResultSet rst1 = pstmt1.executeQuery();
                        while(rst1.next()){
                            Person friend=new Person();
                            friend.setName(rst1.getString("fri_name"));
                            friend.setPerid(rst1.getString("fri_perid"));
                            friend.setPhoneNumber(rst1.getString("fri_phonenumber"));
                            friends.add(friend);
                        }
                    }

                    rezv.setFriend(friends);
                }



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
