package com.deepblue.inaction_06_paxos_zookeeper.chapter_05.zookeeper_cluster.example_01_zkclient;

import org.I0Itec.zkclient.ZkClient;

/**
 *
 */
public class ZkClientExample {

    public final static String ZOOKEEPER_CONNECTION_URL =
            "192.168.188.66:2181," +
            "192.168.188.67:2181," +
            "192.168.188.68:2181," +
            "192.168.188.69:2181";

    public static void main(String[] args){

        ZkClient zkClient = new ZkClient(ZOOKEEPER_CONNECTION_URL, 5000);

//        // 判定节点是否存在
//        String exist = "/zookeeper";
//        boolean existFlag = zkClient.exists(exist);
//        System.out.println("existFlag is " + existFlag);
//
//        // 节点的添加
//        String path1 = zkClient.create("/concurre_books", "zhangsan", CreateMode.PERSISTENT);
//        String path2 = zkClient.create("/security_books", "lisi", CreateMode.PERSISTENT_SEQUENTIAL);
//        String path3 = zkClient.create("/zookeepe_books", "wangwu",  CreateMode.EPHEMERAL);
//        String path4 = zkClient.create("/elastics_books", "zhaoliu",  CreateMode.EPHEMERAL_SEQUENTIAL);
//        System.out.println("path1 is :" + path1);
//        System.out.println("path2 is :" + path2);
//        System.out.println("path3 is :" + path3);
//        System.out.println("path4 is :" + path4);
//
//        // 节点值的读取
//        Object data = zkClient.readData(path2);
//        System.out.println(path2 + " value is :" + data);
//
//        // 递归添加节点
//        zkClient.createPersistent("/parent/child", true);
//
//        // 节点值的设置与修改
//        zkClient.writeData("/parent/child", "child");
//
//        // 节点的获取
//        Object object = zkClient.readData("/parent/child");
//        System.out.println("/parent/child value is :" + object);
//
//        // 节点删除
//        zkClient.delete("/security_books0000000024");
//
//        // 递归删除
        zkClient.deleteRecursive("/dubbo");
        zkClient.deleteRecursive("/dubbo-dev");
        zkClient.deleteRecursive("/concurre_books");
//        zkClient.deleteRecursive("/security_books0000000029");

    }

}