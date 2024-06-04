package com.control;

import com.dao.RezvtionDao;
import com.model.Person;
import com.model.Reservation;
import com.utils.CryptoSM3;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

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
        String phoneNumber = request.getParameter("phoneNumber");
        //String serid = request.getParameter("serid");
        String applytime = request.getParameter("applytime");
        String campus = request.getParameter("campus");
        String intime = request.getParameter("intime");
        String outtime = request.getParameter("ouottime");
        String unit = request.getParameter("unit");
        String vehicle = request.getParameter("vehicle");
        String vname = request.getParameter("vname");
        String Fri_name = request.getParameter("Fri_name");
        String Fri_perid = request.getParameter("Fri_perid");
        String Fri_phoneNumber = request.getParameter("Fri_phoneNumber");
        //LocalDateTime time1= LocalDateTime.now(); 这个在jsp中比较
//        LocalDateTime time2= LocalDateTime.parse(intime);
//        LocalDateTime time3= LocalDateTime.parse(outtime);

        //jsp传入的类型为datatime-local
        String serid = new String();
        byte[] sm3 = CryptoSM3.hash(name.getBytes());
        String ssm=CryptoSM3.bytesToHexString(sm3);
        serid=ssm+applytime; //订单号

       // String data = name+'|'+perid+'|'+phoneNumber+'|'+serid+'|'+applytime+'|'+campus+'|'+intime+'|'+outtime+'|'+unit+'|'+vehicle+'|'+vname+'|'+Fri_name+'|'+Fri_perid+'|'+Fri_phoneNumber;
        String filepath = "X:\\code\\java\\demo1\\webproject\\QRCode\\"+serid+".png";

        Person friend = new Person();
        friend.setName(Fri_name);
        friend.setPerid(Fri_perid);
        friend.setPhoneNumber(Fri_phoneNumber);

        Reservation reservation = new Reservation(name,perid,phoneNumber,serid,applytime,campus,intime,outtime,unit,vehicle,vname,friend,filepath);
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
