package com.springboot.lock.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * Created by zhangna on 2020/7/9 10:00 上午
 */
@Slf4j
public class ZookeeperUtil {


    public static ZooKeeper getInstance() throws IOException, InterruptedException {

       final CountDownLatch countDownLatch = new CountDownLatch(1);

        ZooKeeper zooKeeper = new ZooKeeper("10.211.55.10:2181,10.211.55.12:2181,10.211.55.13:2181", 5000, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                if(Event.KeeperState.SyncConnected == watchedEvent.getState()){
                    countDownLatch.countDown();
                }
            }
        });

        countDownLatch.await();
        return zooKeeper;
    }

//    public static void main(String[] args) {
//        try {
//            ZooKeeper instance = getInstance();
//            System.out.println(instance);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
}
