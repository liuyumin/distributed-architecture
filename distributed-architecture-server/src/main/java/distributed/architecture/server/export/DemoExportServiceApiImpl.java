package distributed.architecture.server.export;

import com.weiwei.distributed.architecture.api.export.DemoExportServiceApi;
import org.springframework.stereotype.Service;

@Service
public class DemoExportServiceApiImpl implements DemoExportServiceApi {
    @Override
    public String sayHello(String name) {
        return "Hello "+name;
    }
}
