package com.control.systemadmin;

import com.dao.adminDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteAdmin.do")
public class deleteAdminServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        adminDao dao=new adminDao();
        String message=null;
        try{
            boolean success=dao.deleteAdmin(request.getParameter("id"));
            if(success){
                message="É¾³ý³É¹¦£¡";
            }else{
                message="É¾³ýÊ§°Ü";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.getSession().setAttribute("message", message);
        response.sendRedirect("Manage/system/home.jsp");
    }
}
