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

public class RezvtionpublicDao implements Basedao{
    public boolean addRezvtion(Reservation_public reservation) throws DaoException{
        String sql ="insert into rezvtionpub values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
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
            pstmt.setString(13,reservation.getVisitunit());
            pstmt.setString(14,reservation.getReceptionist());
            pstmt.setString(15,reservation.getReason());
            pstmt.setString(16,reservation.getStatus());
            pstmt.setString(17,reservation.getQrcode());
            pstmt.executeUpdate();
            //return true;
        }catch (SQLException ne){
            ne.printStackTrace();
            return false;
        }if(n!=0){
            for(Person friend:friends){
                sql ="insert into rezvpub_friend (serid,fri_name,fri_perid,fri_phonenumber) values(?,?,?,?)";
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
    public ArrayList<Reservation_public> find(Reservation_public reservation) throws DaoException{
        String sql="select * "
                +"from rezvtionpub where  1=1";
        ArrayList<Reservation_public> rezvlist = new ArrayList<Reservation_public>();

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

        if(!reservation.getVisitunit().equals("null"))
            sql+="and visitunit like ?" ;
        if(!reservation.getReceptionist().equals("null"))
            sql+="and receptionist like ?" ;
        if(!reservation.getReason().equals("null"))
            sql+="and reason like ?" ;
        if(!reservation.getStatus().equals("null"))
            sql+="and status like ?" ;

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

            if(!reservation.getVisitunit().equals("null")){
                t++;
                pstmt.setString(t,"%"+reservation.getVisitunit()+"%");
            }
            if(!reservation.getReceptionist().equals("null")){
                t++;
                pstmt.setString(t,"%"+reservation.getReceptionist()+"%");
            }
            if(!reservation.getReason().equals("null")){
                t++;
                pstmt.setString(t,"%"+reservation.getReason()+"%");
            }
            if(!reservation.getStatus().equals("null")){
                t++;
                pstmt.setString(t,"%"+reservation.getStatus()+"%");
            }

            try(ResultSet rst = pstmt.executeQuery()){
                while(rst.next()){
                    Reservation_public rezv = new Reservation_public();
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
                                +"from rezvpub_friend where  serid=?";
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


                    rezv.setVisitunit(rst.getString("visitunit"));
                    rezv.setReceptionist(rst.getString("receptionist"));
                    rezv.setReason(rst.getString("reason"));
                    rezv.setStatus(rst.getString("status"));

                    rezvlist.add(rezv);
                }
            }
            return rezvlist;
        }catch (SQLException ne){
            ne.printStackTrace();
            return null;
        }
    }
    public int CountRezv(String intime,String visitunit) throws DaoException{
        String sql="select count (*) as total "
                +"from rezvtionpub where  intime like ? and visitunit = ?";



        try( Connection dbconn =getConnection();
             PreparedStatement pstmt = dbconn.prepareStatement(sql)){
            pstmt.setString(1,intime+"%");
            pstmt.setString(2,visitunit);

            int count =0;

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
    public ArrayList<Reservation_public> findmyRezv(String name, String perid, String phoneNumber) throws DaoException{
        String sql="select * "
                +"from rezvtionpub where  name=? and perid=? and phonenumber=?";
        ArrayList<Reservation_public> rezvlist = new ArrayList<Reservation_public>();

        try( Connection dbconn =getConnection();
             PreparedStatement pstmt = dbconn.prepareStatement(sql)){
            pstmt.setString(1,name);
            pstmt.setString(2,perid);
            pstmt.setString(3,phoneNumber);


            try(ResultSet rst = pstmt.executeQuery()){
                while(rst.next()){
                    Reservation_public rezv = new Reservation_public();
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
                                +"from rezvpub_friend where  serid=?";
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

                    rezv.setVisitunit(rst.getString("visitunit"));
                    rezv.setReceptionist(rst.getString("receptionist"));
                    rezv.setReason(rst.getString("reason"));
                    rezv.setStatus(rst.getString("status"));
                    rezvlist.add(rezv);
                }
            }
            return rezvlist;
        }catch (SQLException ne){
            ne.printStackTrace();
            return null;
        }
    }
    public int CountPeople(String intime,String visitunit) throws DaoException{
        String sql="select fri_number "
                +"from rezvtionpub where  intime like ? and visitunit = ?  ";



        try( Connection dbconn =getConnection();
             PreparedStatement pstmt = dbconn.prepareStatement(sql)){
            int t=0;
            pstmt.setString(1,intime+"%");
            pstmt.setString(2,visitunit);

            int count =0;

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
    public  ArrayList<Reservation_public>findAllrezv()throws DaoException{
        String sql="select * "
                +"from rezvtionpub ";
        ArrayList<Reservation_public> rezvlist = new ArrayList<Reservation_public>();
        try( Connection dbconn =getConnection();
             PreparedStatement pstmt = dbconn.prepareStatement(sql);
             ResultSet rst = pstmt.executeQuery()){
            while(rst.next()){
                Reservation_public rezv = new Reservation_public();
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
                            +"from rezvpub_friend where  serid=?";
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

                rezv.setVisitunit(rst.getString("visitunit"));
                rezv.setReceptionist(rst.getString("receptionist"));
                rezv.setReason(rst.getString("reason"));
                rezv.setStatus(rst.getString("status"));

                rezvlist.add(rezv);
            }
            return rezvlist;
        }catch (SQLException ne){
            ne.printStackTrace();
            return null;
        }
    }
    public  ArrayList<Reservation_public>findBystatus()throws DaoException{
        String sql="select * "
                +"from rezvtionpub where status='未审核' ";
        ArrayList<Reservation_public> rezvlist = new ArrayList<Reservation_public>();
        try( Connection dbconn =getConnection();
             PreparedStatement pstmt = dbconn.prepareStatement(sql);
             ResultSet rst = pstmt.executeQuery()){
            while(rst.next()){
                Reservation_public rezv = new Reservation_public();
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
                            +"from rezvpub_friend where  serid=?";
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

                rezv.setVisitunit(rst.getString("visitunit"));
                rezv.setReceptionist(rst.getString("receptionist"));
                rezv.setReason(rst.getString("reason"));
                rezv.setStatus(rst.getString("status"));

                rezvlist.add(rezv);
            }
            return rezvlist;
        }catch (SQLException ne){
            ne.printStackTrace();
            return null;
        }
    }
    public boolean updateStatus(String status,String serid) throws DaoException{
        String sql ="update rezvtionpub set status=? where serid=?";
        try(Connection dbconn = getConnection();
            PreparedStatement pstmt = dbconn.prepareStatement(sql)){
            pstmt.setString(1,status);
            pstmt.setString(2,serid);
            pstmt.executeUpdate();
            return true;
        }catch (SQLException ne){
            ne.printStackTrace();
            return false;
        }
    } //审核
    public Reservation_public findByserid(String serid) throws  DaoException{
        String sql="select * "
                +"from rezvtionpub where serid = ?";
        Reservation_public rezv = new Reservation_public();
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
                rezv.setVisitunit(rst.getString("visitunit"));
                rezv.setReceptionist(rst.getString("receptionist"));
                rezv.setReason(rst.getString("reason"));
                rezv.setStatus(rst.getString("status"));
                rezv.setQrcode(rst.getString("qrcode"));
                int n= Integer.parseInt(rst.getString("fri_number"));
                if(n!=0){
                    String sql2="select * "
                            +"from rezvpub_friend where serid = ?";

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
    //添加查找有最新的serid的记录的函数。根据会话里的serid 直接用这个
    public boolean updateQrcode(String qrcode,String serid) throws DaoException{
        String sql ="update rezvtionpub set qrcode=? where serid=?";
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
    }  //审核后生成的二维码地址不变 可能这个不需要？或者再加个字段 一个二维码地址一个二维码内容。 不用加二维码内容
    //有地址就能重新生成二维码，现在的思路是每次查看二维码都是重新生成二维码。所以这个应该是用不到
//    public boolean deleteUser(String uname) throws DaoException{
//        String sql ="delete from users where uname=?";
//        try(Connection dbconn = getConnection();
//            PreparedStatement pstmt = dbconn.prepareStatement(sql)){
//            pstmt.setString(1,uname);
//            pstmt.executeUpdate();
//            return true;
//        }catch (SQLException ne){
//            ne.printStackTrace();
//            return false;
//        }
//    }  好像不需要

    //后面是针对部门管理员的
    public  ArrayList<Reservation_public>findDBystatus(String visitunit)throws DaoException{
        String sql="select * "
                +"from rezvtionpub where status='未审核' AND visitunit=?";
        ArrayList<Reservation_public> rezvlist = new ArrayList<Reservation_public>();
        try( Connection dbconn =getConnection();
             PreparedStatement pstmt = dbconn.prepareStatement(sql);){
             pstmt.setString(1,visitunit);
             ResultSet rst = pstmt.executeQuery();
            while(rst.next()){
                Reservation_public rezv = new Reservation_public();
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
                            +"from rezvpub_friend where  serid=?";
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

                rezv.setVisitunit(rst.getString("visitunit"));
                rezv.setReceptionist(rst.getString("receptionist"));
                rezv.setReason(rst.getString("reason"));
                rezv.setStatus(rst.getString("status"));

                rezvlist.add(rezv);
            }
            return rezvlist;
        }catch (SQLException ne){
            ne.printStackTrace();
            return null;
        }
    }
    public  ArrayList<Reservation_public>findAllDrezv(String visitunit)throws DaoException{
        String sql="select * "
                +"from rezvtionpub WHERE visitunit=?";
        ArrayList<Reservation_public> rezvlist = new ArrayList<Reservation_public>();
        try( Connection dbconn =getConnection();
             PreparedStatement pstmt = dbconn.prepareStatement(sql);){
            pstmt.setString(1,visitunit);
             ResultSet rst = pstmt.executeQuery();
            while(rst.next()){
                Reservation_public rezv = new Reservation_public();
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
                            +"from rezvpub_friend where  serid=?";
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

                rezv.setVisitunit(rst.getString("visitunit"));
                rezv.setReceptionist(rst.getString("receptionist"));
                rezv.setReason(rst.getString("reason"));
                rezv.setStatus(rst.getString("status"));

                rezvlist.add(rezv);
            }
            return rezvlist;
        }catch (SQLException ne){
            ne.printStackTrace();
            return null;
        }
    }
}
