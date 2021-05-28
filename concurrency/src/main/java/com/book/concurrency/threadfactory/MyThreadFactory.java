package com.book.concurrency.threadfactory;

import java.util.concurrent.ThreadFactory;

/**
 * @author zhangyoubao
 * @version 2021/5/27
 */
public class MyThreadFactory implements ThreadFactory {

    private final String poolName;

    public MyThreadFactory(String poolName) {
        this.poolName = poolName;
    }

    @Override
    public Thread newThread(Runnable r) {
        return new MyAppThread(r, poolName);
    }
}
