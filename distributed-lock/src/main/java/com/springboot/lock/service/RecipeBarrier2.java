//package com.springboot.lock.service;
//
//import org.apache.curator.framework.CuratorFramework;
//import org.apache.curator.framework.CuratorFrameworkFactory;
//import org.apache.curator.framework.recipes.barriers.DistributedBarrier;
//import org.apache.curator.retry.ExponentialBackoffRetry;
//
///**
// * Created by zhangna on 2020/7/20 4:42 下午
// */
//public class RecipeBarrier2 {
//
//    static String barrier_path = "/curator_recipe_barrier_path";
//
//    private static final String host = "10.211.55.10:2181,10.211.55.12:2181,10.211.55.13:2181";
//
//    // 重试休眠时间
//    private static final int SLEEP_TIME_MS = 1000;
//    // 最大重试1000次
//    private static final int MAX_RETRIES = 1000;
//    //会话超时时间
//    private static final int SESSION_TIMEOUT = 30 * 1000;
//    //连接超时时间
//    private static final int CONNECTION_TIMEOUT = 3 * 1000;// zk自增存储node
//
//    static CuratorFramework client = CuratorFrameworkFactory.builder() .connectString(host)
//            .connectionTimeoutMs(CONNECTION_TIMEOUT)
//            .sessionTimeoutMs(SESSION_TIMEOUT).retryPolicy(new ExponentialBackoffRetry(SLEEP_TIME_MS, MAX_RETRIES))
//            .build();
//
//  static DistributedBarrier distributedBarrier;
//    public static void main(String[] args) throws Exception {
//        client.start();
//        for (int i = 0; i < 5; i++) {
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    DistributedBarrier distributedBarrier = new DistributedBarrier(client,barrier_path);
//                    distributedBarrier.e
//                }
//            }).start();
//
//        }
//
//    }
//}
