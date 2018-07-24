package com.weiwei.distributed.architecture.core.constants;

public interface SocialOAuth2Constant {

    interface QQOAuth2Constant{
        /***
         * 获取QQ端openId
         */
        public static final String URL_GET_OPENID = "https://graph.qq.com/oauth2.0/me?access_token=%s";

        /***
         * 获取QQ端用户信息
         */
        public static final String URL_GET_USERINFO = "https://graph.qq.com/user/get_user_info?oauth_consumer_key=%s&openid=%s";

        /***
         * 引导用户跳到该地址上，确认授权
         */
        public static final String URL_GET_AUTHORIZE = "https://graph.qq.com/oauth2.0/authorize";

        /***
         * 通过authorize_code获取access_token
         */
        public static final String URL_GET_ACCESS_TOKEN = "https://graph.qq.com/oauth2.0/token";
    }
}
