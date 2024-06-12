package com.control.schooladmin;

import com.dao.DepartDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="/deleteDepartServlet",value="/deleteDepart.do")
public class deleteDepartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        DepartDao dao=new DepartDao();
        String message=null;
        try{
            boolean success=dao.deleteDepart(request.getParameter("id"));
            if(success){
                message="É¾³ý³É¹¦£¡";
            }else{
                message="É¾³ýÊ§°Ü!";
            }
        } catch (Exception e) {
            e.printStackTrace();
            message="É¾³ýÊ§°Ü!";
        }
        request.getSession().setAttribute("message",message);
        response.sendRedirect("Manage/school/depart/deleteDepart.jsp");
    }
}
