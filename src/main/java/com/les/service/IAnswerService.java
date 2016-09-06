package com.les.service;

import com.les.dto.UserAnswer;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author Lydia
 * @ClassName: IAnswerService
 * @Description:
 * @date 2016/8/30
 */
public interface IAnswerService {
    public void insertAnswer(String answerContent,int examinationId,int userId);
    List<UserAnswer> getAnswerList(int userId);
    String updateAnswerBatch(List<LinkedHashMap> gradeList);

}
