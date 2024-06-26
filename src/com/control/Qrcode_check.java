package com.control;

import com.dao.RezvtionDao;
import com.model.Person;
import com.model.Reservation;
import com.utils.RezvQRCode;
import com.utils.SM4;
import org.bouncycastle.util.encoders.Hex;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

@WebServlet("/Qrcode_check")
public class Qrcode_check extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sid =(String) request.getParameter("serid");
        RezvtionDao dao = new RezvtionDao();
        Reservation reservation = new Reservation();
        try {
            reservation = dao.findByserid(sid); //根据serid查询有最新的serid的记录;
        }catch (Exception ne){

        }
        String name = reservation.getName();
        StringBuffer maskedname= new StringBuffer();
        if (name.length() == 2) {
            // 名字为两个字，将第二个字变成*
            maskedname.append(name.charAt(0));
            maskedname.append("*");
        } else {
            // 名字超过两个字，将第一个字和最后一个字保留，中间部分用*替换

            maskedname.append(name.charAt(0)); // 保留第一个字
            for (int i = 1; i < name.length() - 1; i++) {
                maskedname.append('*');
            }
            maskedname.append(name.charAt(name.length() - 1)); // 保留最后一个字
        }
        name = maskedname.toString();

        String perid =reservation.getPerid();
        StringBuffer perid_gai =new StringBuffer();
        try{
            // 生成密钥
            String keyHex = "0123456789ABCDEF0123456789ABCDEF";
            byte[] keyData = Hex.decode(keyHex);
            SecretKey key = new SecretKeySpec(keyData, "SM4");

            // 定义初始向量（IV）
            String ivHex = "00000000000000000000000000000000";
            byte[] ivData = Hex.decode(ivHex);

            byte[] encryptedFromHex = Hex.decode(perid);
            SM4 sm4 = new SM4();
// 解密
            byte[] decrypted = sm4.decrypt(encryptedFromHex, key, ivData);
            perid = new String(decrypted);

            perid_gai.append(perid.substring(0, 6));
            perid_gai.append("********");
            perid_gai.append(perid.substring(14,18));
            perid=perid_gai.toString();
        }catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }

        String phoneNumber = reservation.getPhoneNumber();
        StringBuffer phonenumber_gai=new StringBuffer();
        try{
            // 生成密钥
            String keyHex = "0123456789ABCDEF0123456789ABCDEF";
            byte[] keyData = Hex.decode(keyHex);
            SecretKey key = new SecretKeySpec(keyData, "SM4");

            // 定义初始向量（IV）
            String ivHex = "00000000000000000000000000000000";
            byte[] ivData = Hex.decode(ivHex);

            byte[] encryptedFromHex = Hex.decode(phoneNumber);
            SM4 sm4 = new SM4();
// 解密
            byte[] decrypted = sm4.decrypt(encryptedFromHex, key, ivData);
            phoneNumber = new String(decrypted);
            phonenumber_gai.append(phoneNumber.substring(0,3));
            phonenumber_gai.append("****");
            phonenumber_gai.append(phoneNumber.substring(7,11));
            phoneNumber=phonenumber_gai.toString();
        }catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }

        String serid = reservation.getSerid();
        String applytime = reservation.getApplytime();
        String campus =reservation.getCampus();
        String intime = reservation.getIntime();
        String outtime =reservation.getOuttime();
        String unit = reservation.getUnit();
        String vehicle = reservation.getVehicle();
        String vname = reservation.getVname();
        String Fri_number = reservation.getFri_number();
        String Qrcode = reservation.getQrcode();
        String data = name+'|'+perid+'|'+phoneNumber+'|'+serid+'|'+applytime+'|'+campus+'|'+intime+'|'+outtime+'|'+unit+'|'+vehicle+'|'+vname+'|'+Fri_number;

        if(!Fri_number.equals("0")){
            ArrayList<Person> friends =reservation.getFriend();
            for(Person friend:friends){
                String Fri_name = friend.getName();

                String Fri_perid = friend.getPerid();
                StringBuffer fri_perid_gai=new StringBuffer();
                try{
                    // 生成密钥
                    String keyHex = "0123456789ABCDEF0123456789ABCDEF";
                    byte[] keyData = Hex.decode(keyHex);
                    SecretKey key = new SecretKeySpec(keyData, "SM4");

                    // 定义初始向量（IV）
                    String ivHex = "00000000000000000000000000000000";
                    byte[] ivData = Hex.decode(ivHex);

                    byte[] encryptedFromHex = Hex.decode(Fri_perid);
                    SM4 sm4 = new SM4();
// 解密
                    byte[] decrypted = sm4.decrypt(encryptedFromHex, key, ivData);
                    Fri_perid = new String(decrypted);
                    fri_perid_gai.append(Fri_perid.substring(0,6));
                    fri_perid_gai.append("********");
                    fri_perid_gai.append(Fri_perid.substring(14,18));
                    Fri_perid=fri_perid_gai.toString();
                }catch (Exception e) {
                    System.err.println("Error: " + e.getMessage());
                }

                String Fri_phoneNumber = friend.getPhoneNumber();
                StringBuffer fri_phone_gai=new StringBuffer();
                try{
                    // 生成密钥
                    String keyHex = "0123456789ABCDEF0123456789ABCDEF";
                    byte[] keyData = Hex.decode(keyHex);
                    SecretKey key = new SecretKeySpec(keyData, "SM4");

                    // 定义初始向量（IV）
                    String ivHex = "00000000000000000000000000000000";
                    byte[] ivData = Hex.decode(ivHex);

                    byte[] encryptedFromHex = Hex.decode(Fri_phoneNumber);
                    SM4 sm4 = new SM4();
// 解密
                    byte[] decrypted = sm4.decrypt(encryptedFromHex, key, ivData);
                    Fri_phoneNumber = new String(decrypted);
                    fri_phone_gai.append(Fri_phoneNumber.substring(0,3));
                    fri_phone_gai.append("****");
                    fri_phone_gai.append(Fri_phoneNumber.substring(7,11));
                    Fri_phoneNumber=fri_phone_gai.toString();
                }catch (Exception e) {
                    System.err.println("Error: " + e.getMessage());
                }
                data+="|"+Fri_name+"|"+Fri_perid+"|"+Fri_phoneNumber;
            }
        }
        intime=intime.replace(" ","T");
        outtime=outtime.replace(" ","T");
        LocalDateTime time1= LocalDateTime.now();
        LocalDateTime time2= LocalDateTime.parse(intime);
        LocalDateTime time3= LocalDateTime.parse(outtime);

        RezvQRCode qrCode = new RezvQRCode();
        int oncolor = 0;
        if(time1.isAfter(time3) || time1.isBefore(time2)){
            oncolor=0xFF808080;
        }
        else oncolor =0xFF800080;
        String filepath_xd;
        try{
            filepath_xd =qrCode.generateQRCode(data,Qrcode,"PNG",250,oncolor,0xFFFFFFFF);
            HttpSession session =request.getSession();
            synchronized (session){
                session.setAttribute("filepath_xd",filepath_xd);
                session.setAttribute("reservation",reservation);
            }

            RequestDispatcher rd = request.getRequestDispatcher("/Rezvtion/displayQRCode.jsp");
            rd.forward(request,response);
        }catch (Exception e){}
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
