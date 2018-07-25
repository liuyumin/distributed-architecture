package com.weiwei.distributed.architecture.core.properties;

import cn.hutool.core.util.StrUtil;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Setter
@Getter
@Component
public class BrowserProperties {

    private String signOutUrl;

    @Value(value = "${distributed.architecture.server.login.page}")
    private String loginPage;

    private String customPage;

    @Value(value = "${distributed.architecture.server.login.type}")
    private String loginType;

    @Value(value = "${distributed.architecture.server.signUpUrl}")
    private String signUpUrl;

    private int rememberMeSecond = 3600;

    private Integer attempts = 5;

    private Integer attemptsWaitTime = 15;

    private Integer loginPoints = 2;

    private Integer pageSize = 20;

    public String getCustomPage(){
        return StrUtil.isNotBlank(loginPage) ? loginPage : "/custom_login.html";
    }

    public int getRememberMeSecond() {
        return rememberMeSecond;
    }

    public void setRememberMeSecond(int rememberMeSecond) {
        this.rememberMeSecond = rememberMeSecond;
    }


}
