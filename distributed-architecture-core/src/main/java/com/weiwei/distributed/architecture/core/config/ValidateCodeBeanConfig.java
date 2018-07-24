package com.weiwei.distributed.architecture.core.config;

import com.weiwei.distributed.architecture.core.properties.LocalSecurityProperties;
import com.weiwei.distributed.architecture.core.validate.ValidateCodeGenerator;
import com.weiwei.distributed.architecture.core.validate.image.ImageCodeGenerator;
import com.weiwei.distributed.architecture.core.validate.sms.DefaultSmsCodeSender;
import com.weiwei.distributed.architecture.core.validate.sms.SmsCodeSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.weiwei.distributed.architecture.core"})
public class ValidateCodeBeanConfig {

    @Autowired
    private LocalSecurityProperties securityProperties;

    @Bean
    @ConditionalOnMissingBean(name = "imageValidateCodeGenerator")
    public ValidateCodeGenerator imageValidateCodeGenerator(){
        ImageCodeGenerator imageCodeGenerator = new ImageCodeGenerator();
        imageCodeGenerator.setSecurityProperties(securityProperties);
        return imageCodeGenerator;
    }

    @Bean
    @ConditionalOnMissingBean(SmsCodeSender.class)
    public SmsCodeSender smsCodeSender(){
        DefaultSmsCodeSender defaultSmsCodeSender = new DefaultSmsCodeSender();
        return defaultSmsCodeSender;
    }
}
