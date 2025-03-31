package org.taskfly.core.task;

/**
 * 任务对应的具体处理逻辑
 * @param <P>
 * @param <V>
 */
public interface Task<P,V>{

    /**
     * 自定义默认返回值，比如节点执行异常时
     */
    default V defaultValue() {
        return null;
    }

    /**
     * 该方法实现具体执行逻辑
     */
    TaskResult<V> execute(P param) throws Exception;

    /**
     * 执行前回调,回调逻辑不影响正常流程执行
     */
    default void onStart(P param) {}
    /**
     * 执行成功后回调
     */
    default void onSuccess(P param, TaskResult<V> result) {}
    /**
     * 执行异常后回调
     */
    default void onError(P param, TaskResult<V> result) {}

    // 中断任务执行
    void interrupt() throws InterruptedException;
}
