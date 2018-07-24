package distributed.architecture.security.browser.config;

import com.weiwei.distributed.architecture.core.constants.SecurityConstants;
import distributed.architecture.security.browser.authentication.LocalAuthenticationFailureHandler;
import distributed.architecture.security.browser.authentication.LocalAuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

public class AbstractChannelSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private LocalAuthenticationSuccessHandler localAuthenticationSuccessHandler;

    @Autowired
    private LocalAuthenticationFailureHandler localAuthenticationFailureHandler;

    public void applyPasswordAuthenticationConfig(HttpSecurity http) throws Exception {
        http.formLogin()
                .loginPage(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL)
                .loginProcessingUrl(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM)
                .successHandler(localAuthenticationSuccessHandler)
                .failureHandler(localAuthenticationFailureHandler);
    }
}
