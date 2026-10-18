package com.cquewjh1.weibo.controller;

import com.cquewjh1.weibo.model.UserService;
import com.cquewjh1.weibo.model.Account;

import java.io.*;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/register.do")
public class Register extends HttpServlet {
    private final String SUCCESS_VIEW = "success.jsp";
    private final String ERROR_VIEW = "register.jsp";

    protected void doPost(HttpServletRequest req,
            HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String confirmedPasswd = req.getParameter("confirmedPasswd");

        UserService userService = (UserService)this.getServletContext().getAttribute("userService");
        // 保存错误提示信息
        List<String> errors = new ArrayList<>();
        if (userService.isInvalidEmail(email)) {
            errors.add("未填写邮件或邮件格式不正确");
        }
        if (userService.isInvalidUsername (username)) {
            errors.add("用户名称为空或已存在");
        }
        if (userService.isInvalidPassword(password,confirmedPasswd)) {
            errors.add("请确认密码符合格式并再次确认密码");
        }
        String resultPage = ERROR_VIEW;

        if (!errors.isEmpty()){
            // 校验失败后的处理
            req.setAttribute("errors", errors);
        }else{
            // 校验成功后的处理
            resultPage = SUCCESS_VIEW;
           // userService.createUserData(email,username,password);
            Account account = new Account();
            account.setName(username);
            account.setPassword(password);
            account.setEmail(email);
            userService.add(account);
        }
        req.getRequestDispatcher(resultPage).forward(req,resp);//转发，不能用重定向。因为我们需要向SUCCESS_VIEW传递数据
    }



}
