package com.guet.internship.service.Impl;

import com.guet.internship.dto.CommonUserDetails;
import com.guet.internship.mbg.mapper.AdminMapper;
import com.guet.internship.mbg.model.Admin;
import com.guet.internship.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by 欲隐君。 on 2020/8/24
 */
@Service("adminDetailsService")
public class AdminUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private AdminService adminService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //获取登录用户信息

        Admin admin = adminService.getAdminByUsername(username);
        if (admin != null) {
            return new CommonUserDetails(admin.getAccount(),admin.getName(),admin.getPassword(),admin.getIdentify(),null);
        }
        throw new UsernameNotFoundException("用户名或密码错误");

    }
}
