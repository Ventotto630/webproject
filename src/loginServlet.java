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
            // ��¼ʧ�ܣ����ӵ�¼���Դ���
            int loginAttempts = getLoginAttempts(session);
            loginAttempts++;
            session.setAttribute("loginAttempts", loginAttempts);

            if (loginAttempts >= MAX_LOGIN_ATTEMPTS) {
                // �ﵽ���ʧ�ܴ����������˻�
                lockUser(session);
            }
            message="�û���������";
            request.getSession().setAttribute("message", message);
            response.sendRedirect("Manage/login.jsp");
        }
        // ����û��Ƿ��ѱ�����
        if (isUserLocked(session)) {
            message="�˻��ѱ���������30���Ӻ�����";
            request.getSession().setAttribute("message", message);
            response.sendRedirect("Manage/login.jsp");
        }else {
            // û�б���������֤�û���������
            String password = request.getParameter("password");
            byte[] sm3 = CryptoSM3.hash(password.getBytes());
            String sm = CryptoSM3.bytesToHexString(sm3);

            //�û���������ȷ
            if (sm.equals(admin.getPassword())) {
                // ��¼�ɹ������õ�¼���Դ���
                resetLoginAttempts(session);

                request.getSession().setAttribute("myadmin", admin);
                //���ж�һ�������Ƿ񳬹�90��
                LocalDate nowdate = LocalDate.now(); // get the current date
                String ptime = admin.getPtime();
                //��ptime������LocalDate����
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate date = LocalDate.parse(ptime, formatter);
                if (Math.abs(ChronoUnit.DAYS.between(nowdate, date)) > 90) {
                    message = "90�����������һ������";
                    request.getSession().setAttribute("message", message);
                    response.sendRedirect("Manage/passwordchange.jsp");
                } else {
                    //û����90��ֱ�ӽ���
                    message = "��ӭ��¼У԰ͨ����ԤԼ����ϵͳ";
                    request.getSession().setAttribute("message", message);
                    String role = admin.getRole();
                    if (Objects.equals(role, "ϵͳ����Ա")) {
                        response.sendRedirect("Manage/system/home.jsp");
                    } else if (Objects.equals(role, "ѧУ����Ա")) {
                        response.sendRedirect("Manage/school/home.jsp");
                    } else if (role.equals("���Ź���Ա")) {
                        response.sendRedirect("Manage/depart/home.jsp");
                    } else {
                        response.sendRedirect("Manage/audit/home.jsp");
                    }
                }
            } else {
                // ��¼ʧ�ܣ����ӵ�¼���Դ���
                int loginAttempts = getLoginAttempts(session);
                loginAttempts++;
                session.setAttribute("loginAttempts", loginAttempts);

                if (loginAttempts >= MAX_LOGIN_ATTEMPTS) {
                    // �ﵽ���ʧ�ܴ����������˻�
                    lockUser(session);
                }
                message = "�û��������벻��ȷ";
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
        // ��������ʱ���ms
        session.setAttribute("lockTimestamp", new Date(System.currentTimeMillis() + LOCK_TIME_MINUTES * 60 * 1000));
    }

    private boolean isUserLocked(HttpSession session) {
        Date lockTimestamp = (Date) session.getAttribute("lockTimestamp");
        if (lockTimestamp == null) {
            return false; // û������ʱ�����˵��û�б�����
        }
        Date now = new Date();
        return now.before(lockTimestamp); // �����ǰʱ��С������ʱ�����˵���Դ�������״̬
    }
}
