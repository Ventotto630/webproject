package com.control;

import com.dao.RezvtionpublicDao;
import com.model.Reservation_public;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/checkRezvpubServlet")
public class checkRezvpubServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sid =(String) request.getAttribute("serid");
        String status =(String) request.getAttribute("status");
        RezvtionpublicDao dao = new RezvtionpublicDao();
        //Reservation_public reservation_public = new Reservation_public();
        try {
            //reservation_public = dao.findByserid(sid); //����serid��ѯ�����µ�serid�ļ�¼;
            dao.updateStatus(status,sid); �Ӹ���˳ɹ���
        }catch (Exception ne){}

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
