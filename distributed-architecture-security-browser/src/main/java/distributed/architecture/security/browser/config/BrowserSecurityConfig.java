package distributed.architecture.security.browser.config;

import com.weiwei.distributed.architecture.core.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import com.weiwei.distributed.architecture.core.config.ValidateCodeBeanConfig;
import com.weiwei.distributed.architecture.core.config.ValidateCodeSecurityConfig;
import com.weiwei.distributed.architecture.core.constants.SecurityConstants;
import com.weiwei.distributed.architecture.core.social.SocialConfig;
import distributed.architecture.security.browser.authentication.LocalPersistentTokenService;
import distributed.architecture.security.browser.core.LocalUserDetailsService;
import distributed.architecture.security.browser.properties.BrowserSecurityProperties;
import distributed.architecture.security.browser.session.LocalExpiredSessionStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.social.security.SpringSocialConfigurer;


@Configuration
@Import({ValidateCodeBeanConfig.class,SocialConfig.class})//导入图片和验证码生成Bean
@ComponentScan(basePackages = {"distributed.architecture.security.browser"})
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class BrowserSecurityConfig extends AbstractChannelSecurityConfig {

    @Autowired
    private BrowserSecurityProperties browserSecurityProperties;

    @Autowired
    private LocalUserDetailsService localUserDetailsService;

    @Autowired
    private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;

    @Autowired
    private ValidateCodeSecurityConfig validateCodeSecurityConfig;

    @Autowired
    private LocalPersistentTokenService localPersistentTokenService;

    @Autowired
    private SpringSocialConfigurer localSocialSecurityConfig;

    @Autowired
    private LogoutSuccessHandler logoutSuccessHandler;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        applyPasswordAuthenticationConfig(http);

        http.apply(validateCodeSecurityConfig)
                .and()
                .apply(smsCodeAuthenticationSecurityConfig)
                .and()
                .apply(localSocialSecurityConfig)
                .and()
                .rememberMe()
                .tokenRepository(localPersistentTokenService)
                .tokenValiditySeconds(browserSecurityProperties.getRememberMeSecond())
                .userDetailsService(localUserDetailsService)
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessHandler(logoutSuccessHandler)
                .deleteCookies("JSESSIONID")
                .and()
                .sessionManagement()
                .invalidSessionUrl("/session/invalid")
                .maximumSessions(1)
                .maxSessionsPreventsLogin(true)
                .expiredSessionStrategy(new LocalExpiredSessionStrategy())
                .and().and()
                .authorizeRequests()
                .antMatchers(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL,
                        SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_MOBILE
                        , browserSecurityProperties.getCustomPage()
                        , SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX+"/*"
                        , browserSecurityProperties.getSignUpUrl()
                        , "/demo-signOut.html"
                        , "/user/register"
                        , "/user/social"
                        , "/session/invalid"
                ).permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .csrf()
                .disable();
    }
}
