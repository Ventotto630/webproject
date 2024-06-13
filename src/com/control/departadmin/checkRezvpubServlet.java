package com.control.departadmin;

import com.dao.RezvtionpublicDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/checkDRezvpubServlet.do")
public class checkRezvpubServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("GBK");
        response.setCharacterEncoding("GBK");
        String sid =(String) request.getParameter("serid");
        String status =(String) request.getParameter("status");
        RezvtionpublicDao dao = new RezvtionpublicDao();
        String message=null;
        //Reservation_public reservation_public = new Reservation_public();
        try {
            //reservation_public = dao.findByserid(sid); //根据serid查询有最新的serid的记录;
            if(dao.updateStatus(status,sid)){
                //response.sendRedirect("Manage/check.jsp");
                response.sendRedirect("DcheckServlet");
            }
            //else response.sendRedirect("Rezvtion/error.jsp"); else 改
        }catch (Exception ne){
            message="审核出错";
            request.getSession().setAttribute("message",message);
            response.sendRedirect("Manage/depart/home.jsp");
        }

    }
}
