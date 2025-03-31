package org.taskfly.core.task;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TaskResult<V> {
    private V result;
    private Throwable error;
    /**
     * 结果状态
     */
    private ResultState resultState;

    public TaskResult(V result){
        this.result = result;
        this.resultState = ResultState.UNKNOWN;
    }

    public TaskResult(V result, ResultState resultState){
        this.result = result;
        this.resultState = resultState;
    }
    public TaskResult(V result, Throwable error, ResultState resultState){
        this.result = result;
        this.error = error;
        this.resultState = resultState;
    }
    public static <V> TaskResult<V> defaultResult() {
        return new TaskResult<>(null, ResultState.INIT);
    }



}
