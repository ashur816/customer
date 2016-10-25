package com.les.service;

import com.les.dto.UserAnswer;
import com.les.po.Examination;

import java.util.List;
import java.util.Map;

/**
 * @author Lydia
 * @ClassName: IExaminationService
 * @Description:
 * @date 2016/8/30
 */
public interface IExaminationService {

    void insertExam(String examQuestion,String examScore,String referenceAnswer,int examLevel,int examOrientation);

    UserAnswer getExamAndAnswer(int userId, int examId);

    void updateExam(int examId,String examQuestion,String examScore,String referenceAnswer,int examLevel,int examOrientation);

    void deleteExam(int examId);

    List<Integer> getExamIdList(int userId);

    List<Examination> getExamList(int examLevel);

    Examination getExamInfo(int examId);

    Map startExam(int userId);

}
