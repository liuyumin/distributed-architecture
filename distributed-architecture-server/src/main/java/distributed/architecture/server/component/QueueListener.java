package distributed.architecture.server.component;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Component
@Slf4j
public class QueueListener implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private MockQueue mockQueue;

    @Autowired
    private DeferredResultHolder deferredResultHolder;

    private Executor executor = Executors.newFixedThreadPool(1);

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                while (true){
                    if (StrUtil.isNotBlank(mockQueue.getCompleteOrder())){
                        String orderSerinalNumber = mockQueue.getCompleteOrder();
                        log.info("------------返回订单处理--------,{}",orderSerinalNumber);
                        DeferredResult<String> deferredResult = deferredResultHolder.getMap().get(orderSerinalNumber);
                        deferredResult.setResult("place order success");
                        mockQueue.setCompleteOrder(null);
                    }else {
                        try {
                            Thread.sleep(100);
                        }catch (Exception ex){

                        }
                    }
                }
            }
        });

    }
}
