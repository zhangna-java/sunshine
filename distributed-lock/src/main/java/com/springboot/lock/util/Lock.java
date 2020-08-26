package com.springboot.lock.util;

/**
 * Created by zhangna on 2020/7/20 3:26 下午
 */
public interface Lock {

    void getLock() throws Exception;

    void unLock() throws Exception;
}
