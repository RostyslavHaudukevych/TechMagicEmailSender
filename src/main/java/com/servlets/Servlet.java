package com.servlets;

import com.google.gson.Gson;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

public class Servlet extends HttpServlet {
    private String token = "ASDYQW127BFYWEBCAQWUQWNCE38ASDNCNUEO12";

    //    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        resp.getWriter().append("Welcome to my app");
//        EmailSender.testSendEmail();
//    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader body = req.getReader();
        String bodyLines = "";
        String bodyStr = "";
        while ((bodyLines = body.readLine())!=null) {
            bodyStr+=bodyLines;
        }
        System.out.println(bodyStr);


        resp.getWriter().append("Response : "+bodyStr);
        Emails emailsData = new Gson().fromJson(bodyStr, Emails.class);

        if (emailsData.token.equals(token)) {
            EmailSender.sendEmail(emailsData.emails);
        } else {
            resp.getWriter().append("{'message' : 'bad request', 'reason' : 'invalid_token'}");
            resp.setStatus(403);
        }

    }

    class Emails {
        List<EmailJSON> emails;
        String token;
    }

    class EmailJSON {
        public String to;
        public String subject;
        public String html;
    }

}
