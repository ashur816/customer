package com.les.service;

import com.les.dto.UserAnswer;
import com.les.po.Examination;

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
//    void grade(int answer);
}
