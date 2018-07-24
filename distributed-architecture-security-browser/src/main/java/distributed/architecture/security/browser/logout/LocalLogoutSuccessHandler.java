package distributed.architecture.security.browser.logout;

import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LocalLogoutSuccessHandler implements LogoutSuccessHandler {

    private static final Logger logger = LoggerFactory.getLogger(LocalLogoutSuccessHandler.class);

    private String logOutUrl;

    private ObjectMapper objectMapper = new ObjectMapper();

    public LocalLogoutSuccessHandler(String logoutUrl){
        this.logOutUrl = logoutUrl;
    }

    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        logger.info("退出成功");

        if (StrUtil.isBlank(logOutUrl)){
            httpServletResponse.setContentType("application/json;charset=UTF-8");
            httpServletResponse.getWriter().write(objectMapper.writeValueAsString("退出成功,返回json，主要用js退出"));
        }else {
            httpServletResponse.sendRedirect(logOutUrl);
        }
    }
}
