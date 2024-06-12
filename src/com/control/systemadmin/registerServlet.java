package com.control.systemadmin;

import com.dao.adminDao;
import com.model.Administrators;
import com.utils.CryptoSM3;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/addAdmin.do")
public class registerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        adminDao dao=new adminDao();
        Administrators admin=new Administrators();
        String password=request.getParameter("password");
        byte[] sm3 = CryptoSM3.hash(password.getBytes());
        String sm= CryptoSM3.bytesToHexString(sm3);
        admin.setAdminID(request.getParameter("adminID"));
        admin.setName(request.getParameter("name"));
        admin.setUsername(request.getParameter("username"));
        admin.setPassword(sm);
        admin.setDepartmentID(request.getParameter("depart"));
        admin.setPhone(request.getParameter("phone"));
        admin.setRole(request.getParameter("role"));
        admin.setSocial(request.getParameter("social"));
        admin.setPub(request.getParameter("pub"));
        String message=null;
        try{
            boolean sucess=dao.addAdmin(admin);
            if(sucess){
                message="×¢²á³É¹¦";
            }
            else{
                message="×¢²áÊ§°Ü";
            }
        }catch(Exception e){
            message="×¢²áÊ§°Ü";
        }
        request.getSession().setAttribute("message", message);
        response.sendRedirect("Manage/system/addAdmin.jsp");
    }
}
