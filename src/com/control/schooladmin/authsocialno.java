package com.control.schooladmin;

import com.dao.adminDao;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/auth_social.do")
public class authsocialno extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        adminDao dao=new adminDao();
        String message=null;
        try {
            boolean success=dao.noauthSocialAdmin(request.getParameter("adminid"));
            if(success)
                message="取消授权成功";
            else
                message="取消授权失败";
        } catch (Exception e) {
            e.printStackTrace();
            message="取消授权失败";
        }
        request.getSession().setAttribute("message",message);
        response.sendRedirect("Manage/school/admin_depart/auth.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
