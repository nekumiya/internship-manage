package com.guet.internship.dto;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

/**
 * Created by 欲隐君。 on 2020/9/25
 */
public class AdminAuthenticationToken extends UsernamePasswordAuthenticationToken {

    public AdminAuthenticationToken(Object principal, Object credentials) {
        super(principal, credentials);
    }
}