package distributed.architecture.security.browser.session;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

import javax.servlet.ServletException;
import java.io.IOException;

public class LocalExpiredSessionStrategy implements SessionInformationExpiredStrategy {

    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent sessionInformationExpiredEvent) throws IOException, ServletException {
//        UserDetails userDetails = (UserDetails) sessionInformationExpiredEvent.getSessionInformation().getPrincipal();
//        sessionInformationExpiredEvent.getRequest().getRemoteAddr();
//        sessionInformationExpiredEvent.getSource();
        sessionInformationExpiredEvent.getResponse().setContentType("application/json;charset=UTF-8");
        sessionInformationExpiredEvent.getResponse().getWriter().write("session并发控制，最近在某个浏览器登录，剔除之前在其它浏览器上登录");
    }
}
