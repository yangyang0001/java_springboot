package com.deepblue.inaction_01_springboot2.chapter_11.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

/**
 *
 */
@Configuration
public class MongoDBConfig {

    @Bean
    public MongoClient mongoClient() {
//        return MongoClients.create("mongodb://yangyang:123456@192.168.188.82:27017/mydb");
        return MongoClients.create("mongodb://192.168.188.84:30000,192.168.188.85:30000,192.168.188.86:30000/mydb?maxPoolSize=512");
    }

    @Bean
    public MongoTemplate mongoTemplate() {
        return new MongoTemplate(mongoClient(), "mydb");
    }
}


