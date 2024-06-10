package com.control.systemadmin;

import com.dao.adminDao;
import com.model.Administrators;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/modify.do")
public class modifyServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        adminDao dao=new adminDao();
        Administrators admin=new Administrators();
        String message=null;
        try{
            admin=dao.findById(request.getParameter("id"));
        }catch(Exception e){
            message="出现异常";
        }
        request.getSession().setAttribute("message",message);
        request.getSession().setAttribute("admin",admin);
        response.sendRedirect("Manage/system/home.jsp#modify");
    }
}
