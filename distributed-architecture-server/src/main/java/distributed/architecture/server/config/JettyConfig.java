package distributed.architecture.server.config;

import org.eclipse.jetty.util.thread.QueuedThreadPool;
import org.springframework.boot.context.embedded.jetty.JettyEmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.jetty.JettyServerCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/***
 * 添加Jetty容器配置
 */

public class JettyConfig {

//    @Bean
//    public JettyEmbeddedServletContainerFactory jettyEmbeddedServletContainerFactory(JettyServerCustomizer jettyServerCustomizer) {
//        JettyEmbeddedServletContainerFactory jettyEmbeddedServletContainerFactory = new JettyEmbeddedServletContainerFactory();
//        jettyEmbeddedServletContainerFactory.addServerCustomizers(jettyServerCustomizer);
//        jettyEmbeddedServletContainerFactory.setPort(9998);
//        jettyEmbeddedServletContainerFactory.setContextPath("/home");
//
//
//        return jettyEmbeddedServletContainerFactory;
//    }
//
//
//    @Bean
//    public JettyServerCustomizer jettyServerCustomizer() {
//        return server -> {
//            final QueuedThreadPool threadPool = server.getBean(QueuedThreadPool.class);
//            threadPool.setMaxThreads(100);
//            threadPool.setMinThreads(20);
//        };
//    }
}
