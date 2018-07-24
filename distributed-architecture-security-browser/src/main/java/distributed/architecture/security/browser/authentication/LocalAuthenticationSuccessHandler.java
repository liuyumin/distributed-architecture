package distributed.architecture.security.browser.authentication;

import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import distributed.architecture.security.browser.enums.LoginTypeEnum;
import distributed.architecture.security.browser.properties.BrowserSecurityProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/***
 * 该类主要实现登录成功之后，可以进行签到，积分奖励等后续功能
 */
@Slf4j
@Component
public class LocalAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private BrowserSecurityProperties localSecurityProperties;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        log.info("-----------登录成功,根据定义的类型返回前端格式数据-----LocalAuthenticationSuccessHandler onAuthenticationSuccess-----");
        if (StrUtil.equals(localSecurityProperties.getLoginType(),LoginTypeEnum.JSON.getLoginType())){
            httpServletResponse.setContentType("application/json;charset=utf-8");
            httpServletResponse.getWriter().write(objectMapper.writeValueAsString(authentication));
        }else {
            super.onAuthenticationSuccess(httpServletRequest,httpServletResponse,authentication);
        }

    }
}
