package distributed.architecture.server.browser;

import com.weiwei.distributed.architecture.core.validate.ValidateCodeGenerator;
import com.weiwei.distributed.architecture.core.validate.code.ImageCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

@Slf4j
//@Component("imageCodeGenerator")
public class DemoImageCodeGenerator implements ValidateCodeGenerator {
    @Override
    public ImageCode generateCode(ServletWebRequest request) {
        log.info("-----DemoImageCodeGenerator generateCode----更高级图形验证码生成器----------");
        return null;
    }
}
