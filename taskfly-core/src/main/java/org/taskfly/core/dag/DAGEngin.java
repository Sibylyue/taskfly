package org.taskfly.core.dag;

import lombok.extern.slf4j.Slf4j;
import org.taskfly.core.exception.DAGRunException;
import org.taskfly.core.scheduler.Scheduler;
import org.taskfly.core.task.TaskNode;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;

public class DAGEngin {
    private DAGGraph dagGraph;
    private ExecutorService dagExecutor;
    //private Scheduler scheduler;
    private long timeout;

    public DAGEngin(DAGGraph dagGraph, ExecutorService dagExecutor){
        this.dagGraph = dagGraph;
        this.dagExecutor = dagExecutor;
    }

    /**
     * 1、检测DAG是否有向无环图
     * 2、执行DAG
     */
    public void runAndWait(){
        if(dagGraph.isDAGGraphRunnable() == false){
            dagGraph.setState(DAGState.RUNNABLE.getValue());
            System.out.println("not isDAGGraphRunnable");
            return;
        }
        if(dagExecutor == null){
            return;
        }
        dagGraph.setState(DAGState.RUNNING.getValue());
        dagGraph.getbeginTaskNodeSet().forEach((taskNode)->{
            if(dagGraph.isTaskRunnable(taskNode)){
                runTask(taskNode);
            }
        });
    }



    /**
     * 执行节点任务
     * @param taskNode
     */
    private void runTask(TaskNode taskNode){
        dagExecutor.submit(()->{
            try {
                System.out.println(taskNode.getTaskName());
                taskNode.execute();
                taskNode.getNextSet().forEach((nextTaskNode)->{
                    runTask((TaskNode) nextTaskNode);
                });
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }



    /**
     * 异步执行节点任务
     * @param taskNode
     */
    private void runTaskAsync(TaskNode taskNode){

    }

    public void stop(){

    }
    /**
     * 指定结束节点，指定wrapperId
     */
    public void stopAt(String ... endNodeName) {

    }

    public void pause(){

    }
    public void resume(){
        //log.info("DAG resume");
    }
    public void restart(){
        //log.info("DAG restart");
    }


}
