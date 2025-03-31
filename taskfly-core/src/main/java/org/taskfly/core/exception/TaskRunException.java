package org.taskfly.core.exception;

public class TaskRunException extends RuntimeException{
    // 无参构造函数
    public TaskRunException() {
        super();
    }

    // 带消息参数的构造函数
    public TaskRunException(String message) {
        super(message);
    }

    // 带消息和异常原因参数的构造函数
    public TaskRunException(String message, Throwable cause) {
        super(message, cause);
    }
}
