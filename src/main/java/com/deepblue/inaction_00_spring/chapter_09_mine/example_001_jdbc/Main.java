package com.deepblue.inaction_00_spring.chapter_09_mine.example_001_jdbc;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 */
public class Main {

    public static void main(String[] args) {

        SimpleDateFormat defaultSDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String url = "jdbc:mysql://127.0.0.1:3306/mysql_transaction?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai&autoReconnect=true&useSSL=false";
        String username = "root", password = "Yang199001";

        try {
            Connection connection = DriverManager.getConnection(url, username, password);

            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);

            Statement statement = connection.createStatement();
            String sql = "insert into user(name, department_id, create_time) values ('test_jdbc', '1'," +
                    "'" + defaultSDF.format(new Date()) + "')";
            int rows = statement.executeUpdate(sql);

            System.out.println("rows is " + rows);
            connection.commit();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }
}
