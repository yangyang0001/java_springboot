package com.deepblue.inaction_01_springboot2.chapter_12.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 *
 */
@Configuration
public class RedisTemplateConfig {

    /**
     * TODO 如果用redis-cell, 一定要把序列化改了, 默认的jdk序列化会导致redis服务挂掉
     *
     * @param connectionFactory
     * @return
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory) {

        RedisTemplate<String, Object> template = new RedisTemplate<>();

        ObjectMapper objectMapper = new ObjectMapper();
        // 指定要序列化的域, field,get和set, 以及修饰符范围, ANY 包括private和public
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        // 指定序列化输入的类型, 类必须是非final修饰的, final修饰的类, 比如String, Integer等会抛出异常
        objectMapper.activateDefaultTyping(LaissezFaireSubTypeValidator.instance, ObjectMapper.DefaultTyping.NON_FINAL);
        // 兼容java8的时间api
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        GenericJackson2JsonRedisSerializer jacksonSerializer = new GenericJackson2JsonRedisSerializer(objectMapper);


        // 设置 key   序列化模式
        template.setKeySerializer(new StringRedisSerializer());
        // 设置 value 序列化模式
        template.setValueSerializer(jacksonSerializer);
        // 设置 hash key   序列化模式
        template.setHashKeySerializer(new StringRedisSerializer());
        // 设置 hash value 序列化模式
        template.setHashValueSerializer(jacksonSerializer);

        template.setConnectionFactory(connectionFactory);
        template.afterPropertiesSet();

        return template;
    }
}
