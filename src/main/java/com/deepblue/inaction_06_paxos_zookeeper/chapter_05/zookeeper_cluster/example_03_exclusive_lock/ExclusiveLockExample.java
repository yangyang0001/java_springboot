package com.deepblue.inaction_06_paxos_zookeeper.chapter_05.zookeeper_cluster.example_03_exclusive_lock;

import com.deepblue.inaction_06_paxos_zookeeper.chapter_05.zookeeper_cluster.example_02_curator.CuratorClientUtil;
import org.I0Itec.zkclient.ZkClient;
import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.CreateMode;

/**
 *
 */
public class ExclusiveLockExample {

    public static final String PREFIX_PATH = "/exclusive_lock";

    public static void main(String[] args) throws Exception {

        CuratorFramework curatorFramework = CuratorClientUtil.getInstance();

        // 订单/书籍/zookeeper
        String lockPath = PREFIX_PATH + "/order/books/zookeeper";

        /**
         * 创建节点, 可以递归创建 creatingParentIfNeeded
         */

        for(int i = 0; i < 10; i++) {
            String exclusivePath = curatorFramework.create()
                    .creatingParentsIfNeeded()
                    .withMode(CreateMode.EPHEMERAL)
                    .forPath(lockPath, "zookeeper_inaction".getBytes());

            System.out.println(i + " = " + i + ", exclusivePath is :" + exclusivePath);
        }

    }


}
