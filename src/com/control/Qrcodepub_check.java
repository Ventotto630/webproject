package com.control;

import com.dao.RezvtionpublicDao;
import com.model.Reservation_public;
import com.utils.RezvQRCode;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet("/Qrcodepub_check")
public class Qrcodepub_check extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sid =(String) request.getAttribute("serid");
        RezvtionpublicDao dao = new RezvtionpublicDao();
        Reservation_public reservation_public = new Reservation_public();
        try {
            reservation_public = dao.findByserid(sid); //根据serid查询有最新的serid的记录;
        }catch (Exception ne){

        }
        String name = reservation_public.getName();
        String perid =reservation_public.getPerid();
        String phoneNumber = reservation_public.getPhoneNumber();
        String serid = reservation_public.getSerid();
        String applytime = reservation_public.getApplytime();
        String campus =reservation_public.getCampus();
        String intime = reservation_public.getIntime();
        String outtime =reservation_public.getOuttime();
        String unit = reservation_public.getUnit();
        String vehicle = reservation_public.getVehicle();
        String vname = reservation_public.getVname();
        String Fri_name = reservation_public.getFriend().getName();
        String Fri_perid = reservation_public.getFriend().getPerid();
        String Fri_phoneNumber = reservation_public.getFriend().getPhoneNumber();
        String path = reservation_public.getQrcode();
        String visitunit = reservation_public.getVisitunit();
        String receptionist = reservation_public.getReceptionist();
        String reason = reservation_public.getReason();
        String status = reservation_public.getStatus();

        LocalDateTime time1= LocalDateTime.now();
        LocalDateTime time2= LocalDateTime.parse(intime);
        LocalDateTime time3= LocalDateTime.parse(outtime);


        String data = name+'|'+perid+'|'+phoneNumber+'|'+serid+'|'+applytime+'|'+campus+'|'+intime+'|'+outtime+'|'+unit+'|'+vehicle+'|'+vname+'|'+Fri_name+'|'+Fri_perid+'|'+Fri_phoneNumber+'|'+visitunit+'|'+receptionist+'|'+reason+'|'+status;
        RezvQRCode qrCode = new RezvQRCode();
        String filepath_xd;

        int oncolor =0;
        if(status.equals("审核未通过") || time1.isAfter(time3) || time1.isBefore(time2) ){
            oncolor=0xFF808080; //不通过就是灰色
        }
        else if(status.equals("审核通过") && time1.isBefore(time3) && time1.isAfter(time2)){
            oncolor =0xFF800080;
        }

        try{
            filepath_xd =qrCode.generateQRCode(data,path,"PNG",400,oncolor,0xFFFFFFFF);

            HttpSession session =request.getSession();
            synchronized (session){
                session.setAttribute("filepath_xd",filepath_xd);
                session.setAttribute("reservation_public",reservation_public);
            }


            RequestDispatcher rd = request.getRequestDispatcher("/Rezvtion/displayPubQRCode.jsp"); //不改是不是也行 共用一个jsp; 不行 JavaBean不一样
            rd.forward(request,response);
        }catch (Exception e){}
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
