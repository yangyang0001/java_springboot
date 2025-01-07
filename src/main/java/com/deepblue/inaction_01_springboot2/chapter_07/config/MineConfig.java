package com.deepblue.inaction_01_springboot2.chapter_07.config;

import com.deepblue.inaction_01_springboot2.chapter_05.entity.User;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

/**
 *
 */
@Configuration
@Data
public class MineConfig {

    @Value("${spring.mysql.version}")
    private String version;

    @Bean("mineUser")
    public User user() {
        User user = new User();
        user.setId(1000L);
        user.setName("Yang");
        user.setDepartmentId(1000L);
        user.setCreateTime(new Date());
        return  user;
    }

}
