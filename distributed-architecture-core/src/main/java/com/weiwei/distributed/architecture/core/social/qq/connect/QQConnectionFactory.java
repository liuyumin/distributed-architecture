package com.weiwei.distributed.architecture.core.social.qq.connect;

import com.weiwei.distributed.architecture.core.social.qq.api.QQ;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;

public class QQConnectionFactory extends OAuth2ConnectionFactory<QQ> {

    public QQConnectionFactory(String providerId, String appId,String appScret) {
        super(providerId, new QQServiceProvider(appId,appScret), new QQAdapter());
    }
}
