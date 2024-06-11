package com.control.schooladmin;

import com.dao.DepartDao;
import com.model.Department;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/add_depart.do")
public class add_depart extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        Department depart=new Department();
        DepartDao dao=new DepartDao();
        String message=null;
        try{
            depart.setId(request.getParameter("id"));
            depart.setType(request.getParameter("type"));
            depart.setName(request.getParameter("name"));
            boolean success=dao.addDepart(depart);
            if(success){
                message="��ӳɹ���";
            }else{
                message="���ʧ��";
            }
        }catch(Exception e){
            message="�����쳣";
        }
        request.getSession().setAttribute("message",message);
        response.sendRedirect("Manage/school/depart/addDepart.jsp");
    }
}
