package com.deepblue.inaction_01_springboot2.chapter_12.config;

import org.redisson.Redisson;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 */
@Configuration
public class RedissonConfig {

    @Bean
    public RedissonClient redissonClient(){

        // 单机版 RedissionConfig
//        Config config = new Config();
//        config.useSingleServer().setAddress("redis://192.168.188.65:6379")
//                .setPassword("root");
//        config.useSingleServer().setIdleConnectionTimeout(10000);
//        config.useSingleServer().setConnectTimeout(10000);
//        config.useSingleServer().setTimeout(3000);
//        config.useSingleServer().setRetryAttempts(3);
//        config.useSingleServer().setRetryInterval(1500);
//        config.useSingleServer().setSubscriptionsPerConnection(5);
//        config.useSingleServer().setSubscriptionConnectionMinimumIdleSize(1);
//        config.useSingleServer().setSubscriptionConnectionPoolSize(50);
//        config.useSingleServer().setConnectionMinimumIdleSize(32);
//        config.useSingleServer().setConnectionPoolSize(500);
//        RedissonClient client = Redisson.create(config);
//        return client;

        // 主从集群+哨兵机制, 这个非常重要, 是分布式和集群实现的基石!
//        Config config = new Config();
//        config.useSentinelServers().setMasterName("mymaster").addSentinelAddress(
//                "redis://192.168.188.8:26379",
//                "redis://192.168.188.8:26479",
//                "redis://192.168.188.8:26579")
//                .setPassword("root");
//        RedissonClient client = Redisson.create(config);
//        return client;

        /**
         * TODO 源码中提供的例子是错的, 按照下面的方式配置! RedisCluster 集群配置方式, 以后只是用这种方式!
         */
        Config config = new Config();
        config.useClusterServers().addNodeAddress(
                "redis://192.168.188.83:6179",
                "redis://192.168.188.83:6279",
                "redis://192.168.188.83:6379",
                "redis://192.168.188.83:6479",
                "redis://192.168.188.83:6579",
                "redis://192.168.188.83:6679")
                .setPassword("root");
        config.useClusterServers().setIdleConnectionTimeout(10000);
        config.useClusterServers().setConnectTimeout(10000);
        config.useClusterServers().setTimeout(3000);
        config.useClusterServers().setRetryAttempts(3);
        config.useClusterServers().setRetryInterval(1500);
        config.useClusterServers().setSubscriptionsPerConnection(5);
        config.useClusterServers().setSubscriptionConnectionMinimumIdleSize(1);
        config.useClusterServers().setSubscriptionConnectionPoolSize(50);
        config.useClusterServers().setMasterConnectionMinimumIdleSize(32);
        config.useClusterServers().setMasterConnectionPoolSize(500);
        RedissonClient client = Redisson.create(config);
        return client;
    }

    @Bean
    public RBloomFilter bloomFilter() {
        // 使用 bloom filter 中的 key 为 mine-bloom-filter;
        RBloomFilter<Object> bloomFilter = redissonClient().getBloomFilter("mine-bloom-filter");
        // 当前 bloom filter 的预期插入量 和 容错率!
        bloomFilter.tryInit(100000L, 0.03D);

        return bloomFilter;
    }

}
