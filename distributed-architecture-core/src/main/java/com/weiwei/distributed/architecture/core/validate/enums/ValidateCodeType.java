package com.weiwei.distributed.architecture.core.validate.enums;

import cn.hutool.core.util.StrUtil;

public enum ValidateCodeType {

    DEFAULT_PARAMETER_NAME_CODE_SMS("smsCode","SMS","短信验证码"),
    DEFAULT_PARAMETER_NAME_CODE_IMAGE("imageCode","IMAGE","图片验证码");

    private String codeType;
    private String codeUpperType;
    private String codeTypeDesc;

    ValidateCodeType(String codeType,String codeUpperType, String codeTypeDesc){
        this.codeType = codeType;
        this.codeUpperType = codeUpperType;
        this.codeTypeDesc = codeTypeDesc;
    }

    public static ValidateCodeType valuesOfUpperType(String codeUpperType) {
        for (ValidateCodeType validateCodeType : ValidateCodeType.values()){
            if (StrUtil.equals(validateCodeType.getCodeUpperType(),codeUpperType)){
                return validateCodeType;
            }
        }
        throw new IllegalArgumentException("enum is not exist,codeType:=["+codeUpperType+"]");
    }


    public static ValidateCodeType valuesOf(String codeType) {
        for (ValidateCodeType validateCodeType : ValidateCodeType.values()){
            if (StrUtil.equals(validateCodeType.getCodeType(),codeType)){
                return validateCodeType;
            }
        }
        throw new IllegalArgumentException("enum is not exist,codeType:=["+codeType+"]");
    }

    public String getCodeType() {
        return codeType;
    }

    public void setCodeType(String codeType) {
        this.codeType = codeType;
    }

    public String getCodeTypeDesc() {
        return codeTypeDesc;
    }

    public void setCodeTypeDesc(String codeTypeDesc) {
        this.codeTypeDesc = codeTypeDesc;
    }

    public String getCodeUpperType() {
        return codeUpperType;
    }

    public void setCodeUpperType(String codeUpperType) {
        this.codeUpperType = codeUpperType;
    }
}
