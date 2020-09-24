package com.guet.internship.mbg.mapper;

import com.guet.internship.mbg.model.Accept;
import com.guet.internship.mbg.model.AcceptExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AcceptMapper {
    long countByExample(AcceptExample example);

    int deleteByExample(AcceptExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Accept record);

    int insertSelective(Accept record);

    List<Accept> selectByExample(AcceptExample example);

    Accept selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Accept record, @Param("example") AcceptExample example);

    int updateByExample(@Param("record") Accept record, @Param("example") AcceptExample example);

    int updateByPrimaryKeySelective(Accept record);

    int updateByPrimaryKey(Accept record);
}