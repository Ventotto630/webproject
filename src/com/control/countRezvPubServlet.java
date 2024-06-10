package com.control;

import com.dao.RezvtionpublicDao;
import com.model.Person;
import com.model.Reservation_public;
import com.utils.SM4;
import org.bouncycastle.util.encoders.Hex;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/countRezvPubServlet")
public class countRezvPubServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("GBK");
        response.setCharacterEncoding("GBK");
        String name = request.getParameter("name");
        String perid = request.getParameter("perid");
        if(!perid.equals("null")){
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
        }
        String phoneNumber = request.getParameter("phoneNumber");
        if(!phoneNumber.equals("null")){
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
        }
        String serid = request.getParameter("serid");
        String applytime = request.getParameter("applytime");
        if (applytime == "") {
            applytime = "null"; // �����κ�Ĭ��ֵ
        }
        String campus = request.getParameter("campus");
        if (campus == null) {
            campus = "null"; // �����κ�Ĭ��ֵ
        }
        String intime = request.getParameter("intime");
        if (intime == "") {
            intime = "null"; // �����κ�Ĭ��ֵ
        }
        String outtime = request.getParameter("outtime");
        if (outtime == "") {
            outtime = "null"; // �����κ�Ĭ��ֵ
        }
        String unit = request.getParameter("unit");
        String vehicle = request.getParameter("vehicle");
        String vname = request.getParameter("vname");
        String Fri_name = "null";
        String Fri_perid = "null";
        String Fri_phoneNumber = "null";
        String visitunit = request.getParameter("visitunit");
        String receptionist = request.getParameter("receptionist");
        String reason = request.getParameter("reason");
        String status = request.getParameter("status");

        Person friend = new Person();
        friend.setName(Fri_name);
        friend.setPerid(Fri_perid);
        friend.setPhoneNumber(Fri_phoneNumber);

        Reservation_public reservationpub = new Reservation_public(name,perid,phoneNumber,serid,applytime,campus,intime,outtime,unit,vehicle,vname,friend,visitunit,receptionist,reason,status,"null");

        try{
            RezvtionpublicDao dao = new RezvtionpublicDao();
            int cishu = 0;
            int people = 0;
            cishu = dao.CountRezv(reservationpub);
            people = dao.CountPeople(reservationpub);
            people+=cishu;

            request.getSession().setAttribute("cishu",cishu);
            request.getSession().setAttribute("people",people);
            response.sendRedirect("Manage/displaycountRezvtionPub.jsp");

        }catch (Exception e1){
            e1.printStackTrace();
        }
    }
}
