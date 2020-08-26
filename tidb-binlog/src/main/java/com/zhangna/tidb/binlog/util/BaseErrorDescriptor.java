package com.zhangna.tidb.binlog.util;

/**
 * @Author zhangna
 * @Date 2019-05-20 16:20
 * @Description
 */
public interface BaseErrorDescriptor {

    int getCode();

    default String getBizCode() {
        if (this.getClass().isEnum()) {
            return ((Enum) this).name();
        }
        return null;
    }

    String getMessage();

}
