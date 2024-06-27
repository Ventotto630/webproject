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
        int currentPage = 1; // 默认值
        int pageSize = 10; // 默认值
        int totalCount=0;

        String currentPageStr = request.getParameter("currentPage");
        if (currentPageStr != null && !currentPageStr.isEmpty()) {
            try {
                currentPage = Integer.parseInt(currentPageStr);
                if (currentPage < 1) { // 通常页码不应该小于1，可以根据需要调整此逻辑
                    currentPage = 1; // 如果输入不合法，使用默认值
                }
            } catch (NumberFormatException e) {
                // 处理解析异常，可能记录日志或返回错误响应给客户端
                currentPage = 1; // 使用默认值
            }
        }

        String pageSizeStr = request.getParameter("pageSize");
        if (pageSizeStr != null && !pageSizeStr.isEmpty()) {
            try {
                pageSize = Integer.parseInt(pageSizeStr);
                if (pageSize < 1) { // 每页大小通常也不应该小于1，可以根据需要调整此逻辑
                    pageSize = 10; // 如果输入不合法，使用默认值
                }
            } catch (NumberFormatException e) {
                // 处理解析异常，可能记录日志或返回错误响应给客户端
                pageSize = 10; // 使用默认值
            }
        }
        LogDao dao=new LogDao();
        String message;
        try{
            ArrayList<Auditlog> loglist=dao.findAlllogs(currentPage, pageSize);
            // 计算总记录数和总页数...
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
                message="没有相关查询记录";
                request.getSession().setAttribute("message",message);
                response.sendRedirect("Manage/audit/audit.jsp");
            }
        }catch (Exception e){
            e.printStackTrace();
            message="查询失败";
            request.getSession().setAttribute("message",message);
            response.sendRedirect("Manage/audit/audit.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
