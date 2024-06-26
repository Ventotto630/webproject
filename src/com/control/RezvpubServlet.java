package com.control;

import com.dao.RezvtionpublicDao;
import com.model.Person;
import com.model.Reservation_public;
import com.utils.CryptoSM3;
import com.utils.SM4;
import org.bouncycastle.util.encoders.Hex;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/RezvpubServlet")
public class RezvpubServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
//RezvpubServlet ��QrcodepubServlet �� displayPubQRCode ����ʹ�� 1����������Ϣ 2�������ɶ�ά��3����չʾ
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("GBK");
        response.setCharacterEncoding("GBK");
        String name = request.getParameter("name");
        String perid = request.getParameter("perid");
        try {
            // ����ԭʼ����
            //String plaintext = "Hello, World!";
            byte[] input = perid.getBytes();

            // ������Կ
            String keyHex = "0123456789ABCDEF0123456789ABCDEF";
            byte[] keyData = Hex.decode(keyHex);
            SecretKey key = new SecretKeySpec(keyData, "SM4");

            // �����ʼ������IV��
            String ivHex = "00000000000000000000000000000000";
            byte[] ivData = Hex.decode(ivHex);

            // ����
            SM4 sm4 = new SM4();
            byte[] encrypted = sm4.encrypt(input, key, ivData);
            perid = Hex.toHexString(encrypted);
        }catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }


        String phoneNumber = request.getParameter("phoneNumber");
        try {
            // ����ԭʼ����
            //String plaintext = "Hello, World!";
            byte[] input = phoneNumber.getBytes();

            // ������Կ
            String keyHex = "0123456789ABCDEF0123456789ABCDEF";
            byte[] keyData = Hex.decode(keyHex);
            SecretKey key = new SecretKeySpec(keyData, "SM4");

            // �����ʼ������IV��
            String ivHex = "00000000000000000000000000000000";
            byte[] ivData = Hex.decode(ivHex);

            // ����
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
            vname = "null"; // �����κ�Ĭ��ֵ
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
                    // ����ԭʼ����
                    //String plaintext = "Hello, World!";
                    byte[] input = Fri_perid[i].getBytes();

                    // ������Կ
                    String keyHex = "0123456789ABCDEF0123456789ABCDEF";
                    byte[] keyData = Hex.decode(keyHex);
                    SecretKey key = new SecretKeySpec(keyData, "SM4");

                    // �����ʼ������IV��
                    String ivHex = "00000000000000000000000000000000";
                    byte[] ivData = Hex.decode(ivHex);

                    // ����
                    SM4 sm4 = new SM4();
                    byte[] encrypted = sm4.encrypt(input, key, ivData);
                    Fri_perid[i] = Hex.toHexString(encrypted);
                }catch (Exception e) {
                    System.err.println("Error: " + e.getMessage());
                }
                friend.setPerid(Fri_perid[i]);

                try {
                    // ����ԭʼ����
                    //String plaintext = "Hello, World!";
                    byte[] input = Fri_phoneNumber[i].getBytes();

                    // ������Կ
                    String keyHex = "0123456789ABCDEF0123456789ABCDEF";
                    byte[] keyData = Hex.decode(keyHex);
                    SecretKey key = new SecretKeySpec(keyData, "SM4");

                    // �����ʼ������IV��
                    String ivHex = "00000000000000000000000000000000";
                    byte[] ivData = Hex.decode(ivHex);

                    // ����
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

        String visitunit = request.getParameter("visitunit");
        String receptionist = request.getParameter("receptionist");
        String reason = request.getParameter("reason");
        //String status = request.getParameter("status");
        String status ="δ���";
       // LocalDateTime time1= LocalDateTime.now();
//        LocalDateTime time2= LocalDateTime.parse(intime);
//        LocalDateTime time3= LocalDateTime.parse(outtime);

        //jsp���������Ϊdatatime-local
        applytime = applytime.replace("-","");
        applytime = applytime.replace(":","");
        intime = intime.replace("T"," ");
        outtime = outtime.replace("T"," ");
        String serid = new String();
        byte[] sm3 = CryptoSM3.hash(name.getBytes());
        String ssm=CryptoSM3.bytesToHexString(sm3);
        serid=ssm+applytime; //������


       // String data = name+'|'+perid+'|'+phoneNumber+'|'+serid+'|'+applytime+'|'+campus+'|'+intime+'|'+outtime+'|'+unit+'|'+vehicle+'|'+vname+'|'+Fri_name+'|'+Fri_perid+'|'+Fri_phoneNumber;
        String filepath = "X:\\code\\java\\demo1\\webproject\\QRCode\\"+serid+".png";



        Reservation_public reservationpub = new Reservation_public(name,perid,phoneNumber,serid,applytime,campus,intime,outtime,unit,vehicle,vname,Fri_number,friends,visitunit,receptionist,reason,status,filepath);
        RezvtionpublicDao rezvtionpublicDao = new RezvtionpublicDao();
        try{
            rezvtionpublicDao.addRezvtion(reservationpub);
        }catch (Exception e){
            e.printStackTrace();
            //message="<li>�����¼����</li>"+e;
        }
        HttpSession session = request.getSession();
        synchronized (session){
            session.setAttribute("reservationpub",reservationpub); //����ֱ�Ӵ�serid Ȼ����serid���Ҽ�¼ ���� �������displatPubQRCode
            session.setAttribute("serid",serid);
        }

        RequestDispatcher rd = request.getRequestDispatcher("/Rezvtion/checking.jsp");
        rd.forward(request,response);
        //ת��ԤԼ�ɹ�ҳ�� ������ʾ�����ַ;//��ʾ����ҳ���������serid
    }
}
