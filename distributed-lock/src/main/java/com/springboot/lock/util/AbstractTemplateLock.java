//package com.springboot.lock.util;
//
///**
// * Created by zhangna on 2020/7/20 3:29 下午
// */
//public abstract class AbstractTemplateLock implements Lock{
//
//    @Override
//    public void getLock() throws Exception {
//        if(tryLock()){
//            System.out.println("获取锁成功。。。");
//        }else {
//            waitLock();
//            getLock();
//        }
//
//    }
//
//    @Override
//    public void unLock() throws Exception {
//
//    }
//
//    protected abstract void waitLock();
//    protected abstract boolean tryLock();
//    protected abstract void releasrLock();
//}
