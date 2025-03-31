package org.taskfly.core.task;

import java.util.List;
import java.util.concurrent.TimeUnit;

public interface TaskDependency {

    /**
     * 获取等待的时间
     */
    long getWaitTime();

    /**
     * 获取时间单位
     */
    TimeUnit getWaitTimeUnit();

    /**
     * 获取事件触发的条件
     */
    String getEventCondition();

    /**
     * 设置等待时间
     */
    void setWaitTime(long time, TimeUnit unit);

    /**
     * 设置事件触发条件
     */
    void setEventCondition(String event);
}

