package com.springboot.lock.config;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * Created by zhangna on 2020/7/20 2:58 下午
 */
public class CuratorUtil {

    public static CuratorFramework getConn(){

        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000,3);

        CuratorFramework client = CuratorFrameworkFactory.newClient("10.211.55.10:2181,10.211.55.12:2181,10.211.55.13:2181",retryPolicy);

        return client;


    }

}
