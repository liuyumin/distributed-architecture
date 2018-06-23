package distributed.architecture.server.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = {"distributed.architecture.server"})
@Import(JettyConfig.class)
public class ServerConfig {
}
