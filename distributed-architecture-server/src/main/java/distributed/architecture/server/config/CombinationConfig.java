package distributed.architecture.server.config;

import com.weiwei.distributed.architecture.task.config.TaskConfig;
import distributed.architecture.services.config.ServicesConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ServerConfig.class,ServicesConfig.class,TaskConfig.class})
public class CombinationConfig {
}
