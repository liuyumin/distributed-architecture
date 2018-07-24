package com.weiwei.distributed.architecture.core.social.qq.api.impl;

import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.weiwei.distributed.architecture.core.constants.SocialOAuth2Constant;
import com.weiwei.distributed.architecture.core.social.qq.api.QQ;
import com.weiwei.distributed.architecture.core.social.qq.api.QQUserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;

import java.io.IOException;

@Slf4j
public class QQImpl extends AbstractOAuth2ApiBinding implements QQ {

    /***
     * 在qq端，一个用户的唯一标识，可通过accessToken获取
     */
    private String openId;

    /***
     * 某个APP在qq平台唯一标识，通过qq官网申请获得appId编号
     */
    private String appId;

    private ObjectMapper objectMapper = new ObjectMapper();

    public QQImpl(String accessToken,String appId){
        super(accessToken,TokenStrategy.ACCESS_TOKEN_PARAMETER);
        this.appId = appId;

        String url = String.format(SocialOAuth2Constant.QQOAuth2Constant.URL_GET_OPENID,accessToken);
        String result = getRestTemplate().getForObject(url,String.class);
        log.info("返回的openId结果：[{}]",result);

        String openId = StrUtil.subBetween(result,"\"openid\":\"","\"}");
        log.info("返回处理之后的openId结果：[{}]",result);
        this.openId = openId;
    }

    @Override
    public QQUserInfo getUserInfo() {
        String url = String.format(SocialOAuth2Constant.QQOAuth2Constant.URL_GET_USERINFO,appId,openId);
        String result = getRestTemplate().getForObject(url,String.class);
        log.info("返回的openId结果：[{}]",result);

        QQUserInfo qqUserInfo = null;
        try {
            qqUserInfo = objectMapper.readValue(result,QQUserInfo.class);
            qqUserInfo.setOpenId(openId);
        } catch (Exception e) {
            throw new RuntimeException("String to Object meet exception");
        }

        return qqUserInfo;
    }
}
