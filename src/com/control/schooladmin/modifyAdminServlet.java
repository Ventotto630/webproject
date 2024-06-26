package com.control.schooladmin;

import com.dao.adminDao;
import com.model.Administrators;
import com.utils.CryptoSM3;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="/modifyAdminServlet",value="/modifyDAdmin.do")
public class modifyAdminServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        Administrators admin=new Administrators();
        adminDao dao=new adminDao();
        String message=null;
        try {
            admin =dao.findById(request.getParameter("adminid"));
            admin.setName(request.getParameter("name"));
            admin.setUsername(request.getParameter("username"));
            admin.setDepartmentID(request.getParameter("departmentid"));
            admin.setPhone(request.getParameter("phone"));
            boolean success=dao.modifyAdmin(admin);
            if(success){
                message="�޸ĳɹ���";
            }else{
                message="�޸�ʧ��!";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.getSession().setAttribute("message",message);
        response.sendRedirect("Manage/school/admin_depart/addAdmin.jsp");
    }
}
