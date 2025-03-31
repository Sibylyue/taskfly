package org.taskfly.core.task;

import lombok.extern.slf4j.Slf4j;
import org.taskfly.core.dag.DAGEngin;
import org.taskfly.core.dag.DAGGraph;
import org.taskfly.core.dag.DAGGraphBuilder;
import org.taskfly.core.thread.ThreadPool;
import java.util.concurrent.ExecutorService;

@Slf4j
public class TaskNodeTest {
    public static void main(String[] args) throws Exception {
        //定义任务
        TaskNode taskNode1 = new TaskNode<Integer, Integer>("task1", new Task1());
        TaskNode taskNode2 = new TaskNode<Integer, Integer>("task2", new Task1());
        TaskNode taskNode3 = new TaskNode<Integer, Integer>("task3", new Task1());
        TaskNode taskNode4 = new TaskNode<Integer, Integer>("task4", new Task1());
        //定义依赖关系，并添加到dag中
        DAGGraph dagGraph = DAGGraphBuilder.newDAG()
        .addTaskNodes(taskNode1,taskNode2,taskNode3,taskNode4)
        .addTaskEdge(taskNode1, taskNode2)
        .addTaskEdge(taskNode1, taskNode3)
        .addTaskEdge(taskNode2, taskNode4)
        .addTaskEdge(taskNode3, taskNode4).build();

        //执行
        ExecutorService executor = ThreadPool.newTaskThreadPoolExecutor();
        log.info("start");
        DAGEngin dagEngin = new DAGEngin(dagGraph,executor);
        dagEngin.runAndWait();

        //执行
        ExecutorService executor1 = ThreadPool.newTaskThreadPoolExecutor();
        DAGEngin dagEngin1 = new DAGEngin(dagGraph,executor1);

    }


}
