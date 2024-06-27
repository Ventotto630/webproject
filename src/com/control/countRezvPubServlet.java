package com.control;

import com.dao.RezvtionpublicDao;
import com.model.Person;
import com.model.Reservation_public;
import com.utils.SM4;
import org.bouncycastle.util.encoders.Hex;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/count-RezvPub.do")
public class countRezvPubServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("GBK");
        response.setCharacterEncoding("GBK");
        String message;
        String [] months ;
        String [] depart ;
        if(request.getParameterValues("months")==null){
             String [] month={"01","02","03","04","05","06","07","08","09","10","11","12"};
             months=month;
        }
        else {
            String month[] = request.getParameterValues("months");
            months=month;
        }
        if(request.getParameterValues("depart")==null){
            String depart_linshi[]={"教务处","计财处","保卫处","团委"};
            depart=depart_linshi;
        }
        else {String depart_linshi[]=request.getParameterValues("depart");
            depart=depart_linshi;
        }
        int depart_shu= depart.length;
        int months_shu= months.length;
        int cishu [][]=new int[depart_shu][months_shu];
        int people [][]=new int[depart_shu][months_shu];
        for(int i=0;i<depart_shu;i++){
            for(int j=0;j<months_shu;j++){
                try{
                    RezvtionpublicDao dao = new RezvtionpublicDao();
                    cishu[i][j]=dao.CountRezv("2024-"+months[j],depart[i]);
                    people[i][j]=cishu[i][j]+dao.CountRezv("2024-"+months[j],depart[i]);
                }catch (Exception e1){
                    e1.printStackTrace();
                    message="统计失败";
                    request.getSession().setAttribute("message",message);
                    response.sendRedirect("Manage/school/rezv_public/countRezvtionPub.jsp");
                }
            }
        }
        request.getSession().setAttribute("months",months);
        request.getSession().setAttribute("depart",depart);
        request.getSession().setAttribute("cishu",cishu);
        request.getSession().setAttribute("people",people);
        response.sendRedirect("Manage/school/rezv_public/countRezvtionPub.jsp");

    }
}
