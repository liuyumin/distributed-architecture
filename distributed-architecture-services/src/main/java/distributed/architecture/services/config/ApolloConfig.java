package distributed.architecture.services.config;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.springframework.context.annotation.Configuration;

@Configuration
//指示Apollo注入dubbo_config、redis、application、datasource_config  namespace的配置到Spring环境中
@EnableApolloConfig({"dubbo_config", "redis", "application", "datasource_config"})
public class ApolloConfig {

}
