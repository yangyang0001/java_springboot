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
        Config config = new Config();
        config.useSingleServer().setAddress("redis://192.168.188.7:6379").setPassword("root");
        config.useSingleServer().setIdleConnectionTimeout(10000);
        config.useSingleServer().setPingTimeout(2000);
        config.useSingleServer().setConnectTimeout(10000);
        config.useSingleServer().setTimeout(3000);
        config.useSingleServer().setRetryAttempts(3);
        config.useSingleServer().setRetryInterval(1500);
        config.useSingleServer().setReconnectionTimeout(3000);  // failedSlaveReconnectionInterval
        config.useSingleServer().setFailedAttempts(3);          // failedSlaveCheckInterval
        config.useSingleServer().setSubscriptionsPerConnection(5);
        config.useSingleServer().setSubscriptionConnectionMinimumIdleSize(1);
        config.useSingleServer().setSubscriptionConnectionPoolSize(50);
        config.useSingleServer().setConnectionMinimumIdleSize(32);
        config.useSingleServer().setConnectionPoolSize(500);
        RedissonClient client = Redisson.create(config);
        return client;

//        config.useClusterServers().addNodeAddress("redis://192.168.188.7:6379").setPassword("root");
//        config.useClusterServers().setIdleConnectionTimeout(10000);
//        config.useClusterServers().setPingTimeout(2000);
//        config.useClusterServers().setConnectTimeout(10000);
//        config.useClusterServers().setTimeout(3000);
//        config.useClusterServers().setRetryAttempts(3);
//        config.useClusterServers().setRetryInterval(1500);
//        config.useClusterServers().setReconnectionTimeout(3000);
//        config.useClusterServers().setFailedAttempts(3);
//        config.useClusterServers().setSubscriptionsPerConnection(5);
//        config.useClusterServers().setSubscriptionConnectionMinimumIdleSize(1);
//        config.useClusterServers().setSubscriptionConnectionPoolSize(50);
//        config.useClusterServers().setMasterConnectionMinimumIdleSize(32);
//        config.useClusterServers().setMasterConnectionPoolSize(500);
//        RedissonClient client = Redisson.create(config);
//        return client;
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
