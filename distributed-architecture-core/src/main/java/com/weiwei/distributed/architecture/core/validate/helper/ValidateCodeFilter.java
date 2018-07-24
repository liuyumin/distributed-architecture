package com.weiwei.distributed.architecture.core.validate.helper;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.weiwei.distributed.architecture.core.constants.SecurityConstants;
import com.weiwei.distributed.architecture.core.properties.LocalSecurityProperties;
import com.weiwei.distributed.architecture.core.validate.ValidateCodeController;
import com.weiwei.distributed.architecture.core.validate.code.ImageCode;
import com.weiwei.distributed.architecture.core.validate.code.ValidateCode;
import com.weiwei.distributed.architecture.core.validate.enums.ValidateCodeType;
import com.weiwei.distributed.architecture.core.validate.helper.ValidateCodeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/****
 * 该接口主要用作为每个接口进行图片验证码校验
 */
@Slf4j
@Component("validateCodeFilter")
public class ValidateCodeFilter extends OncePerRequestFilter implements InitializingBean {

    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;

    @Autowired
    private LocalSecurityProperties localSecurityProperties;

    @Autowired
    private ValidateCodeProcessorHolder validateCodeProcessorHolder;

    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    private Map<String,ValidateCodeType> urlMap = new HashMap<>();

    @Override
    public void afterPropertiesSet() throws ServletException {
        super.afterPropertiesSet();

        urlMap.put(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM,ValidateCodeType.DEFAULT_PARAMETER_NAME_CODE_IMAGE);
        addUrlToMap(localSecurityProperties.getValidateCode().getImageCode().getUrl(),ValidateCodeType.DEFAULT_PARAMETER_NAME_CODE_IMAGE);

        urlMap.put(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_MOBILE,ValidateCodeType.DEFAULT_PARAMETER_NAME_CODE_SMS);
        addUrlToMap(localSecurityProperties.getValidateCode().getSmsCode().getUrl(),ValidateCodeType.DEFAULT_PARAMETER_NAME_CODE_SMS);

    }

    /***
     * 讲系统中配置的需要校验验证码的URL根据校验的类型放入map
     * @param urlString
     * @param type
     */
    protected void addUrlToMap(String urlString, ValidateCodeType type) {
        if (StrUtil.isNotBlank(urlString)){
            String[] configUrls = StrUtil.splitToArray(urlString,',');
            for (String configUrl : configUrls){
                urlMap.put(configUrl, type);
            }
        }
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

        log.info("------验证码验证请求----requestUrl=[{}]---method=[{}]----",httpServletRequest.getRequestURL(),httpServletRequest.getMethod());
        ValidateCodeType validateCodeType = getValidateCodeType(httpServletRequest);
        if (validateCodeType != null) {
            logger.info("校验请求(" + httpServletRequest.getRequestURI() + ")中的验证码,验证码类型" + httpServletRequest);
            try {
                validateCodeProcessorHolder.findValidateCodeProcessor(validateCodeType.getCodeType())
                        .validate(new ServletWebRequest(httpServletRequest, httpServletResponse));
                logger.info("验证码校验通过");
            } catch (ValidateCodeException exception) {
                authenticationFailureHandler.onAuthenticationFailure(httpServletRequest, httpServletResponse, exception);
                return;
            }
        }

        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }

    private ValidateCodeType getValidateCodeType(HttpServletRequest httpServletRequest) {
        ValidateCodeType validateCodeType = null;
        System.out.println(String.valueOf(httpServletRequest.getMethod()));
//        if (!StrUtil.equalsIgnoreCase(httpServletRequest.getMethod(),"get")){
            Set<String> urls = urlMap.keySet();
            for (String url : urls){
                if (antPathMatcher.match(url,httpServletRequest.getRequestURI())){
                    validateCodeType = urlMap.get(url);
                }
            }
//        }
        return validateCodeType;
    }
}
