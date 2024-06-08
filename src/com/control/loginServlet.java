package com.control;
import com.dao.adminDao;
import com.model.Administrators;
import com.utils.CryptoSM3;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name="/loginServlet",value="/login.do")
public class loginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("GBK");
        response.setCharacterEncoding("GBK");
        adminDao dao=new adminDao();
        Administrators admin=new Administrators();
        String message="The user name or password is incorrect";
        try{
            admin=dao.findByUsername(request.getParameter("username"));
        }catch(Exception e){
            request.setAttribute("message", message);
            RequestDispatcher dispatcher = request.getRequestDispatcher("Manage/login.jsp");
            dispatcher.forward(request, response);
        }
        String password=request.getParameter("password");
        byte[] sm3 = CryptoSM3.hash(password.getBytes());
        String sm= CryptoSM3.bytesToHexString(sm3);
        if(sm.equals(admin.getPassword())) {
            request.getSession().setAttribute("admin", admin);
            response.sendRedirect("customer/displayCustomer.jsp");
        }
        else{
            request.setAttribute("message", message);
            RequestDispatcher dispatcher = request.getRequestDispatcher("Manage/login.jsp");
            dispatcher.forward(request, response);
        }
    }
}
