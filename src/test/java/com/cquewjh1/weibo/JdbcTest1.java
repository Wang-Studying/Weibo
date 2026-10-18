package com.cquewjh1.weibo;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JdbcTest1 {
    private Connection conn;
    private Statement statement;
    private ResultSet resultSet;

    // 每个测试方法执行前，初始化jdbc，获取与数据库的链接
    @BeforeEach
    public void setUp() throws Exception {
        System.out.println("测试初始化.......");
        // 要连接的数据库URL, “test”为数据库名
        String url = "jdbc:mysql://localhost:3306/test?useSSL=false&serverTimezone=UTC";
        // 连接的数据库时使用的用户名
        String username = "root";
        // 连接的数据库时使用的密码
        String password = "123456";
        // 1.加载驱动：推荐使用这种方式来加载驱动
        Class.forName("com.mysql.cj.jdbc.Driver");
        // 2.获取与数据库的链接
        conn = DriverManager.getConnection(url, username, password);
        System.out.println("conn:" + conn);
    }

    // 每个测试方法执行后释放资源
    @AfterEach
    public void tearDown() throws Exception {
        System.out.println("释放资源......");
        if (resultSet != null) resultSet.close();
        if (statement != null) statement.close();
        if (conn != null) conn.close();
    }

    @Test
    public void test1() throws Exception {
        System.out.println("测试方法1......");
    }

    @Test
    public void testQuery() throws Exception {
        // 3.获取用于向数据库发送sql语句的statement
        statement = conn.createStatement();
        String sql = "select * from employee";
        // 4.向数据库发sql,并获取代表结果集的resultset
        resultSet = statement.executeQuery(sql);
        List<Employee> empList = new ArrayList<>();
        // 取出数据
        while (resultSet.next()) {
//            int id = resultSet.getInt("id");
//            String name = resultSet.getString("name");
//            int age = resultSet.getInt("age");
//            double salary = resultSet.getDouble("salary");
//            System.out.println(id + "--" + name + "--" + age + "--" + salary);
            Employee employee = new Employee();
            int id = resultSet.getInt("id");
            employee.setId(id);
            String name = resultSet.getString("name");
            employee.setName(name);
            int age = resultSet.getInt("age");
            employee.setAge(age);
            double salary = resultSet.getDouble("salary");
            employee.setSalary(salary);
            empList.add(employee);
            System.out.println(employee);
        }
    }

    @Test
    public void test2() throws Exception {
        statement = conn.createStatement();
        String sql = "select * from employee where id = '4'";
        resultSet = statement.executeQuery(sql);
        Employee emp = new Employee();
        while (resultSet.next()) {
            emp.setId(resultSet.getInt("id"));
            emp.setName(resultSet.getString("name"));
            emp.setAge(resultSet.getInt("age"));
            emp.setSalary(resultSet.getDouble("salary"));
        }
        System.out.println(emp);
    }

    // 插入数据
    @Test
    public void test3() throws Exception {
        statement = conn.createStatement();
        String sql = "insert into employee VALUES(5, 'jobs', 25, 4000.00)";
        int rows = statement.executeUpdate(sql);
        System.out.println("更新rows： " + rows);
    }

    // 删除数据
    @Test
    public void test4() throws Exception {
        statement = conn.createStatement();
        String sql = "delete from employee where id='4'";
        int rows = statement.executeUpdate(sql);
        System.out.println("更新rows： " + rows);
    }

    // 更新数据
    @Test
    public void test5() throws Exception {
        statement = conn.createStatement();
        String sql = "update employee set name = '^^jobs' where id='5'";
        int rows = statement.executeUpdate(sql);
        System.out.println("更新rows： " + rows);
    }
}
