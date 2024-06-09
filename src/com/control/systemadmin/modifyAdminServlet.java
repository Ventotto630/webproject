package com.control.systemadmin;

import com.dao.adminDao;
import com.model.Administrators;
import com.utils.CryptoSM3;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/modifyAdmin.do")
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
        String password=request.getParameter("password");
        byte[] sm3 = CryptoSM3.hash(password.getBytes());
        String sm= CryptoSM3.bytesToHexString(sm3);
        try {
            admin =dao.findById(request.getParameter("adminid"));
            admin.setName(request.getParameter("name"));
            admin.setUsername(request.getParameter("username"));
            admin.setPassword(sm);
            admin.setDepartmentID(request.getParameter("departmentid"));
            admin.setPhone(request.getParameter("phone"));
            admin.setRole(request.getParameter("role"));
            boolean success=dao.modifyAdmin(admin);
            if(success){
                message="修改成功！";
            }else{
                message="修改失败!";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.getSession().setAttribute("message",message);
        response.sendRedirect("Manage/system/home.jsp");
    }
}
