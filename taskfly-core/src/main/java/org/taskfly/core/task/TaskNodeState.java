package org.taskfly.core.task;

public enum TaskNodeState {
        INIT(1),
        READY(2),
        PENDING(3),
        RUNNING(4),
        SUCCESS(5),
        FAILED(6);
        private final int value;
        TaskNodeState(int value){
           this.value = value;
        }

        public int getValue() {
                return value;
        }
}
