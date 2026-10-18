package com.cquewjh1.weibo.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BlahDAOJDBCImpl implements BlahDAO{
    private Connection conn;
    private PreparedStatement statement;
    private ResultSet resultSet;

    public BlahDAOJDBCImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Blah> getBlahs(Blah blah) {
        String sql = "select * from t_blah where name = ?";
        List<Blah> blahList = null;
        try {
            statement = conn.prepareStatement(sql);
            statement.setString(1, blah.getUsername());
            resultSet = statement.executeQuery();
            blahList = new ArrayList<>();
            while (resultSet.next()) {
                Blah b = new Blah();
                b.setUsername(resultSet.getString("name"));
                b.setDate(resultSet.getTimestamp("date"));
                b.setTxt(resultSet.getString("txt"));
                blahList.add(b);
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return blahList;
    }

    @Override
    public void addBlah(Blah blah) {
        String sql = "insert into t_blah values(?,?,?)";
        try {
            statement = conn.prepareStatement(sql);
            statement.setString(1, blah.getUsername());
            statement.setTimestamp(2, new java.sql.Timestamp(blah.getDate().getTime()));
            statement.setString(3, blah.getTxt());
            int rows = statement.executeUpdate();
            System.out.println("add rows：" + rows);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteBlah(Blah blah) {
        String sql = "delete from t_blah where date=?";
        try {
            statement = conn.prepareStatement(sql);
            statement.setTimestamp(1, new java.sql.Timestamp(blah.getDate().getTime()));
            int rows = statement.executeUpdate();
            System.out.println("delete rows: " + rows);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
