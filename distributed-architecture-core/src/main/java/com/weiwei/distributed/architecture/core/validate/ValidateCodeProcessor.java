package com.weiwei.distributed.architecture.core.validate;

import org.springframework.web.context.request.ServletWebRequest;

public interface ValidateCodeProcessor {

    /***
     * 验证码放入session时的前缀
     */
    String SESSION_KEY_PREFIX = "SESSION_KEY_FOR_CODE_";

    /**
     * 创建验证码
     */
    void createValidateCode(ServletWebRequest request) throws Exception;

    /***
     * 校验code码
     * @param request
     */
    void validate(ServletWebRequest request);
}
