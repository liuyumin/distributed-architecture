package com.weiwei.distributed.architecture.core.social.qq.connect;

import com.weiwei.distributed.architecture.core.social.qq.api.QQ;
import com.weiwei.distributed.architecture.core.social.qq.api.impl.QQImpl;
import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;

import static com.weiwei.distributed.architecture.core.constants.SocialOAuth2Constant.QQOAuth2Constant.URL_GET_ACCESS_TOKEN;
import static com.weiwei.distributed.architecture.core.constants.SocialOAuth2Constant.QQOAuth2Constant.URL_GET_AUTHORIZE;

public class QQServiceProvider extends AbstractOAuth2ServiceProvider<QQ> {

    private String appId;

    public QQServiceProvider(String appId, String appSecret) {
        super(new QQOAuth2Template(appId, appSecret, URL_GET_AUTHORIZE, URL_GET_ACCESS_TOKEN));
        this.appId = appId;
    }

    @Override
    public QQ getApi(String accessToken) {
        return new QQImpl(accessToken,appId);
    }
}
