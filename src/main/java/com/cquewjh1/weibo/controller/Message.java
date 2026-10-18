package com.cquewjh1.weibo.controller;

import com.cquewjh1.weibo.model.Blah;
import com.cquewjh1.weibo.model.UserService;

import java.io.*;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/message.do")
public class Message extends HttpServlet {
//    private final String USERS = "E:\\Pycharm--IDEA\\IntelliJ IDEA 2024.1\\Webproject\\User";
//    private final String LOGIN_VIEW = "index.jsp";
    private final String SUCCESS_VIEW = "member.jsp";
//    private final String ERROR_VIEW = "member.jsp";
    
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //    UserService userService = new UserService("E:\\Pycharm--IDEA\\IntelliJ IDEA 2024.1\\Webproject\\User");
        HttpSession session = request.getSession();
        request.setCharacterEncoding("UTF-8");
        String blabla = request.getParameter("blabla");
        String username = (String) request.getSession().getAttribute("login");
        Blah blah = new Blah();
        blah.setUsername(username);
        blah.setDate(new Date());
        blah.setTxt(blabla);
        UserService userService = (UserService)this.getServletContext().getAttribute("userService");
        if (!"".equals(blabla) && blabla.length() <= 140) {
//            userService.addMessage(username, blabla);
            userService.addBlah(blah);
	    }
        List<Blah> blahs = userService.getBlahs(blah);
        request.setAttribute("blahs", blahs);
        request.getRequestDispatcher(SUCCESS_VIEW).forward(request, response);
	}
}
