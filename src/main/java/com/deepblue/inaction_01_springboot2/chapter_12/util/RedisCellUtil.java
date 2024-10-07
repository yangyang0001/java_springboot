package com.deepblue.inaction_01_springboot2.chapter_12.util;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;

import java.util.Arrays;
import java.util.List;

/**
 *
 */
@Configuration
public class RedisCellUtil {

    /**
     * lua 脚本
     */
    public static final String LUA_SCRIPT = "return redis.call('cl.throttle', KEYS[1], ARGV[1], ARGV[2], ARGV[3], ARGV[4])";

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 测试
     * @param key    键
     * @param max    总容量
     * @param rate   频次
     * @param period 周期
     * @param quantity 无实际意义, 默认采用1
     * @return
     */
    public boolean allow(String key, int max, int rate, long period, int quantity) {
        try {
            DefaultRedisScript<List> script = new DefaultRedisScript<>(LUA_SCRIPT, List.class);
            /**
             * KEYS[1] 需要设置的key值, 可以结合业务需要
             * ARGV[1] 参数是漏斗的大小, 最大爆发
             * ARGV[2] 频次, 结合ARGV[3]一起使用
             * ARGV[3] 周期（秒）, 结合ARGV[2]一起使用; 最后的速率就是ARGV[2]次/ARGV[3]秒
             * ARGV[4] 申请令牌数, 如果省略则默认
             */
            List<Long> result = (List<Long>) redisTemplate.execute(script, Arrays.asList(key), max, rate, period, 1);

            System.out.println("redis cell allow method invoke, key is : " + key + ", result is :" + JSON.toJSONString(result));

            /**
             * 1. 动作是否受限：
             *   0 表示允许该操作。
             *   1 表示该操作受到限制/阻止。
             * 2. 密钥的总限制 (max_burst+1)。这相当于常见的X-RateLimit-Limit HTTP返回头。
             * 3. 密钥的剩余限制。相当于X-RateLimit-Remaining。
             * 4. 用户应重试之前的秒数, -1如果允许操作, 则始终如此。相当于Retry-After。
             * 5. 限制将重置为其最大容量之前的秒数。相当于X-RateLimit-Reset。
             *
             * 这里只关注第一个元素0表示正常, 1表示过载
             */
            return result.get(0) == 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


}

