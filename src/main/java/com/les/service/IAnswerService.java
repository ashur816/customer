package com.les.service;

import com.les.dto.GoalInfo;
import com.les.dto.UserAnswer;

import java.util.List;

/**
 * @author Lydia
 * @ClassName: IAnswerService
 * @Description:
 * @date 2016/8/30
 */
public interface IAnswerService {
    void insertAnswer(String answerContent, int examinationId, int userId);

    List<UserAnswer> getAnswerList(int userId);

    GoalInfo updateAnswerBatch(int loginUserId, GoalInfo goalInfo);

    UserAnswer getUserExamAnswer(int examinationId, int userId);

    void gradeAnswer(int answerId, int goal);
    String totalGoal(String goal);
}
