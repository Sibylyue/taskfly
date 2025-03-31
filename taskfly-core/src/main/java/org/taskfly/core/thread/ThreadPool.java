package org.taskfly.core.thread;

import com.sun.corba.se.spi.orbutil.threadpool.WorkQueue;

import java.util.concurrent.*;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.logging.Handler;

public class ThreadPool {
    /**
     *
     * @param corePoolSize
     * 始终存活的最小线程数，CPU密集型=核数；IO密集型=核数*2
     * @param maximumPoolSize
     * 最大线程数，超过后新任务触发拒绝策略，corePoolSize*(2~3)当使用无界队列时，该值不生效。
     * @param keepAliveTime
     * 多余corePoolSize的线程超过该时间被收回，通常60s
     * @param unit
     * @param workQueue
     * 存放待执行任务队列
     * @param threadFactory
     * 自定义
     * @param handler
     * 拒绝策略
     * @return
     */
    public static ExecutorService newTaskThreadPoolExecutor(
            int corePoolSize,
            int maximumPoolSize,
            long keepAliveTime,
            TimeUnit unit,
            ArrayBlockingQueue workQueue

    ) {
        return new ThreadPoolExecutor(
                corePoolSize,
                maximumPoolSize,
                keepAliveTime,
                unit,
                workQueue,
                new TaskThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy()
        );
    }
    public static ExecutorService newTaskThreadPoolExecutor() {
        return newTaskThreadPoolExecutor(
                2,
                6,
                60,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(100)
        );
    }
}
