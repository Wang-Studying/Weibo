//package com.cquewjh1.weibo.view;
//
//import com.cquewjh1.weibo.model.UserService;
//
//import java.text.SimpleDateFormat;
//import java.util.*;
//import java.io.*;
//import java.io.PrintWriter;
//import java.util.Date;
//import java.util.Map;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//@WebServlet("/member.view")
//public class Member extends HttpServlet {
//    //private final String USERS = "E:\\Pycharm--IDEA\\IntelliJ IDEA 2024.1\\Webproject\\User";
//
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
////        if(request.getSession().getAttribute("login") == null) {
////            response.sendRedirect("index.jsp");
////        }
//
//        String username = (String) request.getSession().getAttribute("login");
//
//        response.setContentType("text/html;charset=UTF-8");
//        PrintWriter out = response.getWriter();
//        out.println("<!DOCTYPE html PUBLIC '-//W3C//DTD HTML 4.01 Transitional//EN'>");
//        out.println("<html>");
//        out.println("<head>");
//        out.println("  <meta content='text/html; charset=UTF-8' http-equiv='content-type'>");
//        out.println("<title>Gossip 微网志</title>");
//        out.println("<link rel='stylesheet' href='css/member.css' type='text/css'>");
//        out.println("</head>");
//        out.println("<body>");
//
//        out.println("<div class='leftPanel'>");
//        out.println("<img src='images/14388.jpg' alt='Gossip 微网志' /><br><br>");
//        out.println("<a href='logout.do'>注销"+  username +"</a>");
//        out.println("</div>");
//
//        out.println("<div class='leftPanel'>");
//        out.println("分享新鲜事...<br>");
//        out.println("信息要140字以内<br>");
//        out.println("<form method='post' action='message.do'>");
//        out.println("<textarea rows=\"6\" cols=\"60\" name=blabla></textarea>");
//        out.println("<br>");
//        out.println("<button type='submit'>送出</button>");
//        out.println("</form>");
//        out.println("<p><hr><p>");
//
//
//        UserService userService = (UserService)this.getServletContext().getAttribute("userService");
//        //UserService userService = new UserService("E:\\Pycharm--IDEA\\IntelliJ IDEA 2024.1\\Webproject\\User");
//        Map<Date,String> messages = userService.readMessage(username);
//
//        if (messages.isEmpty()){
//            out.println("暂无信息");
//        }
//        out.println("<table>");
//        for (Date date : messages.keySet()) {
//            out.println("<tr>");
//                String strDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
//                out.println("<td>");
//                    out.println(username + "<br>");
//                    out.println(messages.get(date) + "<br>");
//                    out.println(strDate);
//                    long time = date.getTime();
//                    out.println("<br><a href='delete.do?message=" + time + "'>删除</a>");
//                out.println("<hr></td>");
//            out.println("</tr>");
//        }
//        out.println("<table>");
//        out.println("</div>");
//
//        out.println("</body>");
//        out.println("</html>");
//    }
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        doPost(req, resp);
//    }
//}
//
