package com.weiwei.distributed.architecture.api.task;

public interface DemoServiceJob {

    public String executeJob(String... strings) throws Exception;
}
