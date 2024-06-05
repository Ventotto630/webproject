package com.control;

import com.dao.RezvtionpublicDao;
import com.model.Person;
import com.model.Reservation_public;

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
        String phoneNumber = request.getParameter("phoneNumber");
        String serid = request.getParameter("serid");
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