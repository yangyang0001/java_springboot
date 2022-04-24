package com.deepblue.inaction_00_spring.chapter_09.example_001;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 */
public class TestJDBC {

    public static void main(String[] args) {

        String url = "jdbc:mysql://127.0.0.1:3306/mysql_transaction?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai&autoReconnect=true&useSSL=false";
        Connection connection = null;

        try {

            connection = DriverManager.getConnection(url, "root", "Yang199001");
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);

            Statement statement = connection.createStatement();
            int rows = statement.executeUpdate("insert into user(name) values('tom')");

            System.out.println("rows is :" + rows);
            connection.commit();

        } catch (Exception e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

    }


}
