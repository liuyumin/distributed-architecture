package com.weiwei.distributed.architecture.core.social.weixin.api.impl;

import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.weiwei.distributed.architecture.core.social.weixin.api.WeiXin;
import com.weiwei.distributed.architecture.core.social.weixin.api.WeiXinUserInfo;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;

import java.nio.charset.Charset;
import java.util.List;

public class WeiXinImpl extends AbstractOAuth2ApiBinding implements WeiXin {

    private ObjectMapper objectMapper = new ObjectMapper();

    private static final String WEIXIN_GET_USER_INFO_URL = "https://api.weixin.qq.com/sns/userinfo?openid=";

    public WeiXinImpl(String accessToken){
        super(accessToken, TokenStrategy.ACCESS_TOKEN_PARAMETER);
    }

    @Override
    protected List<HttpMessageConverter<?>> getMessageConverters() {
        List<HttpMessageConverter<?>> messageConverters = super.getMessageConverters();
        messageConverters.remove(0);
        messageConverters.add(new StringHttpMessageConverter(Charset.forName("UTF-8")));
        return messageConverters;
    }

    @Override
    public WeiXinUserInfo getUserInfo(String openId) {
        String url = WEIXIN_GET_USER_INFO_URL + openId;
        String response = getRestTemplate().getForObject(url, String.class);
        if(StrUtil.containsAny(response, "errcode")) {
            return null;
        }
        WeiXinUserInfo profile = null;
        try {
            profile = objectMapper.readValue(response, WeiXinUserInfo.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return profile;
    }
}
