package org.taskfly.core.task;

public class Task1 implements Task<Integer,Integer>{
        @Override
        public TaskResult<Integer> execute(Integer param) throws Exception {
                System.out.println("task demo:"+param+Thread.currentThread());

                return new TaskResult<Integer>(param+1);
        }

        @Override
        public void interrupt() throws InterruptedException {
                return;
        }
}
