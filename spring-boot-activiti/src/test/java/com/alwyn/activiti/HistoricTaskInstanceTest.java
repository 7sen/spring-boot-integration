package com.alwyn.activiti;

import java.util.List;
import org.activiti.engine.HistoryService;
import org.activiti.engine.history.HistoricTaskInstance;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class HistoricTaskInstanceTest {

    @Autowired
    private HistoryService historyService;

    //根据用户名查询历史记录
    @Test
    public void HistoricTaskInstanceByUser() {
        List<HistoricTaskInstance> list = historyService
                .createHistoricTaskInstanceQuery()
                .orderByHistoricTaskInstanceEndTime().asc()
                .taskAssignee("bajie")
                .list();
        for (HistoricTaskInstance hi : list) {
            System.out.println("Id：" + hi.getId());
            System.out.println("ProcessInstanceId：" + hi.getProcessInstanceId());
            System.out.println("Name：" + hi.getName());
        }
    }

    // 根据流程实例ID查询历史
    @Test
    public void HistoricTaskInstanceByPiID() {
        List<HistoricTaskInstance> list = historyService
                .createHistoricTaskInstanceQuery()
                .orderByHistoricTaskInstanceEndTime().asc()
                .processInstanceId("94db1b1c-dff9-11eb-a733-26fd52b9b2ca")
                .list();
        for (HistoricTaskInstance hi : list) {
            System.out.println("Id：" + hi.getId());
            System.out.println("ProcessInstanceId：" + hi.getProcessInstanceId());
            System.out.println("Name：" + hi.getName());
        }
    }
}
