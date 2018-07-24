package com.weiwei.distributed.architecture.core.validate;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.weiwei.distributed.architecture.core.validate.code.ImageCode;
import com.weiwei.distributed.architecture.core.validate.code.ValidateCode;
import com.weiwei.distributed.architecture.core.validate.enums.ValidateCodeType;
import com.weiwei.distributed.architecture.core.validate.helper.ValidateCodeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import java.util.Map;

public abstract class AbstractValidateCodeProcessor<K extends ValidateCode> implements ValidateCodeProcessor {

    /**
     * 操作session的工具类
     */
    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    /**
     * 收集系统中所有的 {@link ValidateCodeGenerator} 接口的实现。
     */
    @Autowired
    private Map<String, ValidateCodeGenerator> validateCodeGenerators;

    /***
     * 采用模板设计模式
     * @param request
     * @throws Exception
     */
    @Override
    public void createValidateCode(ServletWebRequest request) throws Exception {
        //step1 生成验证码对象
        K validateCode = generate(request);

        //step2 验证码落地
        save(request,validateCode);

        //step3 验证发送
        send(request,validateCode);
    }

    /***
     * 验证码落地
     * @param request
     * @param validateCode
     */
    private void save(ServletWebRequest request, K validateCode){
        ValidateCode code = new ValidateCode(validateCode.getCode(),validateCode.getExpireTime());
        sessionStrategy.setAttribute(request,getSessionKey(request),code);
    }

    private String getSessionKey(ServletWebRequest request) {
        return SESSION_KEY_PREFIX + getValidateCodeType(request).toUpperCase();
    }

    /***
     * 验证发送，图片发送前端，手机验证码发送至手机端
     */
    protected abstract void send(ServletWebRequest request, K validateCode) throws Exception;

    /***
     * 生成验证码对象，由具体子类实现
     */
    private K generate(ServletWebRequest request){
        String type = getValidateCodeType(request);
        String generatorName = type + ValidateCodeGenerator.class.getSimpleName();
        ValidateCodeGenerator validateCodeGenerator = validateCodeGenerators.get(generatorName);
        if (validateCodeGenerator == null) {
            throw new ValidateCodeException("验证码生成器" + generatorName + "不存在");
        }
        return (K)validateCodeGenerator.generateCode(request);
    }

    private String getValidateCodeType(ServletWebRequest request) {
        String type = StrUtil.subBefore(getClass().getSimpleName(),"CodeProcessor",true);
        return ValidateCodeType.valuesOfUpperType(type.toUpperCase()).getCodeType();
    }

    @Override
    public void validate(ServletWebRequest request) {
        String codeType = getValidateCodeType(request);
        String sessionKey = getSessionKey(request);

        K codeInSession = (K) sessionStrategy.getAttribute(request, sessionKey);

        String codeInRequest;
        try {
            codeInRequest = ServletRequestUtils.getStringParameter(request.getRequest(), codeType);
        } catch (ServletRequestBindingException e) {
            throw new ValidateCodeException("获取验证码的值失败");
        }

        if (StrUtil.isBlank(codeInRequest)){
            throw new ValidateCodeException("验证码值不能为空");
        }

        if (ObjectUtil.isNull(codeInSession)){
            throw new ValidateCodeException("验证码不存在");
        }

        if (codeInSession.isExpire()){
            sessionStrategy.removeAttribute(request,sessionKey);
            throw new ValidateCodeException("验证码已过期");
        }

        if (!StrUtil.equals(codeInRequest,codeInSession.getCode())){
            throw new ValidateCodeException("验证码不匹配");
        }

        sessionStrategy.removeAttribute(request,sessionKey);
    }
}
