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
        int currentPage = 1; // 默认值
        int pageSize = 8; // 默认值
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
                    pageSize = 8; // 如果输入不合法，使用默认值
                }
            } catch (NumberFormatException e) {
                // 处理解析异常，可能记录日志或返回错误响应给客户端
                pageSize = 8; // 使用默认值
            }
        }
        request.setCharacterEncoding("utf-8");
        DepartDao dao=new DepartDao();
        ArrayList<Department> departList=new ArrayList<>();
        try {
            departList=dao.findAllDepart(currentPage, pageSize);
            // 计算总记录数和总页数...
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
