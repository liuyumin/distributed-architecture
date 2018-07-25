package com.weiwei.distributed.architecture.core.enums;

import cn.hutool.core.util.StrUtil;

public enum LoginTypeEnum {

    REDIRECT("redirect","重定向格式"),
    JSON("json","json格式");

    private String loginType;
    private String loginTypeDesc;

    LoginTypeEnum(String loginType,String loginTypeDesc){
        this.loginType = loginType;
        this.loginTypeDesc = loginTypeDesc;
    }

    public static LoginTypeEnum valuesOf(String loginType){
        for (LoginTypeEnum typeEnum : values()){
            if (StrUtil.equals(typeEnum.getLoginType(),loginType)){
                return typeEnum;
            }
        }
        return null;
//        throw new Exception("This enum is not exist,["+loginType+"]");
    }

    public String getLoginType() {
        return loginType;
    }

    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }

    public String getLoginTypeDesc() {
        return loginTypeDesc;
    }

    public void setLoginTypeDesc(String loginTypeDesc) {
        this.loginTypeDesc = loginTypeDesc;
    }
}
