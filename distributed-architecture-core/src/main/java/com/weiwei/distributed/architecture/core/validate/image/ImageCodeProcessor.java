package com.weiwei.distributed.architecture.core.validate.image;

import com.weiwei.distributed.architecture.core.validate.AbstractValidateCodeProcessor;
import com.weiwei.distributed.architecture.core.validate.code.ImageCode;
import com.weiwei.distributed.architecture.core.validate.code.ValidateCode;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;

@Service("imageCodeValidateCodeProcessor")
public class ImageCodeProcessor extends AbstractValidateCodeProcessor<ImageCode> {

    @Override
    protected void send(ServletWebRequest request, ImageCode imageCode) throws Exception {
        //将生成图片写到接口的响应中
        ImageIO.write(imageCode.getBufferedImage(),"JPEG",request.getResponse().getOutputStream());
    }
}
