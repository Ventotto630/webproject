package com.control.auditadmin;

import com.dao.LogDao;
import com.model.Auditlog;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/query-alllog.do")
public class queryalllogServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int currentPage = 1; // Ĭ��ֵ
        int pageSize = 10; // Ĭ��ֵ
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
                    pageSize = 10; // ������벻�Ϸ���ʹ��Ĭ��ֵ
                }
            } catch (NumberFormatException e) {
                // ��������쳣�����ܼ�¼��־�򷵻ش�����Ӧ���ͻ���
                pageSize = 10; // ʹ��Ĭ��ֵ
            }
        }
        LogDao dao=new LogDao();
        String message;
        try{
            ArrayList<Auditlog> loglist=dao.findAlllogs(currentPage, pageSize);
            // �����ܼ�¼������ҳ��...
            totalCount=dao.findAlllogsNumber();
            if(!loglist.isEmpty()){
                int totalPages = (int) Math.ceil((double) totalCount / pageSize);
                request.getSession().setAttribute("currentPage", currentPage);
                request.getSession().setAttribute("pageSize", pageSize);
                request.getSession().setAttribute("totalCount", totalCount);
                request.getSession().setAttribute("totalPages", totalPages);
                request.getSession().setAttribute("loglist",loglist);
                response.sendRedirect("Manage/audit/displayalllogs.jsp");
            }
            else {
                message="û����ز�ѯ��¼";
                request.getSession().setAttribute("message",message);
                response.sendRedirect("Manage/audit/audit.jsp");
            }
        }catch (Exception e){
            e.printStackTrace();
            message="��ѯʧ��";
            request.getSession().setAttribute("message",message);
            response.sendRedirect("Manage/audit/audit.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
