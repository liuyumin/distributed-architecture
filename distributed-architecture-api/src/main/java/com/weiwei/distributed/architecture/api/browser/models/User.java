package com.weiwei.distributed.architecture.api.browser.models;

import com.fasterxml.jackson.annotation.JsonView;
import com.weiwei.distributed.architecture.api.browser.validator.MyConstraint;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.Constraint;
import java.util.Date;

public class User {

    public interface UserSimpleView{}

    public interface UserDetailView extends UserSimpleView{}

    @NotBlank(message = "用户名不能为空")
    private String userName;

    @MyConstraint
    private String userPwd;

    private Date birthday;

    public User(){}

    public User(String userName,String userPwd){
        this.userName = userName;
        this.userPwd = userPwd;
    }

    @JsonView(User.UserSimpleView.class)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @JsonView(User.UserDetailView.class)
    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    @JsonView(User.UserDetailView.class)
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
