package com.weiwei.distributed.architecture.core.validate.sms;

import com.weiwei.distributed.architecture.core.constants.SecurityConstants;
import com.weiwei.distributed.architecture.core.validate.AbstractValidateCodeProcessor;
import com.weiwei.distributed.architecture.core.validate.code.ValidateCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

@Service("smsCodeValidateCodeProcessor")
public class SmsCodeProcessor extends AbstractValidateCodeProcessor<ValidateCode> {

    @Autowired
    private SmsCodeSender smsCodeSender;

    @Override
    protected void send(ServletWebRequest request, ValidateCode validateCode) throws Exception {
        String paramName = SecurityConstants.DEFAULT_PARAMETER_NAME_MOBILE;
        String mobile = ServletRequestUtils.getRequiredStringParameter(request.getRequest(),paramName);
        smsCodeSender.smsSend(mobile,validateCode.getCode());
    }
}
