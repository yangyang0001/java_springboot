package com.deepblue.inaction_06_paxos_zookeeper.chapter_05.zookeeper_cluster.example_02_curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * User: YANG
 * Date: 2019/5/24-21:54
 * Description: No Description
 */
public class CuratorClientUtil {

    public final static String ZOOKEEPER_CONNECTION_URL =
            "192.168.188.15:2181," +
            "192.168.188.16:2181," +
            "192.168.188.17:2181," +
            "192.168.188.18:2181";


    public static CuratorFramework getInstance(){

//        CuratorFramework curatorFramework = CuratorFrameworkFactory.newClient(ZOOKEEPER_CONNECTION_URL, 50000,
//                5000, new ExponentialBackoffRetry(1000, 3));
//        curatorFramework.start();

        CuratorFramework curatorFramework = CuratorFrameworkFactory
                .builder()
                .connectString(ZOOKEEPER_CONNECTION_URL).sessionTimeoutMs(50000)
                .retryPolicy(new ExponentialBackoffRetry(1000, 3))
                .build();
        curatorFramework.start();

        return curatorFramework;
    }

}
