package com.guet.internship.service.Impl;

import com.guet.internship.dto.CommonUserDetails;
import com.guet.internship.mbg.model.Student;
import com.guet.internship.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by 欲隐君。 on 2020/8/24
 */
@Service("studentDetailsService")
public class StudentUserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private StudentService studentService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //获取登录用户信息

        Student student = studentService.getStudentByUsername(username);
        if (student != null){
            return new CommonUserDetails(student.getAccount(),student.getName(),student.getPassword(),student.getIdentify(),null);
        }
        throw new UsernameNotFoundException("用户名或密码错误");
    }
}
