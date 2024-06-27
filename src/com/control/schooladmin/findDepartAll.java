package com.control.schooladmin;

import com.dao.DepartDao;
import com.model.Department;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/findAllDepart.do")
public class findDepartAll extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
        request.setCharacterEncoding("utf-8");
        DepartDao dao=new DepartDao();
        ArrayList<Department> departList=new ArrayList<>();
        try {
            departList=dao.findAllDepart(currentPage, pageSize);
            // �����ܼ�¼������ҳ��...
            totalCount=dao.findAllDepartNumber();
        } catch (Exception e) {
            e.printStackTrace();
        }
        int totalPages = (int) Math.ceil((double) totalCount / pageSize);
        request.getSession().setAttribute("currentPage", currentPage);
        request.getSession().setAttribute("pageSize", pageSize);
        request.getSession().setAttribute("totalCount", totalCount);
        request.getSession().setAttribute("totalPages", totalPages);
        request.getSession().setAttribute("departList",departList);
        response.sendRedirect("Manage/school/depart/displayAllDepart.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
