package com.book.concurrency.threadfactory;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author zhangyoubao
 * @version 2021/5/27
 */
public class Test {

    private static final ThreadFactory tf = new MyThreadFactory("Test");

    public static void main(String[] args) {

        {
            Runnable runnable = () -> {
                int i = 1/0;
                System.out.println(i);
            };

            MyAppThread.setDebug(true);
            tf.newThread(runnable).start();
            tf.newThread(runnable).start();
            tf.newThread(runnable).start();

            System.out.println(MyAppThread.getThreadsCreated());
            System.out.println(MyAppThread.getThreadsAlive());
        }

        {
            Runnable command = () -> {
                int sum = 0;
                for (int i = 0; i < 1000; i++) {
                    sum += i;
                }
            };
            TimingThreadPool threadPool = new TimingThreadPool((ThreadPoolExecutor) Executors.newFixedThreadPool(10));
            threadPool.execute(command);
            threadPool.execute(command);

            threadPool.shutdown();
        }
    }
}
