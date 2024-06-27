package com.control.admin_rezv;

import com.dao.RezvtionDao;
import com.model.Person;
import com.model.Reservation;
import com.utils.SM4;
import org.bouncycastle.util.encoders.Hex;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/count-Rezv.do")
public class countRezvServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("GBK");
        response.setCharacterEncoding("GBK");
        String message;
        String[] s= {"01","02","03","04","05","06","07","08","09","10","11","12"}; //月份
        int[] cishu=new int[12];
        int[] people=new int[12];
        for(int i=0;i<12;i++){
            try{
                RezvtionDao dao = new RezvtionDao();
                cishu[i]=dao.CountRezv("2024-"+s[i]);
                people[i]=cishu[i]+dao.CountPeople("2024-"+s[i]);
            }catch (Exception e1){
                e1.printStackTrace();
                message="统计失败";
                request.getSession().setAttribute("message",message);
                response.sendRedirect("Manage/school/rezvtion/countRezvtion.jsp");
            }
        }
        request.getSession().setAttribute("month",s);
        request.getSession().setAttribute("cishu",cishu);
        request.getSession().setAttribute("people",people);
        response.sendRedirect("Manage/school/rezvtion/countRezvtion.jsp");

    }
}
