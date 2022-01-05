package com.deepblue.inaction_01_springboot2.chapter_02.datasource;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.jdbc.metadata.HikariDataSourcePoolMetadata;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 *
 */
// @Configuration
public class DataSourceConfig {

    // @Bean
    public DataSource dataSource() {
        DataSource dataSource = DataSourceBuilder.create().build();
        return dataSource;
    }
}
