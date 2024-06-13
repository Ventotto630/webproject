package com.control.departadmin;

import com.dao.DepartDao;
import com.dao.RezvtionpublicDao;
import com.model.Administrators;
import com.model.Department;
import com.model.Reservation_public;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/DcheckServlet")
public class checkServlet extends HttpServlet { //显示所有可审核的记录
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RezvtionpublicDao dao =new RezvtionpublicDao();
        HttpSession session=request.getSession();
        Administrators myadmin=(Administrators) session.getAttribute("myadmin");
        //由于管理员只有id字段
        String visitunit=null;
        try { DepartDao dapartdao=new DepartDao();
            Department depart= dapartdao.findById(myadmin.getDepartmentID());
            visitunit= depart.getName();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try{
            ArrayList<Reservation_public> rezvlist=dao.findDBystatus(visitunit);
            if(!rezvlist.isEmpty()){
                request.getSession().setAttribute("rezvlist",rezvlist);
                response.sendRedirect("Manage/depart/check.jsp");
            }
            else response.sendRedirect("Manage/school/rezv_public/checkdone.jsp");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
