package com.control.systemadmin;

import com.dao.adminDao;
import com.model.Administrators;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/findAllAdmin.do")
public class findAllAdmin extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        int currentPage = 1; // Ĭ��ֵ
        int pageSize = 8; // Ĭ��ֵ
        int totalCount=0;

        String currentPageStr = request.getParameter("currentPage");
        if (currentPageStr != null && !currentPageStr.isEmpty()) {
            try {
                currentPage = Integer.parseInt(currentPageStr);
                if (currentPage < 1) { // ͨ��ҳ�벻Ӧ��С��1�����Ը�����Ҫ�������߼�
                    currentPage = 1; // ������벻�Ϸ���ʹ��Ĭ��ֵ
                }
            } catch (NumberFormatException e) {
                // ��������쳣�����ܼ�¼��־�򷵻ش�����Ӧ���ͻ���
                currentPage = 1; // ʹ��Ĭ��ֵ
            }
        }

        String pageSizeStr = request.getParameter("pageSize");
        if (pageSizeStr != null && !pageSizeStr.isEmpty()) {
            try {
                pageSize = Integer.parseInt(pageSizeStr);
                if (pageSize < 1) { // ÿҳ��Сͨ��Ҳ��Ӧ��С��1�����Ը�����Ҫ�������߼�
                    pageSize = 8; // ������벻�Ϸ���ʹ��Ĭ��ֵ
                }
            } catch (NumberFormatException e) {
                // ��������쳣�����ܼ�¼��־�򷵻ش�����Ӧ���ͻ���
                pageSize = 8; // ʹ��Ĭ��ֵ
            }
        }


        adminDao dao=new adminDao();
        ArrayList<Administrators> adminList=new ArrayList<>();
        String message=null;
        try {
            adminList=dao.findAllAdmin(currentPage, pageSize);
            // �����ܼ�¼������ҳ��...
            totalCount=dao.findAllNumber();
        } catch (Exception e) {
            e.printStackTrace();
            message="����ʧ��";
            response.sendRedirect("Manage/system/findAdmin.jsp");
        }
        int totalPages = (int) Math.ceil((double) totalCount / pageSize);
        request.getSession().setAttribute("name",request.getParameter("name"));
        request.getSession().setAttribute("adminList",adminList);
        request.getSession().setAttribute("currentPage", currentPage);
        request.getSession().setAttribute("pageSize", pageSize);
        request.getSession().setAttribute("totalCount", totalCount);
        request.getSession().setAttribute("totalPages", totalPages);
        request.getSession().setAttribute("message",message);
        response.sendRedirect("Manage/system/displayAllAdmin.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
