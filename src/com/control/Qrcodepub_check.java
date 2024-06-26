package com.control;

import com.dao.RezvtionpublicDao;
import com.model.Person;
import com.model.Reservation_public;
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

@WebServlet("/Qrcodepub_check")
public class Qrcodepub_check extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sid =(String) request.getParameter("serid");
        RezvtionpublicDao dao = new RezvtionpublicDao();
        Reservation_public reservation_public = new Reservation_public();
        try {
            reservation_public = dao.findByserid(sid); //根据serid查询有最新的serid的记录;
        }catch (Exception ne){

        }
        String name = reservation_public.getName();
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

        String perid =reservation_public.getPerid();
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

        String phoneNumber = reservation_public.getPhoneNumber();
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

        String serid = reservation_public.getSerid();
        String applytime = reservation_public.getApplytime();
        String campus =reservation_public.getCampus();
        String intime = reservation_public.getIntime();
        String outtime =reservation_public.getOuttime();
        String unit = reservation_public.getUnit();
        String vehicle = reservation_public.getVehicle();
        String vname = reservation_public.getVname();
        String Fri_number = reservation_public.getFri_number();
        String path = reservation_public.getQrcode();
        String visitunit = reservation_public.getVisitunit();
        String receptionist = reservation_public.getReceptionist();
        String reason = reservation_public.getReason();
        String status = reservation_public.getStatus();
        String data = name+'|'+perid+'|'+phoneNumber+'|'+serid+'|'+applytime+'|'+campus+'|'+intime+'|'+outtime+'|'+unit+'|'+vehicle+'|'+vname+'|'+Fri_number+'|'+visitunit+'|'+receptionist+'|'+reason+'|'+status;


        if(!Fri_number.equals("0")){
            ArrayList<Person> friends =reservation_public.getFriend();
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
        String filepath_xd;

        int oncolor =0;
        if(status.equals("未审核") || status.equals("审核未通过") || time1.isAfter(time3) || time1.isBefore(time2) ){
            oncolor=0xFF808080; //不通过就是灰色
        }
        else if(status.equals("审核通过") && time1.isBefore(time3) && time1.isAfter(time2)){
            oncolor =0xFF800080;
        }

        try{
            filepath_xd =qrCode.generateQRCode(data,path,"PNG",250,oncolor,0xFFFFFFFF);

            HttpSession session =request.getSession();
            synchronized (session){
                session.setAttribute("filepath_xd",filepath_xd);
                session.setAttribute("reservationpub",reservation_public);
            }


            RequestDispatcher rd = request.getRequestDispatcher("/Rezvtion/displayPubQRCode.jsp"); //不改是不是也行 共用一个jsp; 不行 JavaBean不一样
            rd.forward(request,response);
        }catch (Exception e){

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
