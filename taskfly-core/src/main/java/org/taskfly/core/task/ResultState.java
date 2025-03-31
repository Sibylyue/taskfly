package org.taskfly.core.task;

public enum ResultState {
    INIT,
    SUCCESS,
    FAILURE,
    TIMEOUT,
    INTERRUPTED,
    SKIPPED,
    WAITING,
    RETRYING,
    UNKNOWN
}
