package com.weiwei.distributed.architecture.server.distributedarchitectureserver;

import org.eclipse.jetty.util.thread.QueuedThreadPool;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.jetty.JettyEmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.jetty.JettyServerCustomizer;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DistributedArchitectureServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(DistributedArchitectureServerApplication.class, args);
    }

    @Bean
    public JettyEmbeddedServletContainerFactory jettyEmbeddedServletContainerFactory(JettyServerCustomizer jettyServerCustomizer) {
        JettyEmbeddedServletContainerFactory jettyEmbeddedServletContainerFactory = new JettyEmbeddedServletContainerFactory();
        jettyEmbeddedServletContainerFactory.addServerCustomizers(jettyServerCustomizer);
        jettyEmbeddedServletContainerFactory.setPort(9890);
        jettyEmbeddedServletContainerFactory.setContextPath("/home");

//        http://www.xuxueli.com/xxl-job/#/?id=_11-%E6%A6%82%E8%BF%B0?id=%E3%80%8A%E5%88%86%E5%B8%83%E5%BC%8F%E4%BB%BB%E5%8A%A1%E8%B0%83%E5%BA%A6%E5%B9%B3%E5%8F%B0xxl-job%E3%80%8B
//
//
//        http://www.herohuang.com/2017/06/04/hello-apollo/


        return jettyEmbeddedServletContainerFactory;
    }


    @Bean
    public JettyServerCustomizer jettyServerCustomizer() {
        return server -> {
            final QueuedThreadPool threadPool = server.getBean(QueuedThreadPool.class);
            threadPool.setMaxThreads(100);
            threadPool.setMinThreads(20);
        };
    }
}
