package org.taskfly.core.thread;

import com.alibaba.ttl.TtlRunnable;
import org.apache.catalina.User;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadLocalTest {
    private static AtomicInteger atomicInteger = new AtomicInteger(0);

    /**
     * @param args
     */
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        for (int i = 0; i < 6; i++) {
            executorService.submit(run());
        }
        SessionManager.remove();
        executorService.shutdown();
    }
    private static Runnable run(){
        return () -> {
            Thread thread = Thread.currentThread();
            //System.out.println(thread.getName());
            UserSession old = SessionManager.get();
            UserSession userSession = new UserSession(thread.getName(),atomicInteger.getAndAdd(1));
            SessionManager.set(userSession);
            try {
                Thread.sleep(60);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("old="+old);
            System.out.println("new="+SessionManager.get());


        };
    }
    private static Runnable runTtl(){
        // 使用 TtlRunnable 包装任务
        return TtlRunnable.get(() -> {
            // 在子线程中获取 TransmittableThreadLocal 的值
            System.out.println("Value in child thread: " );
        });
    }

}
