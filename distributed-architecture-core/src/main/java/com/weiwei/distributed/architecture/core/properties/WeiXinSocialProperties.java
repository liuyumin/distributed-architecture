package com.weiwei.distributed.architecture.core.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Setter
@Getter
@Component
public class WeiXinSocialProperties {

    @Value("${security.weixin.social.appId}")
    private String appId;

    @Value("${security.weixin.social.appIdSecret}")
    private String appIdSecret;

    /***
     * 提供商唯一标识
     */
    @Value("${security.weixin.social.providerId}")
    private String providerId;
}
