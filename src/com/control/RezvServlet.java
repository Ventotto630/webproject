package com.control;

import com.dao.RezvtionDao;
import com.model.Person;
import com.model.Reservation;
import com.utils.CryptoSM3;
import org.bouncycastle.util.encoders.Hex;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;

import com.utils.SM4;
@WebServlet("/RezvServlet")
public class RezvServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("GBK");
        response.setCharacterEncoding("GBK");
        String name = request.getParameter("name");
        String perid = request.getParameter("perid");

        try {
            // 定义原始数据
            //String plaintext = "Hello, World!";
            byte[] input = perid.getBytes();

            // 生成密钥
            String keyHex = "0123456789ABCDEF0123456789ABCDEF";
            byte[] keyData = Hex.decode(keyHex);
            SecretKey key = new SecretKeySpec(keyData, "SM4");

            // 定义初始向量（IV）
            String ivHex = "00000000000000000000000000000000";
            byte[] ivData = Hex.decode(ivHex);

            // 加密
            SM4 sm4 = new SM4();
            byte[] encrypted = sm4.encrypt(input, key, ivData);
            perid = Hex.toHexString(encrypted);
        }catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }


        String phoneNumber = request.getParameter("phoneNumber");
        try {
            // 定义原始数据
            //String plaintext = "Hello, World!";
            byte[] input = phoneNumber.getBytes();

            // 生成密钥
            String keyHex = "0123456789ABCDEF0123456789ABCDEF";
            byte[] keyData = Hex.decode(keyHex);
            SecretKey key = new SecretKeySpec(keyData, "SM4");

            // 定义初始向量（IV）
            String ivHex = "00000000000000000000000000000000";
            byte[] ivData = Hex.decode(ivHex);

            // 加密
            SM4 sm4 = new SM4();
            byte[] encrypted = sm4.encrypt(input, key, ivData);
            phoneNumber = Hex.toHexString(encrypted);
        }catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }

        //String serid = request.getParameter("serid");
        String applytime = request.getParameter("applytime");
        String campus = request.getParameter("campus");
        String intime = request.getParameter("intime");
        String outtime = request.getParameter("outtime");
        String unit = request.getParameter("unit");
        String vehicle = request.getParameter("vehicle");
        String vname = request.getParameter("vname");
        if (vname == "") {
            vname = "null"; // 或者任何默认值
        }
        String Fri_number = request.getParameter("Fri_number");
        ArrayList<Person> friends=new ArrayList<>();
        if(Fri_number.equals("0")){
//            Person friend= new Person();
//            friend.setName("null");
//            friend.setPerid("null");
//            friend.setPhoneNumber("null");
//            friends.add(friend);
        }else
        {
            String[] Fri_names = request.getParameterValues("Fri_name");
            String[] Fri_perid = request.getParameterValues("Fri_perid");
            String[] Fri_phoneNumber = request.getParameterValues("Fri_phoneNumber");
            for(int i = 0;i<Fri_names.length;i++){
                Person friend =new Person();
                friend.setName(Fri_names[i]);
                try {
                    // 定义原始数据
                    //String plaintext = "Hello, World!";
                    byte[] input = Fri_perid[i].getBytes();

                    // 生成密钥
                    String keyHex = "0123456789ABCDEF0123456789ABCDEF";
                    byte[] keyData = Hex.decode(keyHex);
                    SecretKey key = new SecretKeySpec(keyData, "SM4");

                    // 定义初始向量（IV）
                    String ivHex = "00000000000000000000000000000000";
                    byte[] ivData = Hex.decode(ivHex);

                    // 加密
                    SM4 sm4 = new SM4();
                    byte[] encrypted = sm4.encrypt(input, key, ivData);
                    Fri_perid[i] = Hex.toHexString(encrypted);
                }catch (Exception e) {
                    System.err.println("Error: " + e.getMessage());
                }
                friend.setPerid(Fri_perid[i]);

                try {
                    // 定义原始数据
                    //String plaintext = "Hello, World!";
                    byte[] input = Fri_phoneNumber[i].getBytes();

                    // 生成密钥
                    String keyHex = "0123456789ABCDEF0123456789ABCDEF";
                    byte[] keyData = Hex.decode(keyHex);
                    SecretKey key = new SecretKeySpec(keyData, "SM4");

                    // 定义初始向量（IV）
                    String ivHex = "00000000000000000000000000000000";
                    byte[] ivData = Hex.decode(ivHex);

                    // 加密
                    SM4 sm4 = new SM4();
                    byte[] encrypted = sm4.encrypt(input, key, ivData);
                    Fri_phoneNumber[i] = Hex.toHexString(encrypted);
                }catch (Exception e) {
                    System.err.println("Error: " + e.getMessage());
                }
                friend.setPhoneNumber(Fri_phoneNumber[i]);
                friends.add(friend);
            }
        }



        //LocalDateTime time1= LocalDateTime.now(); 这个在jsp中比较
//        LocalDateTime time2= LocalDateTime.parse(intime);
//        LocalDateTime time3= LocalDateTime.parse(outtime);

        //jsp传入的类型为datatime-local
        applytime = applytime.replace("-","");
        applytime = applytime.replace(":","");
        intime = intime.replace("T"," ");
        outtime = outtime.replace("T"," ");
        String serid = new String();
        byte[] sm3 = CryptoSM3.hash(name.getBytes());
        String ssm=CryptoSM3.bytesToHexString(sm3);
        serid=ssm+applytime; //订单号

       // String data = name+'|'+perid+'|'+phoneNumber+'|'+serid+'|'+applytime+'|'+campus+'|'+intime+'|'+outtime+'|'+unit+'|'+vehicle+'|'+vname+'|'+Fri_name+'|'+Fri_perid+'|'+Fri_phoneNumber;
        String filepath = "X:\\code\\java\\demo1\\webproject\\QRCode\\"+serid+".png";



        Reservation reservation = new Reservation(name,perid,phoneNumber,serid,applytime,campus,intime,outtime,unit,vehicle,vname,Fri_number,friends,filepath);
        RezvtionDao rezvtionDao = new RezvtionDao();
        try{
            rezvtionDao.addRezvtion(reservation);
        }catch (Exception e){
                e.printStackTrace();
              //message="<li>插入记录错误！</li>"+e;
          }
        HttpSession session = request.getSession();
        synchronized (session){
                session.setAttribute("reservation",reservation);
                session.setAttribute("serid",serid);
            }

        RequestDispatcher rd = request.getRequestDispatcher("/Rezvtion/applysuccess.jsp");
        rd.forward(request,response);
        //RezvQRCode qrCode = new RezvQRCode();
//        int oncolor;
//        if(time1.isAfter(time3) || time1.isBefore(time2)){
//            oncolor=0xFF808080;
//        }
//        else oncolor =0xFF800080;
//        String filepath_xd;
//        try{
//            filepath_xd =qrCode.generateQRCode(data,filepath,"PNG",400,oncolor,0xFFFFFFFF);
//
//
//            RequestDispatcher rd = request.getRequestDispatcher("/bank/displayQRCode.jsp"); 要改
//            rd.forward(request,response);
//        }catch (Exception e){}
    }
}
