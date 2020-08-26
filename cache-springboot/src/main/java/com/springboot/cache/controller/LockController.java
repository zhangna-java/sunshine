package com.springboot.cache.controller;

import com.springboot.cache.lock.RedisLock;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zhangna on 2020/7/8 3:12 下午
 */
@RestController
@RequestMapping("/lock")
@Slf4j
public class LockController {

    @Autowired
    private RedisLock redisLock;

    int count = 0;

    @RequestMapping("/test")
    public String test() throws Exception{

        int clintCount = 10;

        CountDownLatch countDownLatch = new CountDownLatch(clintCount);
        ExecutorService executorService = Executors.newFixedThreadPool(clintCount);
        long start = System.currentTimeMillis();
        for (int i = 0; i < clintCount; i++) {
            executorService.execute( () -> {
                String id = UUID.randomUUID().toString().replace("-", "").toLowerCase();
                try{
                    redisLock.lock(id);
                    count++;
                }finally {
                    redisLock.unlock(id);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        long end = System.currentTimeMillis();
        log.info("执行线程数:{},总耗时:{},count数为:{}", clintCount, end - start, count);
        return "true";

    }


}
