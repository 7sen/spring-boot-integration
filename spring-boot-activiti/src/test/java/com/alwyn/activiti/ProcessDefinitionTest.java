package com.alwyn.activiti;

import java.util.List;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.ProcessDefinition;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ProcessDefinitionTest {

    @Autowired
    private RepositoryService repositoryService;

    @Test
    public void queryDefinition() {
        List<ProcessDefinition> processDefinitionList = repositoryService.createProcessDefinitionQuery().list();
        for (ProcessDefinition definition : processDefinitionList) {
            System.out.println("------流程定义--------");
            System.out.println("Name:" + definition.getName());
            System.out.println("Key:" + definition.getKey());
            System.out.println("DeploymentId:" + definition.getDeploymentId());
            System.out.println("ResourceName:" + definition.getResourceName());
            System.out.println("Version:" + definition.getVersion());
        }
    }


    @Test
    public void deleteDefinition() {
        repositoryService.deleteDeployment("08305e56-dda2-11eb-ad49-26fd52b9b2ca", true);
    }
}
