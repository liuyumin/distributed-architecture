package distributed.architecture.security.browser.authentication;

import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import distributed.architecture.security.browser.enums.LoginTypeEnum;
import distributed.architecture.security.browser.properties.BrowserSecurityProperties;
import distributed.architecture.security.browser.support.SimpleResponse;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/***
 * 登录失败类
 */
@Slf4j
@Component
public class LocalAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private BrowserSecurityProperties localSecurityProperties;

    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        log.info("-----------登录失败,根据定义的类型返回前端格式数据----LocalAuthenticationFailureHandler onAuthenticationFailure-----");
        if (StrUtil.equals(localSecurityProperties.getLoginType(),LoginTypeEnum.JSON.getLoginType()) ){
            httpServletResponse.setContentType("application/json;charset=utf-8");
            httpServletResponse.getWriter().write(objectMapper.writeValueAsString(new SimpleResponse(e.getMessage())));
        }else {
            super.onAuthenticationFailure(httpServletRequest,httpServletResponse,e);
        }

    }
}
