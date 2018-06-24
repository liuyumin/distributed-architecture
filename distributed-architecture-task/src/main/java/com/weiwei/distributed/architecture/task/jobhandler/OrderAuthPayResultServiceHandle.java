package com.weiwei.distributed.architecture.task.jobhandler;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@JobHandler(value = "demoJobHandler")
@Service
public class OrderAuthPayResultServiceHandle extends IJobHandler {
    private static final Logger logger = LoggerFactory.getLogger(OrderAuthPayResultServiceHandle.class);

    @Override
    public ReturnT<String> execute(String s) throws Exception {
        logger.info("wangww test task job>>>>>>>>>>>>>>>>>>>>>>>>>>");
        return ReturnT.SUCCESS;
    }
}
