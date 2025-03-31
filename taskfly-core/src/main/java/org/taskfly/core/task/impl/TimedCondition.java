package org.taskfly.core.task.impl;

public class TimedCondition {
    private final long time;
    private final boolean isTimeout;

    public TimedCondition(long time, boolean isTimeout) {
        this.time = time;
        this.isTimeout = isTimeout;
    }

    public long getTime() {
        return time;
    }

    public boolean isTimeout() {
        return isTimeout;
    }
}
