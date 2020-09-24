package com.guet.internship.dao;

import com.guet.internship.condition.DocumentCondition;
import com.guet.internship.mbg.model.Document;

import java.util.List;

/**
 * Created by 欲隐君。 on 2020/8/27
 */
public interface DocumentDao {

    List<Document> selectApplication(DocumentCondition documentCondition);

    List<Document> selectDocumentsByStudentId(String studentId);
}
