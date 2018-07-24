package com.weiwei.distributed.architecture.core.social.weixin.connect;

import org.springframework.social.oauth2.AccessGrant;

public class WeiXinAccessGrant extends AccessGrant {

    private static final long serialVersionUID = 3011602657196403756L;

    private String openId;

    public WeiXinAccessGrant(String accessToken) {
        super("");
    }

    public WeiXinAccessGrant(String accessToken, String scope, String refreshToken, Long expiresIn) {
        super(accessToken, scope, refreshToken, expiresIn);
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }
}
