package com.weiwei.distributed.architecture.core.social.qq.connect;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2Template;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;

@Slf4j
public class QQOAuth2Template extends OAuth2Template {

    public QQOAuth2Template(String clientId, String clientSecret, String authorizeUrl, String accessTokenUrl) {
        super(clientId, clientSecret, authorizeUrl, accessTokenUrl);
        setUseParametersForClientAuthentication(true);
    }

    @Override
    protected RestTemplate createRestTemplate() {
        RestTemplate restTemplate = super.createRestTemplate();
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter(Charset.forName("utf-8")));
        return restTemplate;
    }

    @Override
    protected AccessGrant postForAccessGrant(String accessTokenUrl, MultiValueMap<String, String> parameters) {
        String responseToken = getRestTemplate().postForObject(accessTokenUrl,parameters,String.class);
        log.info("------QQOAuth2Template postForAccessGrant get token result=[{}]",responseToken);

        String[] items = StrUtil.splitToArray(responseToken,'&');

        //access_token=FE04************************CCE2&expires_in=7776000&refresh_token=88E4************************BE14
        String accessToken = StrUtil.subAfter(items[0], "=", true);
        Long expiresIn = new Long(StrUtil.subAfter(items[1], "=", true));
        String refreshToken = StrUtil.subAfter(items[2], "=", true);

        return new AccessGrant(accessToken,null,refreshToken,expiresIn);
    }
}
