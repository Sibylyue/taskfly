package org.taskfly.core.callbacks;

/**
 * 任务回调
 */
@FunctionalInterface
public interface Callback {
    void call() throws Exception;
}
