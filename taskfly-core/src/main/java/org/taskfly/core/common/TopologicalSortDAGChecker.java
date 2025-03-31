package org.taskfly.core.common;
import org.apache.commons.collections4.CollectionUtils;
import org.taskfly.core.dag.DAGGraph;
import org.taskfly.core.exception.TaskRunException;
import org.taskfly.core.task.TaskNode;


import java.util.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;

public class TopologicalSortDAGChecker {
    /**
     * DAG：即从任意一个顶点出发，沿着有向边前进，都无法回到该顶点。
     * 拓扑排序：如果任意一有向边（u，v）,u都出现在v之前则是有向无环图。
     * Kahn算法不断选择入度为 0 的顶点，并将其从图中移除，
     * 同时更新其相邻顶点的入度，直到图中所有顶点都被处理
     * 或者无法继续选择入度为 0 的顶点。
     * @param taskNodeMap
     * @return
     */
    public static boolean isDag(Map<String,TaskNode<?,?>> taskNodeMap) {
        if(taskNodeMap == null || taskNodeMap.isEmpty()){
            System.out.println("taskNodeMap is empty");
            return false;
        }
        //计算入度
        Map<String,Integer> inDegree = new HashMap<>();
        //将所有入度为0的顶点加入队列
        Queue<String> queue = new LinkedList();
        taskNodeMap.forEach((name,taskNode)->{
            int size = taskNode.getDependSet().size();
            if(size == 0){
                queue.add(name);
            }else{
                inDegree.put(name,size);
            }
        });
        //从队列中取出一个顶点，加入拓扑排序，如相邻顶点入度为0将其加入队列
        //如果拓扑排序结果中顶点数等于图顶点，说明不存在环
        //如果拓扑排序结果中顶点小于图顶点，说明图中存在环
        System.out.println(Thread.currentThread());
        while(!queue.isEmpty()){
            String name = queue.poll();
            Iterator<TaskNode> iter = taskNodeMap.get(name).getNextSet().iterator();
            while(iter.hasNext()){
                String next = iter.next().getName();
                int i = inDegree.get(next);
                if(i>1){
                    inDegree.put(next,i-1);
                }else{
                    queue.add(next);
                    inDegree.remove(next);
                }
            };
        }
        if(inDegree.isEmpty()){
            return true;
        }
        return false;
    }

}
