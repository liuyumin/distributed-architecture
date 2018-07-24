package com.weiwei.distributed.architecture.api.browser.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class UserQueryCondition implements Serializable {

    private static final long serialVersionUID = 918461028685868176L;

    private String userName;

    private String userPwd;

    private int userAge;

}
