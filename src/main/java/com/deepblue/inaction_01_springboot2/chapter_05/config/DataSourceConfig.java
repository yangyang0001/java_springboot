package com.deepblue.inaction_01_springboot2.chapter_05.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;

/**
 *
 */
@Configuration
public class DataSourceConfig {

    @Bean
    public DataSource dataSource(Environment env) {

        HikariDataSource dataSource = new HikariDataSource();

        dataSource.setJdbcUrl(env.getProperty("spring.datasource.mine.url"));
        dataSource.setUsername(env.getProperty("spring.datasource.mine.username"));
        dataSource.setPassword(env.getProperty("spring.datasource.mine.password"));
        dataSource.setDriverClassName(env.getProperty("spring.datasource.mine.driver-class-name"));

        return dataSource;
    }
}
