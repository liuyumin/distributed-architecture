package distributed.architecture.server.security;

import distributed.architecture.services.dao.RememberMeTokenRepository;
import distributed.architecture.services.entity.PersistentLogins;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.security.SocialUser;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Component;

/****
 * 用户登录时账户和密码通过数据库进行校验
 */
@Slf4j
@Component("localUserDetailsService")
public class LocalUserDetailsService implements UserDetailsService,SocialUserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        log.info("--------------LocalUserDetailsService loadUserByUsername 可以通过用户名加载用户信息----------------,{}",userName);

        //这里可以通过数据库方式，查看该用户名信息

        //根据数据库查询该用户是否冻结，过期，删除

        //用户加密过程
//        String passwrodEn = passwordEncoder.encode("123456");
//        boolean isCorrect = passwordEncoder.matches("123456",passwrodEn);
//        log.info("--------------LocalUserDetailsService loadUserByUsername 查看密码是否正确匹配-----------,isMatches:=[{}]",isCorrect);
//        if (!isCorrect) throw new UsernameNotFoundException("账户与密码不正确，请输入正确");
//
//        User user = new User(userName, passwrodEn, AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));

        return buildUser(userName);
    }

    @Override
    public SocialUserDetails loadUserByUserId(String userId) throws UsernameNotFoundException {
        log.info("--------------SocialUserDetailsService loadUserByUserId 可以通过社交用户信息----------------,{}",userId);
        log.info("设计登录用户Id:" + userId);
        return buildUser(userId);
    }

    private SocialUserDetails buildUser(String userId) {
        // 根据用户名查找用户信息
        //根据查找到的用户信息判断用户是否被冻结
        String password = passwordEncoder.encode("123456");
        return new SocialUser(userId, password,
                true, true, true, true,
                AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
    }
}
