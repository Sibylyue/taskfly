package org.taskfly.core.dag;

import org.taskfly.core.task.TaskResult;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DAGContext {
    Map<String, TaskResult> context = new ConcurrentHashMap<>();

    public void addResult(String taskName, TaskResult result){
        context.put(taskName,result);
    }
    public TaskResult getTaskResult(String taskName){
        return context.get(taskName);
    }
}
