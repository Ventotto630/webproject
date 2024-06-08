package com.control.systemadmin;

import com.dao.DepartDao;
import com.model.Department;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="/modify",value="/modify.do")
public class modify extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        DepartDao dao=new DepartDao();
        Department depart=new Department();
        String message=null;
        try{
            depart=dao.findById(request.getParameter("id"));
        }catch(Exception e){
            message="出现异常";
        }
        request.getSession().setAttribute("name",depart.getName());
        request.getSession().setAttribute("id",depart.getId());
        request.getSession().setAttribute("type",depart.getType());
        request.getSession().setAttribute("message",message);
        response.sendRedirect("Manage/school/depart/modifyDepart.jsp");
    }
}
