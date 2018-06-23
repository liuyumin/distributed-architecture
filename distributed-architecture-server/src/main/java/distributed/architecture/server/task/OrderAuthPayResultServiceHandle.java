package distributed.architecture.server.task;

import com.weiwei.distributed.architecture.api.task.DemoServiceJob;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import org.springframework.stereotype.Service;

@JobHandler(value = "OrderAuthPayResultServiceHandle")
@Service
public class OrderAuthPayResultServiceHandle extends IJobHandler implements DemoServiceJob {
    @Override
    public String executeJob(String... strings) throws Exception {
        return "hello xxl job !!!";
    }

    @Override
    public ReturnT<String> execute(String s) throws Exception {
        return ReturnT.SUCCESS;
    }
}
