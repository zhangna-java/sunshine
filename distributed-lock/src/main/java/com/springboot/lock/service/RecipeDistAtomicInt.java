package com.springboot.lock.service;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.atomic.AtomicValue;
import org.apache.curator.framework.recipes.atomic.DistributedAtomicInteger;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.retry.RetryNTimes;

/**
 * Created by zhangna on 2020/7/20 4:37 下午  分布式计数器
 */
public class RecipeDistAtomicInt {

    static String distatomicint_path = "/curator_recipe_distatomicint_path";

    private static final String host = "10.211.55.10:2181,10.211.55.12:2181,10.211.55.13:2181";

    // 重试休眠时间
    private static final int SLEEP_TIME_MS = 1000;
    // 最大重试1000次
    private static final int MAX_RETRIES = 1000;
    //会话超时时间
    private static final int SESSION_TIMEOUT = 30 * 1000;
    //连接超时时间
    private static final int CONNECTION_TIMEOUT = 3 * 1000;// zk自增存储node

    static CuratorFramework client = CuratorFrameworkFactory.builder() .connectString(host)
            .connectionTimeoutMs(CONNECTION_TIMEOUT)
            .sessionTimeoutMs(SESSION_TIMEOUT).retryPolicy(new ExponentialBackoffRetry(SLEEP_TIME_MS, MAX_RETRIES))
            .build();

    public static void main(String[] args) throws Exception {
        client.start();
        DistributedAtomicInteger atomicInteger = new DistributedAtomicInteger(client,distatomicint_path,new RetryNTimes(3,1000));

        AtomicValue<Integer> ac = atomicInteger.add(8);
        System.out.println("result... "+ac.succeeded());
    }

}
