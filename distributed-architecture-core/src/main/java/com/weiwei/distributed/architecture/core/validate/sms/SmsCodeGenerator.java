package com.weiwei.distributed.architecture.core.validate.sms;

import cn.hutool.core.util.RandomUtil;
import com.weiwei.distributed.architecture.core.properties.LocalSecurityProperties;
import com.weiwei.distributed.architecture.core.validate.AbstractValidateCodeProcessor;
import com.weiwei.distributed.architecture.core.validate.ValidateCodeGenerator;
import com.weiwei.distributed.architecture.core.validate.code.ValidateCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

@Slf4j
@Component("smsCodeValidateCodeGenerator")
public class SmsCodeGenerator implements ValidateCodeGenerator {

    @Autowired
    private LocalSecurityProperties securityProperties;

    public ValidateCode generateCode(ServletWebRequest request) {
        String smsCode = RandomUtil.randomNumbers(securityProperties.getValidateCode().getSmsCode().getLength());
        return new ValidateCode(smsCode,securityProperties.getValidateCode().getSmsCode().getExpireIn());
    }
}
