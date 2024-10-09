package com.deepblue.inaction_00_spring.chapter_09_mine.example_001_jdbc;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 */
public class Main {

    public static void main(String[] args) {

        String url = "jdbc:mysql://127.0.0.1:3306/mysql_transaction?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai&autoReconnect=true&useSSL=false";
        String username = "root", password = "Yang199001";

        try {
            Connection connection = DriverManager.getConnection("", "", "");



        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }
}
