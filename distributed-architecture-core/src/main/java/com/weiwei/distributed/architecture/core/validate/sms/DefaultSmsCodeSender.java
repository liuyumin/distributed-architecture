package com.weiwei.distributed.architecture.core.validate.sms;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DefaultSmsCodeSender implements SmsCodeSender {
    @Override
    public void smsSend(String mobile, String smsCode) {
        log.info("向手机[{}]发送短信验证码[{}]",mobile,smsCode);
    }
}
