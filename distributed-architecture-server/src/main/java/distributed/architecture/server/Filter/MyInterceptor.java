package distributed.architecture.server.Filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/***
 * spring 提供拦截器，在拦截时可以获取用户请求处理类和方法名，但是不可以获取方法的参数,因此引入aspect切面
 * 相比于servlet filter要稍微强一些
 */
@Slf4j
@Component
public class MyInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler) throws Exception {

        log.info("---------------------MyInterceptor preHandle---------------");
        log.info(((HandlerMethod)handler).getBean().getClass().getName());
        log.info(((HandlerMethod)handler).getMethod().getName());
        httpServletRequest.setAttribute("startTime",new Date().getTime());

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        log.info("---------------------MyInterceptor postHandle---------------");
        Long startTime = (Long) httpServletRequest.getAttribute("startTime");
        log.info("---------------------MyInterceptor postHandle 耗时:[{}]---------------",(new Date().getTime() - startTime));
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        log.info("---------------------MyInterceptor afterCompletion---------------");
        log.info("---------------------MyInterceptor afterCompletion exception:["+e+"]---------------");
    }
}
