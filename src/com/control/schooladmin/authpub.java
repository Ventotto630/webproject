package com.control.schooladmin;

import com.dao.adminDao;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/authpub.do")
public class authpub extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        adminDao dao=new adminDao();
        String message=null;
        try {
            boolean success=dao.authPubAdmin(request.getParameter("adminid"));
            if(success)
                message="授权成功";
            else
                message="授权失败";
        } catch (Exception e) {
            e.printStackTrace();
            message="授权失败";
        }
        request.getSession().setAttribute("message",message);
        response.sendRedirect("Manage/school/admin_depart/auth.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
