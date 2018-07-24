package distributed.architecture.server.component;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Slf4j
@Component
public class MockQueue implements Serializable {

    private static final long serialVersionUID = -7844168927270210859L;

    private String placeOrder;

    private String completeOrder;

    public String getPlaceOrder() {
        return placeOrder;
    }

    public void setPlaceOrder(String placeOrder) throws Exception {
        new Thread(()->{
            log.info("--------------接到下单请求------------------,{}",placeOrder);
            try {
                Thread.sleep(10000 );
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.completeOrder = placeOrder;
            log.info("--------------下单请求处理完毕------------------{}",placeOrder);
        });

    }

    public String getCompleteOrder() {
        return completeOrder;
    }

    public void setCompleteOrder(String completeOrder) {
        this.completeOrder = completeOrder;
    }
}
