package org.taskfly.core.exception;

public class DAGRunException extends RuntimeException{
    public DAGRunException() {
        super();
    }

    public DAGRunException(String message) {
        super(message);
    }

    public DAGRunException(String message, Throwable cause) {
        super(message, cause);
    }
}
