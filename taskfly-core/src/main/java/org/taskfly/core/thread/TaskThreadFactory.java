package org.taskfly.core.thread;

import java.util.concurrent.ThreadFactory;

//任务执行线程
public class TaskThreadFactory implements ThreadFactory {
    private final String namePrefix="Task";
    private int counter = 0;

    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread(r);
        thread.setName(namePrefix + "-" + counter++);
        return thread;
    }

}
