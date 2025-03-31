package org.taskfly.core.executor;

import org.taskfly.core.task.TaskNode;
import org.taskfly.core.task.TaskResult;

/**
 * 实际执行任务，可以选择不同类型的执行器，
 * 如本地执行器、Celery 执行器等，以适应不同的计算环境和资源需求
 */
public interface TaskExecutor {

    <P,V> TaskResult<V> executeTask(TaskNode<P,V> taskNode);
}
