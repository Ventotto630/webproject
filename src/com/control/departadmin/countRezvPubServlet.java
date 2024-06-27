package com.control.departadmin;

import com.dao.DepartDao;
import com.dao.RezvtionpublicDao;
import com.model.Administrators;
import com.model.Department;
import com.model.Person;
import com.model.Reservation_public;
import com.utils.SM4;
import org.bouncycastle.util.encoders.Hex;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/count-DRezvPub.do")
public class countRezvPubServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("GBK");
        response.setCharacterEncoding("GBK");
        String message;
        HttpSession session=request.getSession();
        Administrators myadmin=(Administrators) session.getAttribute("myadmin");
        //由于管理员只有id字段
        String visitunit=null;
        try { DepartDao dapartdao=new DepartDao();
            Department depart= dapartdao.findById(myadmin.getDepartmentID());
            visitunit= depart.getName();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String[] s= {"01","02","03","04","05","06","07","08","09","10","11","12"}; //月份
        int[] cishu=new int[12];
        int[] people=new int[12];
        for(int i=0;i<12;i++){
            try{
                RezvtionpublicDao dao = new RezvtionpublicDao();
                cishu[i]=dao.CountRezv("2024-"+s[i],visitunit);
                people[i]=cishu[i]+dao.CountPeople("2024-"+s[i],visitunit);
            }catch (Exception e1){
                e1.printStackTrace();
                message="统计失败";
                request.getSession().setAttribute("message",message);
                response.sendRedirect("Manage/school/rezv_public/countRezvtionPub.jsp");
            }
        }
        request.getSession().setAttribute("s",s);
        request.getSession().setAttribute("cishu",cishu);
        request.getSession().setAttribute("people",people);
        response.sendRedirect("Manage/school/rezv_public/displaycountRezvtionPub.jsp");



    }
}
