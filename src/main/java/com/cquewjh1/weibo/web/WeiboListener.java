package com.cquewjh1.weibo.web;

import com.cquewjh1.weibo.model.*;
import com.cquewjh1.weibo.model.UserService;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.Connection;
import java.sql.DriverManager;

@WebListener
public class WeiboListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        String url = context.getInitParameter("url");
        String username = context.getInitParameter("username");
        String password = context.getInitParameter("password");
        Connection conn = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, username, password);
            System.out.println("conn:" + conn);
        } catch (Exception e){
            throw new RuntimeException(e);
        }
        AccountDAOJDBCImpl userDAO = new AccountDAOJDBCImpl(conn);
        BlahDAO blahDAO = new BlahDAOJDBCImpl(conn);

        context.setAttribute("userService", new UserService(userDAO, blahDAO));
    }
}
