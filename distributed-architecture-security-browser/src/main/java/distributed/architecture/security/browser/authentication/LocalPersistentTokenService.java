package distributed.architecture.security.browser.authentication;

import cn.hutool.core.bean.BeanUtil;
import com.weiwei.distributed.architecture.core.properties.BrowserProperties;
import distributed.architecture.security.browser.properties.BrowserSecurityProperties;
import distributed.architecture.services.dao.RememberMeTokenRepository;
import distributed.architecture.services.entity.PersistentLogins;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/****
 * 通过remember-me实现，密码记录,主要向cookies保存token，通过数据库进行匹配，如果匹配上实现免登录功能
 */
@Component("localPersistentTokenService")
public class LocalPersistentTokenService implements PersistentTokenRepository {

    @Autowired
    private RememberMeTokenRepository rememberMeTokenRepository;

    @Autowired
    private BrowserProperties browserProperties;

    @Override
    public void createNewToken(PersistentRememberMeToken persistentRememberMeToken) {
        List<PersistentLogins> tokens = rememberMeTokenRepository.getLocalAllByUsernameOrderByDate(persistentRememberMeToken.getUsername());

        if (null != tokens &&  tokens.size() >= browserProperties.getLoginPoints()){
            int end = tokens.size() - browserProperties.getLoginPoints() + 1;
            for (int i = 0; i < end; i++) {
                rememberMeTokenRepository.delete(tokens.get(i));
            }
        }

        PersistentLogins persistentLogins = new PersistentLogins();
        BeanUtil.copyProperties(persistentRememberMeToken,persistentLogins);
        rememberMeTokenRepository.save(persistentLogins);

    }

    @Override
    public void updateToken(String series, String tokenValue, Date lastUsed) {
        PersistentLogins persistentLogins = rememberMeTokenRepository.getLocalBySeries(series);
        if (persistentLogins != null) {
            persistentLogins.setTokenValue(tokenValue);
            persistentLogins.setDate(lastUsed);
        }
    }

    @Override
    public PersistentRememberMeToken getTokenForSeries(String seriesId) {
        PersistentLogins persistentLogins = rememberMeTokenRepository.getLocalBySeries(seriesId);
        if (persistentLogins != null) {
            return new PersistentRememberMeToken(persistentLogins.getUsername(),
                    persistentLogins.getSeries(), persistentLogins.getTokenValue(),
                    persistentLogins.getDate());
        }
        return null;
    }

    @Override
    public void removeUserTokens(String username) {
        rememberMeTokenRepository.deleteLocalByUsername(username);
    }
}
