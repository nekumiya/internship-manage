package com.guet.internship.mbg.mapper;

import com.guet.internship.mbg.model.HealthyReport;
import com.guet.internship.mbg.model.HealthyReportExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface HealthyReportMapper {
    long countByExample(HealthyReportExample example);

    int deleteByExample(HealthyReportExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(HealthyReport record);

    int insertSelective(HealthyReport record);

    List<HealthyReport> selectByExample(HealthyReportExample example);

    HealthyReport selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") HealthyReport record, @Param("example") HealthyReportExample example);

    int updateByExample(@Param("record") HealthyReport record, @Param("example") HealthyReportExample example);

    int updateByPrimaryKeySelective(HealthyReport record);

    int updateByPrimaryKey(HealthyReport record);
}