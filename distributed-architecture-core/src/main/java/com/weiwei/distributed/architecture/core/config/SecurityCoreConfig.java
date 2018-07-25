package com.weiwei.distributed.architecture.core.config;

import com.weiwei.distributed.architecture.core.social.SocialConfig;
import com.weiwei.distributed.architecture.core.social.qq.config.QQAutoConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@ComponentScan(basePackages = {"com.weiwei.distributed.architecture.core"})
@Import({QQAutoConfig.class,SocialConfig.class})
public class SecurityCoreConfig {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
