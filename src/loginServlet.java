import com.dao.adminDao;
import com.model.Administrators;
import com.utils.CryptoSM3;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Objects;

@WebServlet(name="/loginServlet",value="/login.do")
public class loginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("GBK");
        response.setCharacterEncoding("GBK");
        adminDao dao=new adminDao();
        Administrators admin=new Administrators();
        String message="用户名或密码不正确";
        try{
            admin=dao.findByUsername(request.getParameter("username"));
        }catch(Exception e){
            message="用户名或密码不正确";
            request.getSession().setAttribute("message", message);
            response.sendRedirect("Manage/login.jsp");
        }
        String password=request.getParameter("password");
        byte[] sm3 = CryptoSM3.hash(password.getBytes());
        String sm= CryptoSM3.bytesToHexString(sm3);
        if(sm.equals(admin.getPassword())) {
            message="欢迎登录校园通行码预约管理系统";
            request.getSession().setAttribute("message", message);
            request.getSession().setAttribute("myadmin", admin);
            String role=admin.getRole();
            if (Objects.equals(role, "系统管理员")) {
                response.sendRedirect("Manage/system/home.jsp");
            } else if (Objects.equals(role, "学校管理员")) {
                response.sendRedirect("Manage/school/home.jsp");
            } else if (role.equals("部门管理员")){
                response.sendRedirect("Manage/depart/home.jsp");
            }else{
                response.sendRedirect("Manage/audit/audit.jsp");
            }
        }
        else{
            message="用户名或密码不正确";
            request.getSession().setAttribute("message", message);
            response.sendRedirect("Manage/login.jsp");
        }
    }
}
