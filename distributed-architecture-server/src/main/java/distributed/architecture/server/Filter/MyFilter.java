package distributed.architecture.server.Filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;
import java.util.Date;

/****
 * 该方式，是由SERVLET容器提供
 * 缺点不知道，哪一个控制器，和方法进行处理请求
 */
//@Component
@Slf4j
public class MyFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("-----------------MyFilter init--------------");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("-----------------MyFilter doFilter start--------------");
        long startTime = new Date().getTime();
        filterChain.doFilter(servletRequest,servletResponse);
        log.info("-----------------MyFilter doFilter 耗时：[{}]s--------------",(new Date().getTime() - startTime));
        log.info("-----------------MyFilter doFilter finish--------------");
    }

    @Override
    public void destroy() {
        log.info("-----------------MyFilter destroy--------------");
    }
}
