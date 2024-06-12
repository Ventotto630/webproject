package com.control.schooladmin;

import com.dao.DepartDao;
import com.model.Department;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="/modify",value="/modify2.do")
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
            message="查询失败";
            request.getSession().setAttribute("depart",depart);
            request.getSession().setAttribute("message",message);
            response.sendRedirect("Manage/school/home.jsp#depart_modify");
        }
        request.getSession().setAttribute("depart",depart);
        request.getSession().setAttribute("message",message);
        response.sendRedirect("Manage/school/depart/modify.jsp");
    }
}
