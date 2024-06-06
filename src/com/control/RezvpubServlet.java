package com.control;

import com.dao.RezvtionpublicDao;
import com.model.Person;
import com.model.Reservation_public;
import com.utils.CryptoSM3;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/RezvpubServlet")
public class RezvpubServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
//RezvpubServlet 和QrcodepubServlet 和 displayPubQRCode 联合使用 1用来处理信息 2用来生成二维码3用来展示
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
        String outtime = request.getParameter("outtime");
        String unit = request.getParameter("unit");
        String vehicle = request.getParameter("vehicle");
        String vname = request.getParameter("vname");
        String Fri_name = request.getParameter("Fri_name");
        String Fri_perid = request.getParameter("Fri_perid");
        String Fri_phoneNumber = request.getParameter("Fri_phoneNumber");
        String visitunit = request.getParameter("visitunit");
        String receptionist = request.getParameter("receptionist");
        String reason = request.getParameter("reason");
        //String status = request.getParameter("status");
        String status ="未审核";
       // LocalDateTime time1= LocalDateTime.now();
//        LocalDateTime time2= LocalDateTime.parse(intime);
//        LocalDateTime time3= LocalDateTime.parse(outtime);

        //jsp传入的类型为datatime-local
        applytime = applytime.replace("-","");
        applytime = applytime.replace(":","");
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

        Reservation_public reservationpub = new Reservation_public(name,perid,phoneNumber,serid,applytime,campus,intime,outtime,unit,vehicle,vname,friend,visitunit,receptionist,reason,status,filepath);
        RezvtionpublicDao rezvtionpublicDao = new RezvtionpublicDao();
        try{
            rezvtionpublicDao.addRezvtion(reservationpub);
        }catch (Exception e){
            e.printStackTrace();
            //message="<li>插入记录错误！</li>"+e;
        }
        HttpSession session = request.getSession();
        synchronized (session){
            session.setAttribute("reservationpub",reservationpub); //不如直接传serid 然后用serid来找记录 传了 这个用于displatPubQRCode
            session.setAttribute("serid",serid);
        }

        RequestDispatcher rd = request.getRequestDispatcher("/Rezvtion/checking.jsp");
        rd.forward(request,response);
        //转到预约成功页面 带上显示码的网址;//显示码网页搜索最近的serid
    }
}
