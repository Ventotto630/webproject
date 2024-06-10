package com.control.systemadmin;

import com.dao.adminDao;
import com.model.Administrators;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/findAdmin.do")
public class findAdminServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        adminDao dao=new adminDao();
        ArrayList<Administrators> adminList=new ArrayList<>();
        try {
            adminList=dao.findAllAdmin();
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.getSession().setAttribute("adminList",adminList);
        response.sendRedirect("Manage/system/home.jsp#findall");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        adminDao dao=new adminDao();
        ArrayList<Administrators> adminList=new ArrayList<>();
        String message=null;
        try{
            adminList=dao.findByFuzzyName(request.getParameter("name"));
        }catch(Exception e){
            message="查找失败";
        }
        request.getSession().setAttribute("adminList",adminList);
        request.getSession().setAttribute("message",message);
        response.sendRedirect("Manage/system/home.jsp#find");
    }
}
