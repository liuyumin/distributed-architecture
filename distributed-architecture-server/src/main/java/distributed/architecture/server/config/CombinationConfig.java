package distributed.architecture.server.config;

import com.weiwei.distributed.architecture.config.SecurityAppConfig;
import com.weiwei.distributed.architecture.core.config.SecurityCoreConfig;
import com.weiwei.distributed.architecture.task.config.TaskConfig;
import distributed.architecture.services.config.ServicesConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = {"distributed.architecture.server"})
@Import({ServerConfig.class,ServicesConfig.class,
        TaskConfig.class,SecurityCoreConfig.class,
        SecurityAppConfig.class})
public class CombinationConfig {
}
