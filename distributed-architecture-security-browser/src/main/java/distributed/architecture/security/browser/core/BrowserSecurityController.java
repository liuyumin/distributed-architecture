package distributed.architecture.security.browser.core;

import cn.hutool.core.util.StrUtil;
import com.weiwei.distributed.architecture.core.constants.SecurityConstants;
import com.weiwei.distributed.architecture.core.properties.BrowserProperties;
import com.weiwei.distributed.architecture.core.support.SimpleResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@RestController
public class BrowserSecurityController {

    private RequestCache requestCache = new HttpSessionRequestCache();

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Autowired
    private BrowserProperties browserProperties;

    @Autowired
    private ProviderSignInUtils providerSignInUtils;

    /***
     * 当需要身份认证时，跳转到这里,可以通过数据库控制那些角色，应该开放那些功能
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = SecurityConstants.DEFAULT_UNAUTHENTICATION_URL)
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    public SimpleResponse requireAuthentication(HttpServletRequest request, HttpServletResponse response) throws Exception{
        SavedRequest savedRequest = requestCache.getRequest(request,response);
        if (null != savedRequest){
            String targetUrl = savedRequest.getRedirectUrl();
            log.info("-------BrowserSecurityController requireAuthentication-----引发跳转的请求是:=[{}]",savedRequest.getRedirectUrl());
            if (StrUtil.endWithIgnoreCase(targetUrl,".html")){
                redirectStrategy.sendRedirect(request,response, browserProperties.getCustomPage());
            }
        }

        return new SimpleResponse("访问的服务需要身份认证，请引导用户到登录页面");
    }

//    @GetMapping("/social/user")
//    public SocialUserInfo getSocialUserInfo(HttpServletRequest request){
//        SocialUserInfo socialUserInfo = new SocialUserInfo();
//        Connection<?> connection = providerSignInUtils.getConnectionFromSession(new ServletWebRequest(request));
//        socialUserInfo.setProviderId(connection.getKey().getProviderId());
//        socialUserInfo.setProviderUserId(connection.getKey().getProviderUserId());
//        socialUserInfo.setHeadimg(connection.getImageUrl());
//        socialUserInfo.setNickname(connection.getDisplayName());
//        return socialUserInfo;
//    }

    @GetMapping("/session/invalid")
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    public SimpleResponse sessionInvalid(){
        String message = "session失效";
        return new SimpleResponse(message);
    }
}
