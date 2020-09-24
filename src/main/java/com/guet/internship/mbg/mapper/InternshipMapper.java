package com.guet.internship.mbg.mapper;

import com.guet.internship.mbg.model.Internship;
import com.guet.internship.mbg.model.InternshipExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface InternshipMapper {
    long countByExample(InternshipExample example);

    int deleteByExample(InternshipExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Internship record);

    int insertSelective(Internship record);

    List<Internship> selectByExample(InternshipExample example);

    Internship selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Internship record, @Param("example") InternshipExample example);

    int updateByExample(@Param("record") Internship record, @Param("example") InternshipExample example);

    int updateByPrimaryKeySelective(Internship record);

    int updateByPrimaryKey(Internship record);
}