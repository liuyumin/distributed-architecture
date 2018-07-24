package com.weiwei.distributed.architecture.core.validate;

import com.weiwei.distributed.architecture.core.constants.SecurityConstants;
import com.weiwei.distributed.architecture.core.validate.code.ImageCode;
import com.weiwei.distributed.architecture.core.validate.code.ValidateCode;
import com.weiwei.distributed.architecture.core.validate.helper.ValidateCodeProcessorHolder;
import com.weiwei.distributed.architecture.core.validate.sms.SmsCodeGenerator;
import com.weiwei.distributed.architecture.core.validate.sms.SmsCodeSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@RestController
public class ValidateCodeController {

    @Autowired
    private ValidateCodeProcessorHolder validateCodeProcessorHolder;

    @GetMapping(SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX + "/{type}")
    public void createCode(HttpServletRequest request, HttpServletResponse response,@PathVariable String type) throws Exception{
        validateCodeProcessorHolder.findValidateCodeProcessor(type).createValidateCode(new ServletWebRequest(request,response));
    }
}
