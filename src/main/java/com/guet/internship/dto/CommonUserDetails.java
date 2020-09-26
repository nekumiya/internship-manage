package com.guet.internship.dto;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * Created by 欲隐君。 on 2020/9/25
 */
public class CommonUserDetails implements UserDetails{

    //学生类型code:校外实习
    public final static String STUDENT_INTERNSHIP_TYPE_CODE = "1";
    //学生类型code:校内实习
    public final static String STUDENT_CLASS_TYPE_CODE = "2";
    //管理员类型code
    public final static String ADMIN_TYPE_CODE = "3";

    //用户id
    private String account;
    //用户名
    private String username;
    //密码
    private String password;
    //用户类型
    private String userType;
    //用户角色表
    private Collection<? extends GrantedAuthority> authorities;

    public CommonUserDetails(String account, String username, String password, String userType, Collection<? extends GrantedAuthority> authorities) {
        this.account = account;
        this.username = username;
        this.password = password;
        this.userType = userType;
        this.authorities = authorities;
    }

    //获取权限列表
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public String getAccount() {
        return account;
    }

    public String getUserType() {
        return userType;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    //账号是否未过期
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    //账号是否未锁定
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    //凭证是否未过期
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    //账号是否已启用
    @Override
    public boolean isEnabled() {
        return true;
    }
}
