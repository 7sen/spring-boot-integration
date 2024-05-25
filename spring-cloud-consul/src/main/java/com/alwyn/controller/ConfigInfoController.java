package com.alwyn.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 配置自动刷新
 * </p>
 *
 * @author: Ryan
 * @date: 2024-05-25 22:03
 */
@RestController
@RefreshScope
public class ConfigInfoController {

    @Value("${config.info}")
    private String configInfo;

    @GetMapping("/configInfo")
    public String getConfigInfo() {
        return configInfo;
    }
}