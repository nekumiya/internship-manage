package com.guet.internship.dao;

import com.guet.internship.mbg.model.Admin;

/**
 * Created by 欲隐君。 on 2020/8/20
 */
public interface AdminDao {

    Admin login(Admin admin);

    Admin getAdminByUsername(String username);
}
