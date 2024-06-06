package com.control;

import com.dao.RezvtionpublicDao;
import com.model.Reservation_public;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/checkServlet")
public class checkServlet extends HttpServlet { //显示所有可审核的记录
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RezvtionpublicDao dao =new RezvtionpublicDao();
        try{
            ArrayList<Reservation_public> rezvlist=dao.findBystatus();
            if(!rezvlist.isEmpty()){
                request.getSession().setAttribute("rezvlist",rezvlist);
                response.sendRedirect("Manage/check.jsp");
            }
            else response.sendRedirect("Manage/checkdone.jsp");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
