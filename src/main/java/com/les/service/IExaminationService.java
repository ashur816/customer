package com.les.service;

import com.les.dto.UserAnswer;
import com.les.po.Examination;

import java.util.List;

/**
 * @author Lydia
 * @ClassName: IExaminationService
 * @Description:
 * @date 2016/8/30
 */
public interface IExaminationService {

    void insertExam(String examQuestion,String examScore,String referenceAnswer,String examLevel);

    UserAnswer getExamAndAnswer(int userId, int examId);

    Examination updateExam(int examId,String examQuestion,String examScore,String referenceAnswer,String examLevel);

    void deleteExam(int examId);

    List<Integer> getExamIdList(int userId);

    List<Examination> getExamList(String examLevel);
//    void grade(int answer);
}
