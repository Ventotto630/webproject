package com.control.admin_rezv;

import com.dao.RezvtionDao;
import com.model.Person;
import com.model.Reservation;
import com.utils.SM4;
import org.bouncycastle.util.encoders.Hex;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/query-rezv.do")
public class queryRezvServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RezvtionDao dao = new RezvtionDao();
        String message;
        try{
            ArrayList<Reservation> rezvlist=dao.findAllrezv();
            if(!rezvlist.isEmpty()){
                request.getSession().setAttribute("rezvlist",rezvlist);
                response.sendRedirect("Manage/school/rezvtion/displayRezvtion.jsp");
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
        String message;
        String name = request.getParameter("name");
        if(name.isEmpty()) name="null";
        String perid = request.getParameter("perid");
        if(!perid.isEmpty()){
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
        else {
            perid="null";
        }
        String phoneNumber = request.getParameter("phoneNumber");
        if(!phoneNumber.isEmpty()){
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
        }else {
            phoneNumber="null";
        }
        String serid = request.getParameter("serid");
        if(serid.isEmpty()) serid="null";
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
        if(unit.isEmpty()) unit="null";
        String vehicle = request.getParameter("vehicle");
        String vname = request.getParameter("vname");
        if(vname.isEmpty()) vname="null";
        String Fri_number = "0";
        ArrayList<Person> friends = new ArrayList<>();
//随行人员不参与查询


        Reservation reservation = new Reservation(name,perid,phoneNumber,serid,applytime,campus,intime,outtime,unit,vehicle,vname,Fri_number,friends,"null");

        try{
            RezvtionDao dao = new RezvtionDao();
            ArrayList<Reservation> rezvlist = dao.find(reservation);
            if(!rezvlist.isEmpty()){
                request.getSession().setAttribute("rezvlist",rezvlist);
                response.sendRedirect("Manage/school/rezvtion/displayRezvtion.jsp");
            }
            else {
                message="没有相关查询记录";
                request.getSession().setAttribute("message",message);
                response.sendRedirect("Manage/school/rezvtion/queryRezvtion.jsp");
            }
        }catch (Exception e1){
            e1.printStackTrace();
            message="查询失败";
            request.getSession().setAttribute("message",message);
            response.sendRedirect("Manage/school/rezvtion/queryRezvtion.jsp");
        }
    }
}
