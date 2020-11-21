package com.guet.internship.dao;


import com.guet.internship.condition.SignCondition;
import com.guet.internship.mbg.model.Sign;

import java.util.List;

/**
 * Created by 欲隐君。 on 2020/11/20
 */
public interface SignDao {

    List<Sign> selectSign(SignCondition condition);

}
