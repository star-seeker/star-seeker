package com.book.concurrency.threadfactory;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

/**
 * 增加日志和计时等功能的线程池
 * @author zhangyoubao
 * @version 2021/5/27
 */
public class TimingThreadPool extends ThreadPoolExecutor {

    public TimingThreadPool(ThreadPoolExecutor threadPoolExecutor) {
        super(threadPoolExecutor.getCorePoolSize(), threadPoolExecutor.getMaximumPoolSize(),
                threadPoolExecutor.getKeepAliveTime(TimeUnit.MILLISECONDS), TimeUnit.MILLISECONDS,
                threadPoolExecutor.getQueue(), threadPoolExecutor.getThreadFactory(),
                threadPoolExecutor.getRejectedExecutionHandler());
    }

    private final ThreadLocal<Map> startTime = new ThreadLocal<>();
    private final Logger log = Logger.getLogger("TimingThreadPool");
    private final AtomicLong numTasks = new AtomicLong();
    private final AtomicLong totalTime = new AtomicLong();

    protected void beforeExecute(Thread t, Runnable r) {
        super.beforeExecute(t, r);
        log.info(String.format("Thread %s: start %s", t.getId(), r.hashCode()));

        Map<String, Long> map = new HashMap<>();
        map.put("tid", t.getId());
        map.put("time", System.nanoTime());
        startTime.set(map);
    }

    protected void afterExecute(Runnable r, Throwable t) {
        try {
            long endTime = System.nanoTime();
            long taskTime = endTime - Long.parseLong(startTime.get().get("time").toString());
            numTasks.incrementAndGet();
            totalTime.addAndGet(taskTime);
            log.info(String.format("Thread %s: end %s, time=%dns", startTime.get().get("tid").toString(), r.hashCode(), taskTime));
        } finally {
            super.afterExecute(r, t);
        }
    }

    protected void terminated() {
        try {
            log.info(String.format("Terminated: avg time=%dns", totalTime.get() / numTasks.get()));
        } finally {
            super.terminated();
        }
    }
}
