package com.cquewjh1.weibo.model;

import java.sql.*;

public class AccountDAOJDBCImpl implements AccountDAO{
    private Connection conn;
    private PreparedStatement statement;
    private ResultSet resultSet;

    public AccountDAOJDBCImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public boolean isUserExisted(Account account) {
        return this.getAccount(account) != null;
    }

    @Override
    public void addAccount(Account account) {
        String sql = "insert into t_account values(?,?,?)";
        try{
            statement = conn.prepareStatement(sql);
            statement.setString(1, account.getName());
            statement.setString(2, account.getPassword());
            statement.setString(3, account.getEmail());
            int rows = statement.executeUpdate();
            System.out.println("add rows：" + rows);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public Account getAccount(Account account) {
        String sql = "select * from t_account where name = ? and password = ?";
        try {
            statement = conn.prepareStatement(sql);
            statement.setString(1, account.getName());
            statement.setString(2, account.getPassword());
            resultSet = statement.executeQuery();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }

        Account acct = null;
        while(true){
            try{
                if (!resultSet.next()) break;
            }catch (SQLException e){
                throw new RuntimeException(e);
            }
            acct = new Account();
            try{
                acct.setName(resultSet.getString("name"));
                acct.setPassword(resultSet.getString("password"));
                acct.setEmail(resultSet.getString("email"));
            }catch (SQLException e){
                throw new RuntimeException(e);
            }
        }
        System.out.println(acct);
        return acct;
    }
}
