package com.alwyn.activiti;

import java.util.List;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ProcessInstancesTest {

    @Autowired
    private RuntimeService runtimeService;

    @Test
    public void initProcessInstance() {
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("myProcess_Part1", "businessKey");
        System.out.println("流程实例ID：" + processInstance.getProcessDefinitionId());
    }

    @Test
    public void getProcessInstances() {
        List<ProcessInstance> list = runtimeService.createProcessInstanceQuery().list();
        for (ProcessInstance pi : list) {
            System.out.println("--------流程实例------");
            System.out.println("ProcessInstanceId：" + pi.getProcessInstanceId());
            System.out.println("ProcessDefinitionId：" + pi.getProcessDefinitionId());
            System.out.println("isEnded：" + pi.isEnded());
            System.out.println("isSuspended：" + pi.isSuspended());
        }
    }

    @Test
    public void activateProcessInstance() {
        runtimeService.suspendProcessInstanceById("9717c8eb-de6a-11eb-95bf-26fd52b9b2ca");
        System.out.println("挂起流程实例");

        // runtimeService.activateProcessInstanceById("9717c8eb-de6a-11eb-95bf-26fd52b9b2ca");
        // System.out.println("激活流程实例");
    }
}
