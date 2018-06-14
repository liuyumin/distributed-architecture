package com.weiwei.distributed.architecture.server.distributedarchitectureserver;

import org.eclipse.jetty.util.thread.QueuedThreadPool;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.jetty.JettyServerCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
public class DistributedArchitectureServerApplication {

    public static void main(String[] args) {
//        JettyEmbeddedServletContainerFactory jettyEmbeddedServletContainerFactory;
        SpringApplication.run(DistributedArchitectureServerApplication.class, args);
    }

//    @Profile("jetty")
//    @Bean
//    public JettyEmbeddedServletContainerFactory jettyEmbeddedServletContainerFactory(JettyServerCustomizer jettyServerCustomizer) {
//        JettyEmbeddedServletContainerFactory factory = new JettyEmbeddedServletContainerFactory();
//        factory.addServerCustomizers(jettyServerCustomizer);
//        return factory;
//    }
//
//
//    @Bean
//    public JettyServerCustomizer jettyServerCustomizer() {
//        return server -> {
//            // Tweak the connection config used by Jetty to handle incoming HTTP
//            // connections
//            final QueuedThreadPool threadPool = server.getBean(QueuedThreadPool.class);
//            threadPool.setMaxThreads(100);
//            threadPool.setMinThreads(20);
//        };
//    }
}
