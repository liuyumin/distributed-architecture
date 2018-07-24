package com.weiwei.distributed.architecture.core.validate.code;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
public class ValidateCode implements Serializable {

    private static final long serialVersionUID = 6798524244908786256L;

    public String code;

    public LocalDateTime expireTime;

    public ValidateCode(){}

    public ValidateCode(String code,int expireIn){
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
    }

    public ValidateCode(String code,LocalDateTime expireTime){
        this.code = code;
        this.expireTime = expireTime;
    }

    public boolean isExpire(){
        return LocalDateTime.now().isAfter(expireTime);
    }
}
