import com.dao.adminDao;
import com.model.Administrators;
import com.utils.CryptoSM3;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Objects;

@WebServlet(name="/loginServlet",value="/login.do")
public class loginServlet extends HttpServlet {
    private static final int MAX_LOGIN_ATTEMPTS = 5;
    private static final int LOCK_TIME_MINUTES = 2;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        adminDao dao=new adminDao();
        Administrators admin=new Administrators();
        String message=null;

        try{
            admin=dao.findByUsername(request.getParameter("username"));
        }catch(Exception e){
            // 登录失败，增加登录尝试次数
            int loginAttempts = getLoginAttempts(session);
            loginAttempts++;
            session.setAttribute("loginAttempts", loginAttempts);

            if (loginAttempts >= MAX_LOGIN_ATTEMPTS) {
                // 达到最大失败次数，锁定账户
                lockUser(session);
            }
            message="用户名不存在";
            request.getSession().setAttribute("message", message);
            response.sendRedirect("Manage/login.jsp");
        }
        // 检查用户是否已被锁定
        if (isUserLocked(session)) {
            message="账户已被锁定，请30分钟后再试";
            request.getSession().setAttribute("message", message);
            response.sendRedirect("Manage/login.jsp");
        }else {
            // 没有被锁定，验证用户名和密码
            String password = request.getParameter("password");
            byte[] sm3 = CryptoSM3.hash(password.getBytes());
            String sm = CryptoSM3.bytesToHexString(sm3);

            //用户名密码正确
            if (sm.equals(admin.getPassword())) {
                // 登录成功，重置登录尝试次数
                resetLoginAttempts(session);

                request.getSession().setAttribute("myadmin", admin);
                //先判断一下密码是否超过90天
                LocalDate nowdate = LocalDate.now(); // get the current date
                String ptime = admin.getPtime();
                //将ptime解析成LocalDate类型
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate date = LocalDate.parse(ptime, formatter);
                if (Math.abs(ChronoUnit.DAYS.between(nowdate, date)) > 90) {
                    message = "90天以上需更换一次密码";
                    request.getSession().setAttribute("message", message);
                    response.sendRedirect("Manage/passwordchange.jsp");
                } else {
                    //没超过90天直接进入
                    message = "欢迎登录校园通行码预约管理系统";
                    request.getSession().setAttribute("message", message);
                    String role = admin.getRole();
                    if (Objects.equals(role, "系统管理员")) {
                        response.sendRedirect("Manage/system/home.jsp");
                    } else if (Objects.equals(role, "学校管理员")) {
                        response.sendRedirect("Manage/school/home.jsp");
                    } else if (role.equals("部门管理员")) {
                        response.sendRedirect("Manage/depart/home.jsp");
                    } else {
                        response.sendRedirect("Manage/audit/home.jsp");
                    }
                }
            } else {
                // 登录失败，增加登录尝试次数
                int loginAttempts = getLoginAttempts(session);
                loginAttempts++;
                session.setAttribute("loginAttempts", loginAttempts);

                if (loginAttempts >= MAX_LOGIN_ATTEMPTS) {
                    // 达到最大失败次数，锁定账户
                    lockUser(session);
                }
                message = "用户名或密码不正确";
                request.getSession().setAttribute("message", message);
                response.sendRedirect("Manage/login.jsp");
            }
        }
    }

    private int getLoginAttempts(HttpSession session) {
        Integer loginAttempts = (Integer) session.getAttribute("loginAttempts");
        return loginAttempts != null ? loginAttempts : 0;
    }

    private void resetLoginAttempts(HttpSession session) {
        session.removeAttribute("loginAttempts");
    }

    private void lockUser(HttpSession session) {
        // 设置锁定时间戳ms
        session.setAttribute("lockTimestamp", new Date(System.currentTimeMillis() + LOCK_TIME_MINUTES * 60 * 1000));
    }

    private boolean isUserLocked(HttpSession session) {
        Date lockTimestamp = (Date) session.getAttribute("lockTimestamp");
        if (lockTimestamp == null) {
            return false; // 没有锁定时间戳，说明没有被锁定
        }
        Date now = new Date();
        return now.before(lockTimestamp); // 如果当前时间小于锁定时间戳，说明仍处于锁定状态
    }
}
