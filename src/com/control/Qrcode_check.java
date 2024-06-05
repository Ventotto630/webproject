package com.control;

import com.dao.RezvtionDao;
import com.dao.RezvtionpublicDao;
import com.model.Reservation;
import com.model.Reservation_public;
import com.utils.RezvQRCode;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet("/Qrcode_check")
public class Qrcode_check extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sid =(String) request.getAttribute("serid");
        RezvtionDao dao = new RezvtionDao();
        Reservation reservation = new Reservation();
        try {
            reservation = dao.findByserid(sid); //根据serid查询有最新的serid的记录;
        }catch (Exception ne){

        }
        String name = reservation.getName();
        String perid =reservation.getPerid();
        String phoneNumber = reservation.getPhoneNumber();
        String serid = reservation.getSerid();
        String applytime = reservation.getApplytime();
        String campus =reservation.getCampus();
        String intime = reservation.getIntime();
        String outtime =reservation.getOuttime();
        String unit = reservation.getUnit();
        String vehicle = reservation.getVehicle();
        String vname = reservation.getVname();
        String Fri_name = reservation.getFriend().getName();
        String Fri_perid = reservation.getFriend().getPerid();
        String Fri_phoneNumber = reservation.getFriend().getPhoneNumber();
        String Qrcode = reservation.getQrcode();

        LocalDateTime time1= LocalDateTime.now();
        LocalDateTime time2= LocalDateTime.parse(intime);
        LocalDateTime time3= LocalDateTime.parse(outtime);

        String data = name+'|'+perid+'|'+phoneNumber+'|'+serid+'|'+applytime+'|'+campus+'|'+intime+'|'+outtime+'|'+unit+'|'+vehicle+'|'+vname+'|'+Fri_name+'|'+Fri_perid+'|'+Fri_phoneNumber;
        RezvQRCode qrCode = new RezvQRCode();
        int oncolor = 0;
        if(time1.isAfter(time3) || time1.isBefore(time2)){
            oncolor=0xFF808080;
        }
        else oncolor =0xFF800080;
        String filepath_xd;
        try{
            filepath_xd =qrCode.generateQRCode(data,Qrcode,"PNG",400,oncolor,0xFFFFFFFF);
            HttpSession session =request.getSession();
            synchronized (session){
                session.setAttribute("filepath_xd",filepath_xd);
                session.setAttribute("reservation",reservation);
            }

            RequestDispatcher rd = request.getRequestDispatcher("/Rezvtion/displayQRCode.jsp");
            rd.forward(request,response);
        }catch (Exception e){}
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
