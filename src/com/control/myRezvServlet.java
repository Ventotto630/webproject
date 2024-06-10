package com.control;

import com.dao.RezvtionDao;
import com.model.Person;
import com.model.Reservation;
import com.utils.SM4;
import org.bouncycastle.util.encoders.Hex;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/myRezvServlet")
public class myRezvServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("GBK");
        response.setCharacterEncoding("GBK");
        String name = request.getParameter("name");
        String perid = request.getParameter("perid");
        try {
            // 定义原始数据
            //String plaintext = "Hello, World!";
            byte[] input = perid.getBytes();

            // 生成密钥
            String keyHex = "0123456789ABCDEF0123456789ABCDEF";
            byte[] keyData = Hex.decode(keyHex);
            SecretKey key = new SecretKeySpec(keyData, "SM4");

            // 定义初始向量（IV）
            String ivHex = "00000000000000000000000000000000";
            byte[] ivData = Hex.decode(ivHex);

            // 加密
            SM4 sm4 = new SM4();
            byte[] encrypted = sm4.encrypt(input, key, ivData);
            perid = Hex.toHexString(encrypted);
        }catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }

        String phoneNumber = request.getParameter("phoneNumber");
        try {
            // 定义原始数据
            //String plaintext = "Hello, World!";
            byte[] input = phoneNumber.getBytes();

            // 生成密钥
            String keyHex = "0123456789ABCDEF0123456789ABCDEF";
            byte[] keyData = Hex.decode(keyHex);
            SecretKey key = new SecretKeySpec(keyData, "SM4");

            // 定义初始向量（IV）
            String ivHex = "00000000000000000000000000000000";
            byte[] ivData = Hex.decode(ivHex);

            // 加密
            SM4 sm4 = new SM4();
            byte[] encrypted = sm4.encrypt(input, key, ivData);
            phoneNumber = Hex.toHexString(encrypted);
        }catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }

        try{
            RezvtionDao dao = new RezvtionDao();
            ArrayList<Reservation> rezvlist = dao.findmyRezv(name,perid,phoneNumber);
            if(!rezvlist.isEmpty()){
                request.getSession().setAttribute("rezvlist",rezvlist);
                response.sendRedirect("Rezvtion/displayRezvtion.jsp");
            }
            else response.sendRedirect("Rezvtion/error.jsp");
        }catch (Exception e1){
            e1.printStackTrace();
        }
    }
}
