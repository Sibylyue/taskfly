package org.taskfly.core.task;

/**
 * TaskNode节点执行条件
 */
public interface Condition {
    //是否必须
    boolean isRequired();
    boolean isSatisfied();
}
