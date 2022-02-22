package com.deepblue.inaction_06_paxos_zookeeper.chapter_05.zookeeper_cluster.example_02_curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.zookeeper.CreateMode;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * User: YANG
 * Date: 2019/5/25-10:39
 * Description: No Description
 */
public class CuratorWatcherExample {

    public static void main(String[] args) throws Exception {

        CuratorFramework curatorFramework = CuratorClientUtil.getInstance();

        /**
         * curator中提供了三种监视事件的watcher
         * PathCache    监视一个路径下子节点的创建、删除、节点数据更新
         * NodeCache    监视一个节点的创建、更新、删除
         * TreeCache    pathcaceh + nodecache 的合体（监视路径下的创建、更新、删除事件），缓存路径下的所有子节点的数据
         */

        /**
         * NodeCache演示
         * NodeCache中的三个参数中true为是否进行数据压缩,如果压缩则 nodeCache.getCurrentData().getData() 抛出异常GZIP的异常
         */
//        final NodeCache nodeCache = new NodeCache(curatorFramework, "/curator", true);
//        nodeCache.start(true);
//
//        nodeCache.getListenable().addListener(new NodeCacheListener() {
//            @Override
//            public void nodeChanged() throws Exception {
//                System.out.println("nodeCache.getCurrentData().getData ------------->:" + nodeCache.getCurrentData().getData());
//                System.out.println("nodeCache.getCurrentData().getPath ------------->:" + nodeCache.getCurrentData().getPath());
//            }
//        });
//
//        curatorFramework.setData().forPath("/curator", "curator_up".getBytes());

        /**
         * PathChildrenCache
         */
        PathChildrenCache pathChildrenCache = new PathChildrenCache(curatorFramework, "/curator", true);
        pathChildrenCache.start(PathChildrenCache.StartMode.POST_INITIALIZED_EVENT);
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        pathChildrenCache.getListenable().addListener(new PathChildrenCacheListener() {
            @Override
            public void childEvent(CuratorFramework client, PathChildrenCacheEvent event) throws Exception {
                switch (event.getType()){
                    case CHILD_ADDED:
                        System.out.println("增加子节点");
                        break;
                    case CHILD_REMOVED:
                        System.out.println("删除子节点");
                        break;
                    case CHILD_UPDATED:
                        System.out.println("更新子节点");
                        break;
                    default:break;
                }
            }
        }, executorService);
        curatorFramework.create().withMode(CreateMode.PERSISTENT).forPath("/curator","event".getBytes());
        TimeUnit.SECONDS.sleep(2);
        System.out.println("1");

        curatorFramework.create().withMode(CreateMode.EPHEMERAL).forPath("/curator/curator1","1".getBytes());
        TimeUnit.SECONDS.sleep(2);
        System.out.println("2");

        curatorFramework.setData().forPath("/curator/curator1", "222".getBytes());
        TimeUnit.SECONDS.sleep(2);
        System.out.println("3");

        curatorFramework.delete().forPath("/curator/curator1");
        System.out.println("4");


        //延迟 main 线程的执行实现,来看各种效果
        System.in.read();


    }

}
