package com.control.schooladmin;

import com.dao.adminDao;
import com.model.Administrators;
import com.utils.CryptoSM3;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.time.LocalDate;

@WebServlet(name="/registerServlet",value="/addDAdmin.do")
public class addAdminServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LocalDate date = LocalDate.now(); // get the current date
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        adminDao dao=new adminDao();
        Administrators admin=new Administrators();
        String password=request.getParameter("password");
        byte[] sm3 = CryptoSM3.hash(password.getBytes());
        String sm= CryptoSM3.bytesToHexString(sm3);
        admin.setName(request.getParameter("name"));
        admin.setUsername(request.getParameter("username"));
        admin.setPassword(sm);
        admin.setDepartmentID(request.getParameter("depart"));
        admin.setPhone(request.getParameter("phone"));
        admin.setRole(request.getParameter("role"));
        admin.setSocial(request.getParameter("social"));
        admin.setPub(request.getParameter("pub"));
        admin.setPtime(String.valueOf(date));
        String message=null;
        try{
            boolean sucess=dao.addAdmin(admin);
            if(sucess){
                message="ע��ɹ�";
            }
            else{
                message="ע��ʧ��";
            }
        }catch(Exception e){
            message="ע��ʧ��";
        }
        request.getSession().setAttribute("message", message);
        response.sendRedirect("Manage/school/admin_depart/addAdmin.jsp");
    }
}
