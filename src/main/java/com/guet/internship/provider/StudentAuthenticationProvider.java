package com.guet.internship.provider;

import com.guet.internship.dto.AdminAuthenticationToken;
import com.guet.internship.dto.StudentAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Created by 欲隐君。 on 2020/9/25
 */
public class StudentAuthenticationProvider extends DaoAuthenticationProvider {
    /**
     * 初始化 将使用Student专用的userDetailsService
     * @param encoder
     * @param userDetailsService
     */
    public StudentAuthenticationProvider(PasswordEncoder encoder, UserDetailsService userDetailsService){
        setPasswordEncoder(encoder);
        setUserDetailsService(userDetailsService);
    }

    @Override
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        super.setPasswordEncoder(passwordEncoder);
    }

    @Override
    public void setUserDetailsPasswordService(UserDetailsPasswordService userDetailsPasswordService) {
        super.setUserDetailsPasswordService(userDetailsPasswordService);
    }

    /**
     * 判断只有传入StudentAuthenticationToken的时候才使用这个Provider
     * supports会在AuthenticationManager层被调用
     * @param authentication
     * @return
     */
    public boolean supports(Class<?> authentication) {
        return StudentAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
