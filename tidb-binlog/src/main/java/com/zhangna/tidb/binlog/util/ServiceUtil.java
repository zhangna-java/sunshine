package com.zhangna.tidb.binlog.util;


import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * 为Service类提供的工具/模板方法
 * @author zhangna
 * @date  2019-09-19 10:30
 */
public class ServiceUtil {


    /**
     * 无返回值的业务逻辑代码模板，无额外的异常处理，走公用的异常处理逻辑
     * @param biz
     * @throws
     */
    public static void execute(Runnable biz) throws BaseException {
        execute(biz, null);
    }

    /**
     * 无返回值的业务逻辑代码模板，带额外的异常处理
     * @param biz
     * @param exceptionConsumer
     * @throws
     */
    public static void execute(Runnable biz, Consumer<Throwable> exceptionConsumer) throws BaseException {
        execute(() -> {
            biz.run();
            return Boolean.TRUE;
        }, exceptionConsumer);
    }


    /**
     * 执行指定的业务逻辑，默认带了最外层异常捕获。 在发生异常时抛出BizException
     * @param supplier
     * @param <T>
     * @return
     * @throws
     */
    public static <T> T execute(Supplier<T> supplier) throws BaseException {
        return execute(supplier, null);
    }


    /**
     * 执行指定的业务逻辑，异常发生时使用指定的异常处理逻辑处理
     * @param supplier
     * @param exceptionConsumer 异常处理逻辑，为空时执行默认处理： 抛出BizException
     * @param <T>
     * @return
     * @throws
     */
    public static <T> T execute(Supplier<T> supplier, Consumer<Throwable> exceptionConsumer) throws BaseException {
        try {
            return supplier.get();
        } catch (BaseException b) {
            throw b;
        } catch (Throwable t) {
            if (exceptionConsumer != null) {
                try {
                    exceptionConsumer.accept(t);
                } catch (BaseException b) {
                    throw b;
                } catch (Throwable tt) {
                    throw new BaseException(tt.getMessage());
                }
            }
            throw new BaseException(t.getMessage());
        }
    }

}
