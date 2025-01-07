package com.deepblue.inaction_01_springboot2.chapter_16.example_01_exclusive_lock_curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 *
 */
@Service
public class OrderServiceImpl implements OrderService {

    private static Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    private CuratorFramework curatorFramework;

    public static final String LOCK_PATH = "/lock/order/";


    @Override
    public void makeOrderType(String type) {
        String path = LOCK_PATH + type;
        logger.info("order service make order type method invoke, type is :" + type);

        try {
            InterProcessMutex lock = new InterProcessMutex(curatorFramework, path);

            try {
                if(lock.acquire(10, TimeUnit.HOURS)) {
                    Thread.sleep(5000);
                    logger.info("make order type method invoke type is : " + type + ", do job done!");
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.release();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
