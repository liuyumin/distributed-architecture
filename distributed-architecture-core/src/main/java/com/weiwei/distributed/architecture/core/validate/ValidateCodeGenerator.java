package com.weiwei.distributed.architecture.core.validate;

import com.weiwei.distributed.architecture.core.validate.code.ValidateCode;
import org.springframework.web.context.request.ServletWebRequest;

public interface ValidateCodeGenerator {

    ValidateCode generateCode(ServletWebRequest request);


}
