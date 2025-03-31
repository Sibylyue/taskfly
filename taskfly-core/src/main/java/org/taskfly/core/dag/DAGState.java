package org.taskfly.core.dag;

public enum DAGState {

    INIT(1),

    RUNNABLE(2),
    /**
     * 未执行
     */
    READY(3),
    /**
     * 执行中
     */
    RUNNING(4),
    /**
     * 执行完成
     */
    SUCCESS(5),
    /**
     * 执行失败
     */
    FAILED(6);
    private final int value;

    DAGState(int value){
        this.value = value;
    }
    public int getValue(){
        return this.value;
    }
}
