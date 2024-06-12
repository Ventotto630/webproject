package com.control.auditadmin;

import com.dao.LogDao;
import com.dao.RezvtionDao;
import com.model.Auditlog;
import com.model.Reservation;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/query-log.do")
public class querylogServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LogDao dao=new LogDao();
        String message;
        try{
            ArrayList<Auditlog> loglist=dao.findAlllogs();
            if(!loglist.isEmpty()){
                request.getSession().setAttribute("loglist",loglist);
                response.sendRedirect("Manage/audit/displaylogs.jsp");
            }
            else {
                message="没有相关查询记录";
                request.getSession().setAttribute("message",message);
                response.sendRedirect("Manage/audit/audit.jsp");
            }
        }catch (Exception e){
            e.printStackTrace();
            message="查询失败";
            request.getSession().setAttribute("message",message);
            response.sendRedirect("Manage/audit/audit.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("GBK");
        response.setCharacterEncoding("GBK");
        String uname = request.getParameter("uname");
        String operation = request.getParameter("operation");
        Auditlog auditlog=new Auditlog();
        auditlog.setUname(uname);
        auditlog.setOperation(operation);
        String message;
        try{
            LogDao dao = new LogDao();
            ArrayList<Auditlog> loglist = dao.find(auditlog);
            if(!loglist.isEmpty()){
                request.getSession().setAttribute("loglist",loglist);
                response.sendRedirect("Manage/audit/displaylogs.jsp");
            }
            else {
                message="没有相关查询记录";
                request.getSession().setAttribute("message",message);
                response.sendRedirect("Manage/audit/audit.jsp");
            }
        }catch (Exception e1){
            e1.printStackTrace();
            message="查询失败";
            request.getSession().setAttribute("message",message);
            response.sendRedirect("Manage/audit/audit.jsp");
        }
    }
}
