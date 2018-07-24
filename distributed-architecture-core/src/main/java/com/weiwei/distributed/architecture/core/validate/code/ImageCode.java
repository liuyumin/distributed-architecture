package com.weiwei.distributed.architecture.core.validate.code;

import lombok.Getter;
import lombok.Setter;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

@Getter
@Setter
public class ImageCode extends ValidateCode{

    private static final long serialVersionUID = 9218553344912013524L;

    private BufferedImage bufferedImage;

    public ImageCode(){}

    public ImageCode(BufferedImage bufferedImage,String imageCode,LocalDateTime expireTime){
        super(imageCode,expireTime);
        this.bufferedImage = bufferedImage;
    }

    public ImageCode(BufferedImage bufferedImage,String imageCode,int expireIn){
        super(imageCode,expireIn);
        this.bufferedImage = bufferedImage;
    }




}
