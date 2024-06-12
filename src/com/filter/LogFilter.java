package com.filter;

import com.dao.LogDao;
import com.model.Administrators;
import com.model.Auditlog;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

@WebFilter(filterName = "logFilter" , urlPatterns = {"*.do"})
public class LogFilter extends HttpFilter {
    public void doFilter(HttpServletRequest request, HttpServletResponse response,
                         FilterChain chain) throws IOException,ServletException{

        HttpSession session = request.getSession();
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String uri = httpServletRequest.getRequestURI();
        String operation = determineOperation(uri);
        if (operation != null && !operation.equals("LOGIN")) {
            Administrators myadmin=(Administrators) session.getAttribute("myadmin");
            String uname = myadmin.getUsername();
            LogDao dao = new LogDao();
            Auditlog log = new Auditlog();
            log.setUname(uname);
            log.setOperation(operation);
            log.setDescription("User accessed: " + uri);
            try{
                dao.addLog(log);
            }catch(Exception se) {
                se.printStackTrace();
            }
        }
        else if(operation != null && operation.equals("LOGIN")){
            String  uname = httpServletRequest.getParameter("username");
            LogDao dao = new LogDao();
            Auditlog log = new Auditlog();
            log.setUname(uname);
            log.setOperation(operation);
            log.setDescription("User accessed: " + uri);
            try{
                dao.addLog(log);
            }catch(Exception se) {
                se.printStackTrace();
            }
        }
        chain.doFilter(request, response);

        //设置到过滤器的行为
        //addlog
        //转发？if (operation != null && userId != -1) {
        //            logService.logOperation(userId, operation, "User accessed: " + uri);
        //        }
        //
        //        chain.doFilter(request, response);
    }
    private String determineOperation(String uri) {
        if (uri.contains("/query")) {
            return "QUERYREZV";
        } else if (uri.contains("/login")) {
            return "LOGIN";
        } else if (uri.contains("/count")) {
            return "COUNTREZV";
        } else if (uri.contains("/checkRezv")) {
            return "CHECKREZV";
        } else if (uri.contains("/deleteDAdmin")) {
            return "DELETEDADMIN";
        } else if (uri.contains("/deleteDepart")) {
            return "DELETEDEPART";
        }
        return null;
    }
    public void destroy() {
        // 清理操作（如果需要）
    }
}
