package com.cquewjh1.weibo.controller;

import com.cquewjh1.weibo.model.Blah;
import com.cquewjh1.weibo.model.UserService;

import java.io.*;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/delete.do")
public class Delete extends HttpServlet {

    //private final String USERS = "E:\\Pycharm--IDEA\\IntelliJ IDEA 2024.1\\Webproject\\User";
    private final String LOGIN_VIEW = "index.jsp";
    private final String SUCCESS_VIEW = "member.jsp";

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = (String) request.getSession().getAttribute("login");
        String message = request.getParameter("message");
//        UserService userService = new UserService("E:\\Pycharm--IDEA\\IntelliJ IDEA 2024.1\\Webproject\\User");
        UserService userService = (UserService)this.getServletContext().getAttribute("userService");
        Blah blah = new Blah();
        blah.setUsername(username);
        long time = Long.parseLong(message);
        blah.setDate(new Date(time));
        userService.deleteBlah(blah);
        List<Blah> blahs = userService.getBlahs(blah);
        request.setAttribute("blahs", blahs);
        request.getRequestDispatcher(SUCCESS_VIEW).forward(request, response);
	}
}
