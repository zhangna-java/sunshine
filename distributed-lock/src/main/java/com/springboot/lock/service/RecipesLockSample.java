package com.springboot.lock.service;

import com.springboot.lock.util.ZkLockWithCuratorTemplate;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

/**
 * Created by zhangna on 2020/7/20 4:06 下午  基于curator实现分布式锁
 */
public class RecipesLockSample {

    static String lock_path = "/curator_lock_path";

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

    public static void main(String[] args) {

        client.start();
        System.out.println("started");

        final InterProcessMutex lock  = new InterProcessMutex(client,lock_path); //分布式可重入排它锁

        final CountDownLatch countDownLatch = new CountDownLatch(1);
        for (int i = 0; i < 30; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        countDownLatch.await();
                        try {
                            //尝试加锁  调用方法后，会一直阻塞，直到抢夺到锁资源，或者zookeeer连接中断后，抛出异常
                            lock.acquire();
                        } catch (Exception e) {

                        }

                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss|SSS");
                        String format = simpleDateFormat.format(new Date());
                        System.out.println("生成。。。"+format);
                        try {
                            lock.release();
                        }catch (Exception ex){

                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();

            countDownLatch.countDown();
        }

    }
}

