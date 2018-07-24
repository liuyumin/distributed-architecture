package com.weiwei.distributed.architecture.core.social.qq.config;

import com.weiwei.distributed.architecture.core.properties.QQSocialProperties;
import com.weiwei.distributed.architecture.core.social.qq.connect.QQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.ConnectionFactory;

@Configuration
public class QQAutoConfig extends SocialAutoConfigurerAdapter {

    @Autowired
    private QQSocialProperties qqSocialProperties;

    @Override
    protected ConnectionFactory<?> createConnectionFactory() {
        return new QQConnectionFactory(qqSocialProperties.getProviderId()
                , qqSocialProperties.getAppId()
                ,qqSocialProperties.getAppIdSecret());
    }
}
