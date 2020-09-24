package com.guet.internship.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by 欲隐君。 on 2020/6/9
 */

@Configuration
@MapperScan({"com.guet.internship.mbg.mapper","com.guet.internship.dao"})
public class MybatisGeneratorConfig {
}
