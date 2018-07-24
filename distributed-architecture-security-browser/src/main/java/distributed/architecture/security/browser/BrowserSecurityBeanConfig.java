package distributed.architecture.security.browser;

import com.weiwei.distributed.architecture.core.properties.LocalSecurityProperties;
import distributed.architecture.security.browser.logout.LocalLogoutSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

@Configuration
public class BrowserSecurityBeanConfig {

    @Autowired
    private LocalSecurityProperties localSecurityProperties;

    @Bean
    @ConditionalOnMissingBean(LogoutSuccessHandler.class)
    public LogoutSuccessHandler logoutSuccessHandler(){
        return new LocalLogoutSuccessHandler(localSecurityProperties.getBrowser().getSignOutUrl());
    }
}
