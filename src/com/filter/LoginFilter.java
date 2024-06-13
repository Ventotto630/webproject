package com.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.*;
import java.io.IOException;

@WebFilter(filterName = "loginFilter" , urlPatterns = {"/Manage/*"})
public class LoginFilter extends HttpFilter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // 初始化代码
    }
    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response,
                         FilterChain chain) throws IOException,ServletException{

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession(false); // 不创建新的session

        // 检查请求的URL是否是登录页面
        String requestURI = httpRequest.getRequestURI();
        boolean isLoginPage = requestURI.endsWith("/login.jsp"); // 假设登录页面是/login.jsp

        if (isLoginPage) {
            // 如果是登录页面，允许直接访问，不执行安全检查
            chain.doFilter(request, response);
        } else {
            //对于非登录页面的请求， 检查用户是否登录
            if (session == null || session.getAttribute("myadmin") == null) {
                // 用户未登录，重定向到登录页面
                httpRequest.setAttribute("message", "您还没有登录，请登录");
                httpResponse.sendRedirect(httpRequest.getContextPath() + "/login.jsp");
            } else {
                // 用户已登录，继续处理请求
                chain.doFilter(request, response);
            }
        }
    }
    @Override
    public void destroy() {
        // 清理操作（如果需要）
    }
}

