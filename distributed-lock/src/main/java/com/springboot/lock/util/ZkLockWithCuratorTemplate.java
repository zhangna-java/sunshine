package com.springboot.lock.util;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.concurrent.TimeUnit;

/**
 * Created by zhangna on 2020/7/20 3:43 下午
 */
public class ZkLockWithCuratorTemplate implements Lock{

    private static final String host = "10.211.55.10:2181,10.211.55.12:2181,10.211.55.13:2181";

    // zk自增存储node
    private String lockPath = "/curatorLock";

    // 重试休眠时间
    private static final int SLEEP_TIME_MS = 1000;
    // 最大重试1000次
    private static final int MAX_RETRIES = 1000;
    //会话超时时间
    private static final int SESSION_TIMEOUT = 30 * 1000;
    //连接超时时间
    private static final int CONNECTION_TIMEOUT = 3 * 1000;// zk自增存储node

    private CuratorFramework curatorFramework;

    InterProcessMutex lock;

    public ZkLockWithCuratorTemplate(){
        curatorFramework = CuratorFrameworkFactory.builder() .connectString(host)
                .connectionTimeoutMs(CONNECTION_TIMEOUT)
                .sessionTimeoutMs(SESSION_TIMEOUT).retryPolicy(new ExponentialBackoffRetry(SLEEP_TIME_MS,MAX_RETRIES))
                .build();
        curatorFramework.start();

//        lock = new InterProcessMutex(curatorFramework,lockPath);
    }

    @Override
    public void getLock() throws Exception {
        lock.acquire(5, TimeUnit.SECONDS);
    }

    @Override
    public void unLock() throws Exception {
        lock.release();
    }

}
