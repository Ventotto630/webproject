package com.control.admin_rezv;

import com.dao.RezvtionDao;
import com.model.Person;
import com.model.Reservation;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/count-Rezv.do")
public class countRezvServlet extends HttpServlet {
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

        Person friend = new Person();
        friend.setName(Fri_name);
        friend.setPerid(Fri_perid);
        friend.setPhoneNumber(Fri_phoneNumber);

        Reservation reservation = new Reservation(name,perid,phoneNumber,serid,applytime,campus,intime,outtime,unit,vehicle,vname,friend,"null");

        try{
            RezvtionDao dao = new RezvtionDao();
            int cishu = 0;
            int people = 0;
            cishu = dao.CountRezv(reservation);
            people = dao.CountPeople(reservation);
            people+=cishu;

            request.getSession().setAttribute("cishu",cishu);
            request.getSession().setAttribute("people",people);
            response.sendRedirect("Manage/school/rezvtion/displaycountRezvtion.jsp");

        }catch (Exception e1){
            e1.printStackTrace();
            String message="ͳ��ʧ��";
            request.getSession().setAttribute("message",message);
            response.sendRedirect("Manage/school/rezvtion/countRezvtion.jsp");
        }
    }
}
