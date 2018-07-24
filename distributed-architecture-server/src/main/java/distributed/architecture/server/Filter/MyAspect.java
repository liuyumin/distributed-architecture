package distributed.architecture.server.Filter;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Date;

/***
 * @Before() + @After() + @AfterThrowing = @Around
 * 切面可以获取请求方法中参数，于显示事物测试，待后面测试
 */
@Slf4j
@Aspect
@Component
public class MyAspect {

    @Around("execution(* distributed.architecture.server.browser.SecurityDemoBrowser.*(..))")
    public Object handleControllerMethod(ProceedingJoinPoint pjp) throws Throwable{
        log.info("-------------MyAspect handleControllerMethod start---------");
        Object[] args = pjp.getArgs();
        for (Object arg : args){
            log.info("arg is {}",arg);
        }
        long startTime = new Date().getTime();
        Object result = null;
        try {
            result = pjp.proceed();
        }catch (Throwable throwable){
            log.info("-------------MyAspect handleControllerMethodException exception---------");
            throw throwable;
        }
        log.info("-------------MyAspect handleControllerMethod finished 耗时:[{}]---------",(new Date().getTime()-startTime));
        return result;
    }

//    @AfterThrowing("execution(* distributed.architecture.server.browser.SecurityDemoBrowser.*(..))")
//    public void handleControllerMethodException(ProceedingJoinPoint pjp){
//        log.info("-------------MyAspect handleControllerMethodException exception---------");
//    }
}
