package com.alwyn.activiti;

import java.util.List;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TaskTest {

    @Autowired
    private TaskService taskService;

    // 任务查询
    @Test
    public void getTasks() {
        List<Task> list = taskService.createTaskQuery().list();
        for (Task tk : list) {
            System.out.println("Id：" + tk.getId());
            System.out.println("Name：" + tk.getName());
            System.out.println("Assignee：" + tk.getAssignee());
        }
    }

    // 查询我的代办任务
    @Test
    public void getTasksByAssignee() {
        List<Task> list = taskService.createTaskQuery().taskAssignee("bajie").list();
        for (Task tk : list) {
            System.out.println("Id：" + tk.getId());
            System.out.println("Name：" + tk.getName());
            System.out.println("Assignee：" + tk.getAssignee());
        }
    }

    // 执行任务
    @Test
    public void test() {
        taskService.complete("86294c13-dfff-11eb-b1cc-26fd52b9b2ca");
    }

    //拾取任务
    @Test
    public void claimTask() {
        Task task = taskService.createTaskQuery().taskId("eb904552-dffe-11eb-9a07-26fd52b9b2ca").singleResult();
        taskService.claim("eb904552-dffe-11eb-9a07-26fd52b9b2ca", "bajie");
    }

    // 归还与交办任务
    @Test
    public void setTaskAssignee() {
        Task task = taskService.createTaskQuery().taskId("eb904552-dffe-11eb-9a07-26fd52b9b2ca").singleResult();
        // taskService.setAssignee("94e15cb0-dff9-11eb-a733-26fd52b9b2ca", "null");//归还候选任务
        taskService.setAssignee("eb904552-dffe-11eb-9a07-26fd52b9b2ca", "wukong");//交办
    }
}
