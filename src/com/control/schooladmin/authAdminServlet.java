package com.control.schooladmin;

import com.dao.adminDao;
import com.model.Administrators;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/authAdminServlet")
public class authAdminServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        adminDao dao=new adminDao();
        ArrayList<Administrators> adminList=new ArrayList<>();
        try {
            adminList=dao.findAllDAdmin();
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.getSession().setAttribute("adminList",adminList);
        response.sendRedirect("Manage/school/admin_depart/auth.jsp");
    }
}
