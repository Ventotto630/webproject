package com.control.schooladmin;

import com.dao.DepartDao;
import com.model.Department;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="/modifyDepartServlet",value="/modifyDepart.do")
public class modifyDepartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        Department depart=new Department();
        DepartDao dao=new DepartDao();
        String message=null;
        try {
            depart = dao.findById(request.getParameter("id"));
            depart.setType(request.getParameter("type"));
            depart.setName(request.getParameter("name"));
            boolean success=dao.modifyDepart(depart);
            if(success){
                message="修改成功！";
            }else{
                message="修改失败";
            }
        } catch (Exception e) {
            e.printStackTrace();
            message="修改失败";
        }
        request.getSession().setAttribute("message",message);
        response.sendRedirect("Manage/school/home.jsp");
    }
}
