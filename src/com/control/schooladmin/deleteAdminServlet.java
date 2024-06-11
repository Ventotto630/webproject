package com.control.schooladmin;

import com.dao.adminDao;
import com.model.Administrators;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="/deleteAdminServlet",value="/deleteDAdmin.do")
public class deleteAdminServlet extends HttpServlet {
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
            if(admin.getRole().equals("部门管理员")) {
                boolean success = dao.deleteAdmin(request.getParameter("id"));
                if (success) {
                    message = "删除成功！";
                } else {
                    message = "删除失败";
                }
            }else{
                message="您没有删除的权限";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.getSession().setAttribute("message", message);
        response.sendRedirect("Manage/school/admin_depart/addAdmin.jsp");
    }
}
