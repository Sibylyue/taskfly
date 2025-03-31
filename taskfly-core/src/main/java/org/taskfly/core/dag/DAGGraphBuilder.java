package org.taskfly.core.dag;

import org.taskfly.core.exception.DAGRunException;
import org.taskfly.core.task.TaskNode;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class DAGGraphBuilder {
    private String name;
    private Map<String, TaskNode<?,?>> taskNodeMap;
    protected  DAGGraphBuilder(){
    }
    public static DAGGraphBuilder newDAG(){
        return new DAGGraphBuilder();
    }
    public DAGGraph build(){
        DAGGraph dag = new DAGGraph();
        dag.setName(this.name);
        dag.setTaskNodeMap(this.taskNodeMap);
        dag.setBeginTaskNodeSet(getBeginTaskNodeSet());
        return dag;
    }
    public DAGGraphBuilder addTaskNodes(TaskNode<?,?>... taskNodes){
        this.taskNodeMap = new ConcurrentHashMap();
        for(TaskNode<?,?> taskNode:taskNodes) {
            if(!taskNodeMap.containsKey(taskNode.getName())){
                taskNodeMap.put(taskNode.getName(),taskNode);
            }
        };
        return this;
    }
    public DAGGraphBuilder addTaskEdge(TaskNode<?,?> source,TaskNode target){
        if(source != null || target != null){
            source.addNext(target);
            target.addDepend(source);
        }else{
            throw new DAGRunException("addTaskEdge error");
        }
        return this;
    }

    private Set<TaskNode<?,?>> getBeginTaskNodeSet(){
        Set<TaskNode<?,?>> beginTaskNodeSet = new HashSet<>();
        taskNodeMap.forEach((name,taskNode)->{
            if(taskNode.getDependSet().isEmpty()){
                beginTaskNodeSet.add(taskNode);
            }
        });
        return beginTaskNodeSet;
    }
}
