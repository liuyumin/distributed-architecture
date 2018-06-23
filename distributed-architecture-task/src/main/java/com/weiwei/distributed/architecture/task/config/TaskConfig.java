package com.weiwei.distributed.architecture.task.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Import(XxlJobConfig.class)
@Configuration
@ComponentScan(basePackages = {"com.weiwei.distributed.architecture.task"})
public class TaskConfig {
}
