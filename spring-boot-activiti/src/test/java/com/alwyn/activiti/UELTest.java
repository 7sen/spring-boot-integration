package com.alwyn.activiti;

import java.util.HashMap;
import java.util.Map;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UELTest {

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    //启动流程实例带参数，执行执行人
    @Test
    public void initProcessInstanceWithArgs() {
        //流程变量
        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("assignee", "bajie");
        //variables.put("ZhiXingRen2", "aaa");
        //variables.put("ZhiXingRen3", "wukbbbong");
        ProcessInstance processInstance = runtimeService
                .startProcessInstanceByKey(
                        "myProcess_UEL"
                        , "bKey002"
                        , variables);
        System.out.println("流程实例ID：" + processInstance.getProcessDefinitionId());
    }
}
