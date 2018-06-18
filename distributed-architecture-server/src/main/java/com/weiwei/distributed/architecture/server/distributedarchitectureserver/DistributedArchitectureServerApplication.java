package com.weiwei.distributed.architecture.server.distributedarchitectureserver;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.eclipse.jetty.util.thread.QueuedThreadPool;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.jetty.JettyEmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.jetty.JettyServerCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@EnableApolloConfig
@EnableAutoConfiguration
@SpringBootApplication
public class DistributedArchitectureServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(DistributedArchitectureServerApplication.class, args);
    }

    @Bean
    public TestJavaConfigBean javaConfigBean() {
        TestJavaConfigBean testJavaConfigBean = new TestJavaConfigBean();
        System.out.println("aaaaaaa=="+testJavaConfigBean.name);
        return testJavaConfigBean;
    }



    @Bean
    public JettyEmbeddedServletContainerFactory jettyEmbeddedServletContainerFactory(JettyServerCustomizer jettyServerCustomizer) {
        JettyEmbeddedServletContainerFactory jettyEmbeddedServletContainerFactory = new JettyEmbeddedServletContainerFactory();
        jettyEmbeddedServletContainerFactory.addServerCustomizers(jettyServerCustomizer);
        jettyEmbeddedServletContainerFactory.setPort(9998);
        jettyEmbeddedServletContainerFactory.setContextPath("/home");


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
