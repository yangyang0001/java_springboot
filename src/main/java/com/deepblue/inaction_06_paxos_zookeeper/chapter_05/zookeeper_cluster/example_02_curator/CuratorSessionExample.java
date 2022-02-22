package com.deepblue.inaction_06_paxos_zookeeper.chapter_05.zookeeper_cluster.example_02_curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * User: YANG
 * Date: 2019/5/24-18:56
 * Description: No Description
 * curator提供两种创建 链接的方式
 * newClient的重载的方法!
 */
public class CuratorSessionExample {

    public final static String ZOOKEEPER_CONNECTION_URL =
            "192.168.188.15:2181," +
            "192.168.188.16:2181," +
            "192.168.188.17:2181," +
            "192.168.188.18:2181";

    public static void main(String[] args){

        // 第一种创建链接的方式
        CuratorFramework curatorFramework = CuratorFrameworkFactory.newClient(ZOOKEEPER_CONNECTION_URL, 50000,
                5000, new ExponentialBackoffRetry(1000, 3));
        curatorFramework.start();

        // 第二种创建链接的方式
        CuratorFramework curatorFramework1 = CuratorFrameworkFactory
                .builder()
                .connectString(ZOOKEEPER_CONNECTION_URL).sessionTimeoutMs(50000)
                .retryPolicy(new ExponentialBackoffRetry(1000, 3))
                .build();
        curatorFramework1.start();

        System.out.println("suceess");
    }
}
