package com.control.schooladmin;

import com.dao.adminDao;
import com.model.Administrators;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/Dmodify.do")
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
            message="�����쳣";
            request.getSession().setAttribute("message",message);
            response.sendRedirect("Manage/school/admin_depart/modify.jsp");
        }
        if(admin==null){
            message="����Ա�����ڣ�";
            request.getSession().setAttribute("message",message);
            response.sendRedirect("Manage/school/admin_depart/modify.jsp");
        }
        else{
            //��Ҫ���ж��Ƿ�Ϊ���Ź���Ա�����ܶԹ���Ա��Ϣ�����޸�
            if (admin.getRole().equals("���Ź���Ա")) {
                request.getSession().setAttribute("admin", admin);
                response.sendRedirect("Manage/school/admin_depart/modifyAdmin.jsp");
            } else {
                message = "��û��Ȩ�޶�������޸ģ�";
                request.getSession().setAttribute("message", message);
                response.sendRedirect("Manage/school/admin_depart/modify.jsp");
            }
        }
    }
}
