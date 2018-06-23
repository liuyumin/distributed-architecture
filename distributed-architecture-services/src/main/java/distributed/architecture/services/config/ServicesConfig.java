package distributed.architecture.services.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Import(DbConfig.class)
@Configuration
@ComponentScan(basePackages = {"distributed.architecture.services"})
@MapperScan("distributed.architecture.services.mapper")
public class ServicesConfig {

}
