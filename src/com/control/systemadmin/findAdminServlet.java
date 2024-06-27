package com.control.systemadmin;

import com.dao.adminDao;
import com.model.Administrators;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/findAdmin.do")
public class findAdminServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
        adminDao dao=new adminDao();
        ArrayList<Administrators> adminList=new ArrayList<>();
        String message=null;
        try{
            adminList=dao.findByFuzzyName(request.getParameter("name"),currentPage, pageSize);
            // 计算总记录数和总页数...
            totalCount=dao.findByFuzzyNameNumber(request.getParameter("name"));
        }catch(Exception e){
            message="查找失败";
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
        response.sendRedirect("Manage/system/displayAdmin.jsp");
    }
}
