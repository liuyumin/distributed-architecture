package com.weiwei.distributed.architecture.core.validate.sms;

/***
 * mock第三方短信发送
 */
public interface SmsCodeSender {

    void smsSend(String mobile,String smsCode);
}
