package com.deepblue.inaction_01_springboot2.chapter_12.controller;

import com.alibaba.fastjson.JSON;
import com.deepblue.inaction_01_springboot2.chapter_12.util.RedisCellUtil;
import com.deepblue.inaction_01_springboot2.chapter_12.entity.User;
import com.google.common.collect.Lists;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.geo.Point;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 *
 */
@Controller
public class RedisController {

    public static String KEY = "lock_key";

    @Autowired
    private RedissonClient redissonClient;

    @Autowired
    private RBloomFilter<Object> bloomFilter;


    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RedisCellUtil redisCellUtil;


    @ResponseBody
    @RequestMapping("/setUser")
    public String setUser() {

        User user = new User(1000L, "Yang", "Yang199001");

        String json = JSON.toJSONString(user);

        redisTemplate.opsForValue().set(user.getUserId().toString() , json);

        return "success";

    }

    @ResponseBody
    @RequestMapping("/redis_lock")
    public Boolean lock() {
        Boolean lockFlag = true;

        String value = UUID.randomUUID().toString();
        lockFlag = redisTemplate.opsForValue().setIfAbsent(KEY, value, 10, TimeUnit.SECONDS);

        System.out.println("lock method is invoked key is :" + KEY + ", lockFlag is :" + lockFlag);

        if(lockFlag) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 删除锁!
            redisTemplate.delete(KEY);
        }

        return lockFlag;
    }


    /**
     * 基于Redisson的加锁的方式!
     * @param productNo
     * @param killNum
     * @return
     */
    @ResponseBody
    @RequestMapping("/killWithRedisson")
    public Integer killWithRedisson(@RequestParam("productNo") String productNo, @RequestParam("killNum") Integer killNum) {
        RLock lock = redissonClient.getLock(productNo);
        int result = 0;
        try {
            if (lock != null){
                lock.lock();
                //TODO：真正的核心处理逻辑
//                Product product = new Product();
//                product.setProductNo(productNo);
//                product.setKillNum(killNum);
//                result = productService.minusProduct(product);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (lock != null){
                lock.unlock();
            }
        }
        return result;
    }

    @ResponseBody
    @RequestMapping("/redis_setuv")
    public Long setUV() {
        String key = "www.deep.com";

        for(int i = 0; i < 100000; i++) {
            redisTemplate.opsForHyperLogLog().add(key, "user_" + i);
        }

        return redisTemplate.opsForHyperLogLog().size(key);
    }


    /**
     * Redis bloom 过滤器的使用!
     * @return
     */
    @ResponseBody
    @RequestMapping("/bfadd")
    public List<Boolean> bfadd() {
        List<Boolean> flagList = Lists.newArrayList();
        for(int i = 100; i < 200; i++) {
            boolean flag = bloomFilter.add("user_" + i);
            flagList.add(flag);
        }

        return flagList;
    }

    @ResponseBody
    @RequestMapping("/bfexists")
    public Boolean bfexists(String value) {
        return bloomFilter.contains(value);
    }

    @ResponseBody
    @RequestMapping("/bloomcount")
    public Long getBloomCount() {
        return bloomFilter.count();
    }

    @ResponseBody
    @RequestMapping("/bloomname")
    public String getBloomName() {
        return bloomFilter.getName();
    }


    /**
     * 滑动窗口: 设置 1天 之内修改 nickname 最多只能 5次
     */
    @ResponseBody
    @RequestMapping("/set_nickname_zset")
    public Boolean setNickNameWithZSet(String nickname) {
        Integer maxCount = 5;

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Date zero = calendar.getTime();

        String key = "199001_Yang";

        redisTemplate.opsForZSet().removeRangeByScore(key, 0, zero.getTime());
        long count = redisTemplate.opsForZSet().zCard(key);
        System.out.println("set_nickname count is :" + count);

        if(count < maxCount){
            Long score = System.currentTimeMillis();
            redisTemplate.opsForZSet().add(key, String.valueOf(score), Double.valueOf(score));
            return true;
        } else {
            return false;
        }

    }

    /**
     * Redis-Cell 组件的使用, 漏斗限流: 设置 1小时 之内修改 nickname 最多只能 5次
     */
    @ResponseBody
    @RequestMapping("/set_nickname_funnel")
    public Boolean setNickNameWithFunnel(String nickname) {
        int max  = 5;
        int rate = 5;
        long period = 3600L;

        boolean allow = redisCellUtil.allow(nickname, max, rate, period, 1);
        return allow;
    }

    /**
     * GeoHash 的使用, 其实是变种的 zset 的使用
     */
    @ResponseBody
    @RequestMapping("/geo_hash_add")
    public String geoHash(double longitude, double latitude, String member) {
        String key = "company";
        Point point = new Point(longitude, latitude);
        Long result = redisTemplate.opsForGeo().add(key, point, member);
        System.out.println("geoHash");
        return "success";
    }

    @ResponseBody
    @RequestMapping("/redis_watch")
    public Object redisWatch() {
        String key = "books";

        // 开始事务功能
        redisTemplate.setEnableTransactionSupport(true);

        // 使用 redis watch 功能
        redisTemplate.watch(key);

        SessionCallback callback = new SessionCallback() {
            @Override
            public Object execute(RedisOperations operations) throws DataAccessException {
                operations.multi();
                operations.opsForValue().increment(key);
                operations.opsForValue().increment(key);
                operations.opsForValue().increment(key);
                return operations.exec();
            }
        };

        Object execute = redisTemplate.execute(callback);

        // 举例: redis watch method invoke the execute is :[1,2,3]
        System.out.println("redis watch method invoke the execute is :" + JSON.toJSONString(execute));

        return execute;
    }


}
