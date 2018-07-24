package distributed.architecture.security.browser.support;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SocialUserInfo {

    private String providerId;

    private String providerUserId;

    private String nickname;

    private String headimg;
}
