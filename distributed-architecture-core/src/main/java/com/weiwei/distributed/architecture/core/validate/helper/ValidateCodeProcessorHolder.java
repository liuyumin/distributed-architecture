package com.weiwei.distributed.architecture.core.validate.helper;

import com.weiwei.distributed.architecture.core.validate.ValidateCodeProcessor;
import com.weiwei.distributed.architecture.core.validate.enums.ValidateCodeType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ValidateCodeProcessorHolder {

    @Autowired
    private Map<String, ValidateCodeProcessor> validateCodeProcessors;

    public ValidateCodeProcessor findValidateCodeProcessor(String type) {
        String paramName = ValidateCodeType.valuesOf(type).getCodeType();
        String generatorName = paramName + ValidateCodeProcessor.class.getSimpleName();
        ValidateCodeProcessor validateCodeProcessor = validateCodeProcessors.get(generatorName);
        if (null == validateCodeProcessor){
            throw new ValidateCodeException("验证码处理器" + generatorName + "不存在");
        }
        return validateCodeProcessor;
    }
}
