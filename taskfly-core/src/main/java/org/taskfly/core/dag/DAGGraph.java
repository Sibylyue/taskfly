package org.taskfly.core.dag;

import org.taskfly.core.common.TopologicalSortDAGChecker;
import org.taskfly.core.task.TaskNode;
import org.taskfly.core.task.TaskNodeState;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 一个DAG被定义后可以多次执行
 *
 */
public class DAGGraph {
    private String name;
    private AtomicInteger dagState = new AtomicInteger(DAGState.INIT.getValue());
    private Map<String, TaskNode<?,?>> taskNodeMap;
    //开始node
    private Set<TaskNode<?,?>> beginTaskNodeSet = new HashSet<>();
    //结束node，线程执行期间可指定结束节点
    private Set<String> endTaskNodeSet = ConcurrentHashMap.newKeySet();
    private DAGContext dagContext = new DAGContext();
    //超时时间
    private long timeout;
    protected DAGGraph(){}

    public void setName(String name){
        this.name = name;
    }
    public void setTaskNodeMap(Map<String,TaskNode<?,?>> taskNodeMap){
        this.taskNodeMap = taskNodeMap;
    }
    public void setBeginTaskNodeSet(Set<TaskNode<?,?>> beginTaskNodeSet){
        this.beginTaskNodeSet = beginTaskNodeSet;
    }

    /**
     * 检查DAG是否可以运行
     * @return
     */
    public boolean isDAGGraphRunnable() {
        if(dagState.get() == DAGState.INIT.getValue()){

        }
        boolean ret = isDag();
        System.out.println("ret"+ret);
        return ret && (false == beginTaskNodeSet.isEmpty());
    }

    /**
     * 检查任务依赖的节点是否执行完成
     * @param taskNode
     * @return
     */
    public boolean isTaskRunnable(TaskNode<?,?> taskNode){
        boolean isRunnable = true;
        Iterator<TaskNode> iter = taskNode.getDependSet().iterator();
        while(iter.hasNext()){
            TaskNode dependNode = iter.next();
            if(taskNodeMap.get(dependNode.getName()).getTaskNodeState() !=TaskNodeState.SUCCESS.getValue()){
                isRunnable = false;
            }
        }
        return isRunnable;
    }

    public Set<TaskNode<?,?>> getbeginTaskNodeSet() {
        return this.beginTaskNodeSet;
    }

    public Map<String,TaskNode<?,?>> getTaskNodeMap() {return this.taskNodeMap;}

    public String getName() {return this.name;}


    private boolean isDag(){
        return TopologicalSortDAGChecker.isDag(taskNodeMap);
    }
    public void setState(int state){
        dagState.set(state);
    }
    public TaskNode getTaskNodeByName(String name){
        return taskNodeMap.get(name);
    }



}
