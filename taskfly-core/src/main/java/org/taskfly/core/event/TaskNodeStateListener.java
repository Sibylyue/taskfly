package org.taskfly.core.event;

import org.taskfly.core.task.TaskNodeState;
import org.taskfly.core.task.TaskNode;

/**
 * 执行过程监听器
 */
public interface TaskNodeStateListener {
    void onEvent(TaskNode node, TaskNodeState eventEnum);
}
