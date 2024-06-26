package com.control.departadmin;

import com.dao.DepartDao;
import com.dao.RezvtionpublicDao;
import com.model.Administrators;
import com.model.Department;
import com.model.Person;
import com.model.Reservation_public;
import com.utils.SM4;
import org.bouncycastle.util.encoders.Hex;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

//����Ա��ѯ
@WebServlet("/Dquery-RezvPub.do")
public class queryRezvPubServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RezvtionpublicDao dao = new RezvtionpublicDao();
        String message;
        HttpSession session=request.getSession();
        Administrators myadmin=(Administrators) session.getAttribute("myadmin");
        //���ڹ���Աֻ��id�ֶ�
        String visitunit=null;
        try { DepartDao dapartdao=new DepartDao();
            Department depart= dapartdao.findById(myadmin.getDepartmentID());
            visitunit= depart.getName();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try{
            ArrayList<Reservation_public> rezvlist=dao.findAllDrezv(visitunit);
            if(!rezvlist.isEmpty()){
                request.getSession().setAttribute("rezvlist",rezvlist);
                response.sendRedirect("Manage/school/rezv_public/displayRezvtionPub.jsp");
            }
            else  {
                message="û����ز�ѯ��¼";
                request.getSession().setAttribute("message",message);
                response.sendRedirect("Manage/school/rezv_public/queryRezvtionPub.jsp");
            }
        }catch (Exception e){
            e.printStackTrace();
            message="��ѯʧ��";
            request.getSession().setAttribute("message",message);
            response.sendRedirect("Manage/school/rezv_public/queryRezvtionPub.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("GBK");
        response.setCharacterEncoding("GBK");
        String name = request.getParameter("name");
        if(name.isEmpty()) name="null";
        String perid = request.getParameter("perid");
        String message;
        if(!perid.isEmpty()){
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
        else {
            perid="null";
        }
        String phoneNumber = request.getParameter("phoneNumber");
        if(!phoneNumber.isEmpty()){
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
        }else {
            phoneNumber="null";
        }

        String serid = request.getParameter("serid");
        if(serid.isEmpty()) serid="null";
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
        if(unit.isEmpty()) unit="null";
        String vehicle = request.getParameter("vehicle");
        String vname = request.getParameter("vname");
        if(vname.isEmpty()) vname="null";
        String Fri_number = "0";
        ArrayList<Person> friends = new ArrayList<>();
//������Ա�������ѯ
        HttpSession session=request.getSession();
        Administrators myadmin=(Administrators) session.getAttribute("myadmin");
        //���ڹ���Աֻ��id�ֶ�
        String visitunit=null;
        try { DepartDao dapartdao=new DepartDao();
            Department depart= dapartdao.findById(myadmin.getDepartmentID());
            visitunit= depart.getName();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String receptionist = request.getParameter("receptionist");
        if(receptionist.isEmpty()) receptionist="null";
        String reason = request.getParameter("reason");
        if(reason.isEmpty()) reason="null";
        String status = request.getParameter("status");
        if(status.isEmpty()) status="null";


        Reservation_public reservationpub = new Reservation_public(name,perid,phoneNumber,serid,applytime,campus,intime,outtime,unit,vehicle,vname,Fri_number,friends,visitunit,receptionist,reason,status,"null");

        try{
            RezvtionpublicDao dao = new RezvtionpublicDao();
            ArrayList<Reservation_public> rezvlist = dao.find(reservationpub);
            if(!rezvlist.isEmpty()){
                request.getSession().setAttribute("rezvlist",rezvlist);
                response.sendRedirect("Manage/school/rezv_public/displayRezvtionPub.jsp");
            }
            else {
                message="û�����ԤԼ��¼";
                request.getSession().setAttribute("message",message);
                response.sendRedirect("Manage/school/rezv_public/queryRezvtionPub.jsp");
            }
        }catch (Exception e1){
            e1.printStackTrace();
            message="��ѯʧ��";
            request.getSession().setAttribute("message",message);
            response.sendRedirect("Manage/school/rezv_public/queryRezvtionPub.jsp");
        }
    }
}
