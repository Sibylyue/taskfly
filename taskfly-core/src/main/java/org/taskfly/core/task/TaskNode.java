package org.taskfly.core.task;

import org.taskfly.core.dag.DAGContext;
import org.taskfly.core.exception.TaskRunException;

import java.util.*;
import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;


public class TaskNode<P,V>{
    //节点名，自定义,dag中唯一
    String name;
    //节点id，系统生成
    String id;
    // 有同一组输入和输出的任务
    TaskGroup group;
    //具体的任务
    Task<P,V> task;
    //任务执行结果
    TaskResult<V> taskResult;
    //获取任务执行参数
    P taskParam;
    //依赖条件
    Map<String,List<Condition>> conditions;
    //该任务的依赖节点,无重复节点
    Set<TaskNode> dependSet = new HashSet<>();
    //依赖该任务的节点，无重复节点
    Set<TaskNode> nextSet = new HashSet<>();
    //任务状态
    private AtomicInteger taskNodeState = new AtomicInteger(TaskNodeState.INIT.getValue());

    boolean isAsync = false;

    public TaskNode(){}
    public TaskNode(String name,Task<P,V> task){
        if(task==null){
            throw new TaskRunException("task is null in TaskNode");
        }
        this.name = name;
        this.task = task;
    }
    /**
     *
     */
    public void init(){
        this.id = UUID.randomUUID().toString();
        this.taskResult = TaskResult.defaultResult();
        this.taskParam = null;
        this.dependSet = new HashSet<>();
        this.conditions = new HashMap<>();
    }

    /**
     *  从上下文中获取参数
     * @param dagContext
     */
    public void setTaskResult(String name,DAGContext dagContext){
        this.taskParam = (P) dagContext.getTaskResult(name);
    }
    /**
     * task任务执行
     * @throws Exception
     */
    public void execute() throws Exception {
        if(task == null){
            throw new NullPointerException();
        }
        taskResult = TaskResult.defaultResult();
        try{
            task.onStart(taskParam);
            V result = (V)task.execute(taskParam);
            taskResult.setResult(result);
        }catch (Exception e){
            taskResult.setError(e);
            task.onError(taskParam,taskResult);
        }finally {
            taskResult.setResultState(ResultState.SUCCESS);
            task.onSuccess(taskParam,taskResult);
        }
    }


    public void interrupt() throws InterruptedException {
        if(task != null){
                task.interrupt();
        }

        Thread thread = Thread.currentThread();
        thread.interrupt();

    }

    public Set<TaskNode> getNextSet(){
        return this.nextSet;
    }

    public void addNext(TaskNode next){
        if(next != null){
            nextSet.add(next);
        }

    }
    public void addDepend(TaskNode depend){
        if(depend !=null){
            this.dependSet.add(depend);
        }
    }
    public String getName(){ return this.name;}
    public String getId(){ return this.id;}
    public int getTaskNodeState(){
        return taskNodeState.get();
    }
    public boolean compareAndSwap(int now,int to){
        return taskNodeState.compareAndSet(now,to);
    }
    public TaskResult<V> getResult(){return taskResult;}

    public Set<TaskNode> getDependSet(){return this.dependSet;}
    public Map<String,List<Condition>> getConditions(){return this.conditions;}
    public String getTaskName(){return this.name;}
    public TaskGroup getGroup(){return this.group;}
    public boolean isAsync(){return this.isAsync;}

}
