package com.control;

import com.dao.RezvtionpublicDao;
import com.model.Reservation_public;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/checkRezvpubServlet")
public class checkRezvpubServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("GBK");
        response.setCharacterEncoding("GBK");
        String sid =(String) request.getParameter("serid");
        String status =(String) request.getParameter("status");
        RezvtionpublicDao dao = new RezvtionpublicDao();
        //Reservation_public reservation_public = new Reservation_public();
        try {
            //reservation_public = dao.findByserid(sid); //根据serid查询有最新的serid的记录;
            if(dao.updateStatus(status,sid)){
                response.sendRedirect("Manage/check.jsp");
            }
            //else response.sendRedirect("Rezvtion/error.jsp"); else 改
        }catch (Exception ne){}

    }
}
