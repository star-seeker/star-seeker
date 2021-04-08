package com.book.concurrency.chapter7.program12;

import java.util.concurrent.Callable;
import java.util.concurrent.RunnableFuture;

/**
 * @author zhangyoubao
 * @version 2021/4/7
 */
public interface CancellableTask<T> extends Callable<T> {
    void cancel();
    RunnableFuture<T> newTask();
}
