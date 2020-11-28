package com.guet.internship.dao;

import com.guet.internship.mbg.model.Student;

import java.util.List;

/**
 * Created by 欲隐君。 on 2020/11/28
 */
public interface StudentDao {

    List<Student> selectAbsentStudents(List<Student> presentList,Integer classId);
}
