package com.weiwei.distributed.architecture.core.social.weixin.config;

import com.weiwei.distributed.architecture.core.properties.WeiXinSocialProperties;
import com.weiwei.distributed.architecture.core.social.LocalConnectView;
import com.weiwei.distributed.architecture.core.social.weixin.connect.WeixinConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.ConnectionFactory;
import org.springframework.web.servlet.View;

@Configuration
public class WeiXinAutoConfig extends SocialAutoConfigurerAdapter {

    @Autowired
    private WeiXinSocialProperties weiXinSocialProperties;

    @Override
    protected ConnectionFactory<?> createConnectionFactory() {

        return new WeixinConnectionFactory(weiXinSocialProperties.getProviderId()
                ,weiXinSocialProperties.getAppId(),weiXinSocialProperties.getAppIdSecret());
    }

    @Bean({"connect/weixinConnect", "connect/weixinConnected"})
    @ConditionalOnMissingBean(name = "weixinConnectedView")
    public View weixinConnectedView(){
        return new LocalConnectView();
    }
}
