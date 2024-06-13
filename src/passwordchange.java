import com.dao.adminDao;
import com.model.Administrators;
import com.utils.CryptoSM3;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/pchange.do")
public class passwordchange extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        Administrators admin=new Administrators();
        adminDao dao=new adminDao();
        String message=null;
        String old=request.getParameter("old");
        byte[] old_sm3 = CryptoSM3.hash(old.getBytes());
        String old_sm= CryptoSM3.bytesToHexString(old_sm3);

        String mynew=request.getParameter("new");
        try {
            String adminid=request.getParameter("adminid");
            admin =dao.findById(adminid);
            //先判断原密码是否正确
            if(admin.getPassword().equals(old_sm)){
                byte[] sm3 = CryptoSM3.hash(mynew.getBytes());
                String sm= CryptoSM3.bytesToHexString(sm3);
                boolean success=dao.changepass(sm,adminid);
                if(success){
                    message="修改成功！请重新登录";
                    request.getSession().setAttribute("message",message);
                    response.sendRedirect("Manage/login.jsp");
                }else{
                    message="修改失败!";
                    request.getSession().setAttribute("message",message);
                    response.sendRedirect("Manage/login.jsp");
                }
            }else{
                message="原密码有误";
                request.getSession().setAttribute("message",message);
                response.sendRedirect("Manage/passwordchange.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();
            message="修改失败!";
            request.getSession().setAttribute("message",message);
            response.sendRedirect("Manage/login.jsp");
        }
    }
}
