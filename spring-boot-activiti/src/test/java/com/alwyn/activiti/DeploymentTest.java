package com.alwyn.activiti;

import java.io.InputStream;
import java.util.zip.ZipInputStream;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class DeploymentTest {

    @Autowired
    private RepositoryService repositoryService;

    @Test
    public void initDeploymentBPMN() {
        String filename = "processes/UEL_Test.bpmn";
        Deployment deployment = repositoryService.createDeployment()
                .addClasspathResource(filename)
                .name("流程部署测试UEL表达式")
                .deploy();
        System.out.println(deployment.getName());
    }

    @Test
    public void initDeploymentZIP() {
        InputStream stream = Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("processes/Part1_DeploymentV2.zip");
        ZipInputStream zipInputStream = new ZipInputStream(stream);
        Deployment deployment = repositoryService.createDeployment()
                .addZipInputStream(zipInputStream)
                .name("流程部署测试zip")
                .deploy();
        System.out.println(deployment.getName());
    }
}
