package com.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.*;
import java.io.IOException;

@WebFilter(filterName = "loginFilter" , urlPatterns = {"/Manage/*"})
public class LoginFilter extends HttpFilter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // ��ʼ������
    }
    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response,
                         FilterChain chain) throws IOException,ServletException{

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession(false); // �������µ�session

        // ��������URL�Ƿ��ǵ�¼ҳ��
        String requestURI = httpRequest.getRequestURI();
        boolean isLoginPage = requestURI.endsWith("/login.jsp"); // �����¼ҳ����/login.jsp

        if (isLoginPage) {
            // ����ǵ�¼ҳ�棬����ֱ�ӷ��ʣ���ִ�а�ȫ���
            chain.doFilter(request, response);
        } else {
            //���ڷǵ�¼ҳ������� ����û��Ƿ��¼
            if (session == null || session.getAttribute("myadmin") == null) {
                // �û�δ��¼���ض��򵽵�¼ҳ��
                httpRequest.setAttribute("message", "����û�е�¼�����¼");
                httpResponse.sendRedirect(httpRequest.getContextPath() + "/login.jsp");
            } else {
                // �û��ѵ�¼��������������
                chain.doFilter(request, response);
            }
        }
    }
    @Override
    public void destroy() {
        // ��������������Ҫ��
    }
}

