package com.les.service.impl;

import com.les.dao.mapper.AnswerMapper;
import com.les.dao.mapper.UserMapper;
import com.les.dto.GoalInfo;
import com.les.dto.UserAnswer;
import com.les.dto.UserGoal;
import com.les.dto.UserResult;
import com.les.po.Answer;
import com.les.service.IAnswerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Lydia
 * @ClassName: AnswerServiceImpl
 * @Description:
 * @date 2016/8/30
 */
@Service
public class AnswerServiceImpl implements IAnswerService {

    @Resource
    private AnswerMapper answerMapper;

    @Resource
    private UserMapper userMapper;

    @Override
    public void insertAnswer(String answerContent, int examId, int userId) {
        //查询是否已经作答
        Answer answer = answerMapper.getUserAnswer(userId, examId);
        if (answer == null) {
            answerMapper.insertAnswer(answerContent, examId, userId);
        } else {
            answerMapper.updateAnswer(answer.getAnswerId(), answerContent);
        }
    }

    @Override
    public List<UserAnswer> getAnswerList(int userId) {
        return answerMapper.getAnswerList(userId);
    }

    @Override
    public String updateAnswerBatch(int loginUserId, GoalInfo goalInfo) {
        double totalGoal = 0.0;
        List<UserGoal> goalList = goalInfo.getGoalList();
        for (UserGoal userGoal : goalList) {
            answerMapper.updateAnswerGoal(userGoal.getAnswerId(), userGoal.getGoal());
            totalGoal += userGoal.getGoal();
        }

        String tGoal = String.valueOf(totalGoal);
        UserResult userResults = userMapper.getUserById(loginUserId);
        if(userResults != null){
            String examMaker = userResults.getFullname();
            userMapper.updateUserGoal(examMaker, goalInfo.getUserId(), tGoal);
        }
        return tGoal;
    }
}
