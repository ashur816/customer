package com.les.service;

import com.les.dto.UserAnswer;

/**
 * @author Lydia
 * @ClassName: IExaminationService
 * @Description:
 * @date 2016/8/30
 */
public interface IExaminationService {

    void insertExam(String examinationQuestion);

    UserAnswer getExamAndAnswer(int userId, int examId);

//    void grade(int answer);
}
