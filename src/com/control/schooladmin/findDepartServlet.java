package com.control.schooladmin;

import com.dao.DepartDao;
import com.model.Department;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name="/findDepartServlet",value="/findDepart.do")
public class findDepartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        DepartDao dao=new DepartDao();
        ArrayList<Department> departList=new ArrayList<>();
        try {
            departList=dao.findAllDepart();
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.getSession().setAttribute("departList",departList);
        response.sendRedirect("Manage/school/depart/displayDepart.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        DepartDao dao=new DepartDao();
        ArrayList<Department> departList=new ArrayList<>();
        String message=null;
        try{
            departList=dao.findByFuzzyName(request.getParameter("name"));
        }catch(Exception e){
            message="出现异常";
        }
        request.getSession().setAttribute("message",message);
        request.getSession().setAttribute("departList",departList);
        response.sendRedirect("Manage/school/depart/displayDepart.jsp");
    }
}
