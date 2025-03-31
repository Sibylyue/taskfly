package org.taskfly.core.scheduler;

import org.taskfly.core.dag.DAGEngin;
import org.taskfly.core.dag.DAGGraph;

import java.util.concurrent.Executor;

/**
 * 任务调度器Scheduler
 * 调度器会根据 DAG的定义和调度策略，将满足条件的任务发给执行器负责执行，
 * 并定期检查和记录每个DAG和任务的执行状态，并处理异常和重试。
 * 支持的调度策略：
 * 周期性执行：借助Cron表达式等按时、天、周、月等调度
 * 时间点出发：设定时间点触发
 * 依赖调度：确保DAG任务按照定义依赖关系顺序执行
 * 延迟执行：某任务完成后等待一段时间触发下一个任务
 */
public interface Scheduler {
    void schedule(DAGGraph dagGraph, Executor executor);
    //List<String> getExecutionOrder();
}
