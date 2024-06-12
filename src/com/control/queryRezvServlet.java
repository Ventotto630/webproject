package com.control;

import com.dao.RezvtionDao;
import com.model.Customer;
import com.model.Person;
import com.model.Reservation;
import com.utils.SM4;
import org.bouncycastle.util.encoders.Hex;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;
//管理员查询
@WebServlet("/query-Rezv.do")
public class queryRezvServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RezvtionDao dao = new RezvtionDao();
        String message;
        try{
            ArrayList<Reservation> rezvlist=dao.findAllrezv();
            if(!rezvlist.isEmpty()){
                request.getSession().setAttribute("rezvlist",rezvlist);
                response.sendRedirect("Rezvtion/displayRezvtion.jsp");
            }
            else {
                message="没有相关查询记录";
                request.getSession().setAttribute("message",message);
                response.sendRedirect("Manage/school/rezvtion/queryRezvtion.jsp");
            }
        }catch (Exception e){
            e.printStackTrace();
            message="查询失败";
            request.getSession().setAttribute("message",message);
            response.sendRedirect("Manage/school/rezvtion/queryRezvtion.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("GBK");
        response.setCharacterEncoding("GBK");
        String name = request.getParameter("name");
        String perid = request.getParameter("perid");
        if(!perid.equals("null")){
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
        }

        String phoneNumber = request.getParameter("phoneNumber");
        if(!phoneNumber.equals("null")){
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
        }

        String serid = request.getParameter("serid");
        String applytime = request.getParameter("applytime");
        if (applytime == "") {
            applytime = "null"; // 或者任何默认值
        }
        String campus = request.getParameter("campus");
        if (campus == null) {
            campus = "null"; // 或者任何默认值
        }
        String intime = request.getParameter("intime");
        if (intime == "") {
            intime = "null"; // 或者任何默认值
        }
        String outtime = request.getParameter("outtime");
        if (outtime == "") {
            outtime = "null"; // 或者任何默认值
        }
        String unit = request.getParameter("unit");
        String vehicle = request.getParameter("vehicle");
        String vname = request.getParameter("vname");
        String Fri_number = "0";
        ArrayList<Person> friends = new ArrayList<>();
//随行人员不参与查询


        Reservation reservation = new Reservation(name,perid,phoneNumber,serid,applytime,campus,intime,outtime,unit,vehicle,vname,Fri_number,friends,"null");

        try{
            RezvtionDao dao = new RezvtionDao();
            ArrayList<Reservation> rezvlist = dao.find(reservation);
            if(!rezvlist.isEmpty()){
                request.getSession().setAttribute("rezvlist",rezvlist);
                response.sendRedirect("Rezvtion/displayRezvtion.jsp");
            }
            else response.sendRedirect("Rezvtion/error.jsp");
        }catch (Exception e1){
            e1.printStackTrace();
        }
    }
}
