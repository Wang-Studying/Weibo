package com.cquewjh1.weibo.controller;

import com.cquewjh1.weibo.model.UserService;
import com.cquewjh1.weibo.model.Account;
import com.cquewjh1.weibo.model.Blah;


import java.io.*;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login.do")
public class Login extends HttpServlet {
    private final String SUCCESS_VIEW = "member.jsp";
    private final String ERROR_VIEW = "index.jsp";
	protected void doPost(HttpServletRequest req,
	                      HttpServletResponse resp)
	                        throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        UserService userService = (UserService)this.getServletContext().getAttribute("userService");
        Account account = new Account();
        account.setName(username);
        account.setPassword(password);
        if(userService.checkLogin(account)){
            req.getSession().setAttribute("login", username);
            Blah blah= new Blah();
            blah.setUsername(username);
            List<Blah> blahs = userService.getBlahs(blah); // 该用户的所有发言
            req.setAttribute("blahs", blahs);
            req.getRequestDispatcher(SUCCESS_VIEW).forward(req, resp);
        }else{
            // 用户名、密码错误，“跳转”到ERROR_VIEW = "index.jsp":
            req.setAttribute("error", "用户名或密码错误");
            req.getRequestDispatcher(ERROR_VIEW).forward(req, resp);
        }

	}


}
