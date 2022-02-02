package com.deepblue;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;

//此处的配置 取消 MongoDB 的自动注入, 可以配合 MongoDBConfig.java 使用!
//@SpringBootApplication(exclude = {MongoAutoConfiguration.class, MongoDataAutoConfiguration.class})

// 下面配置没有取消 MongoDB 的自动注入, 要使用 application.properties 中 spring.data.mongodb.uri 配合使用!
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
