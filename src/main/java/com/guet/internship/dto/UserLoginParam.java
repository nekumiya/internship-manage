package com.guet.internship.dto;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotEmpty;

/**
 * Created by 欲隐君。 on 2020/8/20
 *
 * 用户登录参数
 */
public class UserLoginParam {

    @ApiModelProperty(value = "用户账号",required = true)
    @NotEmpty(message = "用户账号不能为空")
    private String account;

    @ApiModelProperty(value = "用户密码", required = true)
    @NotEmpty(message = "用户密码不能为空")
    private String password;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserLoginParam{" +
                "account='" + account + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
