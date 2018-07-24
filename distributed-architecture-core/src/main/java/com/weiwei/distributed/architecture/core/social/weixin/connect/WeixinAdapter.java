package com.weiwei.distributed.architecture.core.social.weixin.connect;

import com.weiwei.distributed.architecture.core.social.weixin.api.WeiXin;
import com.weiwei.distributed.architecture.core.social.weixin.api.WeiXinUserInfo;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;

public class WeixinAdapter implements ApiAdapter<WeiXin> {

    private String openId;

    public WeixinAdapter() {}

    public WeixinAdapter(String openId){
        this.openId = openId;
    }

    @Override
    public boolean test(WeiXin weiXin) {
        return true;
    }

    @Override
    public void setConnectionValues(WeiXin api, ConnectionValues values) {
        WeiXinUserInfo profile = api.getUserInfo(openId);
        values.setProviderUserId(profile.getOpenid());
        values.setDisplayName(profile.getNickname());
        values.setImageUrl(profile.getHeadimgurl());
    }

    @Override
    public UserProfile fetchUserProfile(WeiXin weiXin) {
        return null;
    }

    @Override
    public void updateStatus(WeiXin weiXin, String s) {
        //do nothing
    }
}
