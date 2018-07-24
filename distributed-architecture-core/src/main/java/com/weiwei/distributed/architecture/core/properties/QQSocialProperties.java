package com.weiwei.distributed.architecture.core.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Setter
@Getter
@Component
public class QQSocialProperties {

    @Value("${security.qq.social.appId}")
    private String appId;

    @Value("${security.qq.social.appIdSecret}")
    private String appIdSecret;

    /***
     * 提供商唯一标识
     */
    @Value("${security.qq.social.providerId}")
    private String providerId;

    /***
     *
     */
    @Value("${security.qq.social.filterProcessesUrl}")
    private String filterProcessesUrl;
}
