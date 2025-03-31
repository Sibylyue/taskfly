package org.taskfly.core.thread;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.alibaba.ttl.TtlRunnable;
import org.apache.catalina.session.StandardManager;
import org.apache.catalina.session.StandardSession;

import javax.servlet.http.HttpSession;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TransmittableThreadLocalExample {
    // 创建 TransmittableThreadLocal 实例
    private static final ThreadLocal<String> context = new TransmittableThreadLocal<>();

    public static void main(String[] args) {
        // 创建一个固定大小为 1 的线程池
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        HttpSession session = new StandardSession(new StandardManager());

        // 设置 TransmittableThreadLocal 的值
        context.set("value from main thread");

        // 使用 TtlRunnable 包装任务
        Runnable task = TtlRunnable.get(() -> {
            // 在子线程中获取 TransmittableThreadLocal 的值
            String value = context.get();
            System.out.println("Value in child thread: " + value);
        });

        // 提交任务到线程池
        executorService.submit(task);

        // 关闭线程池
        executorService.shutdown();
    }
}
