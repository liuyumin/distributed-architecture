package distributed.architecture.server.component;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(SystemBizException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String,Object> handleUserNotExistException(Exception ex){
        Map<String,Object> result = new HashMap<>();
        if (ex instanceof SystemBizException){
            SystemBizException systemBizException = (SystemBizException)ex;
            result.put(systemBizException.getErrorCode(),systemBizException.getErrorMessage());
        }
        return result;
    }
}
