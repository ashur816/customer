package com.les.service.impl;

import com.les.dao.mapper.AnswerMapper;
import com.les.dto.UserAnswer;
import com.les.po.Answer;
import com.les.service.IAnswerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.LinkedHashMap;
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
    public String updateAnswerBatch(List<LinkedHashMap> goalList) {
        double totalGoal = 0.0;
        for (LinkedHashMap map : goalList) {
            answerMapper.updateAnswerGoal(Integer.parseInt(map.get("answer_id").toString()),
                    (map.get("goal").toString()));
            totalGoal += Double.parseDouble(map.get("goal").toString());
        }
        return  String.valueOf(totalGoal);
    }
}
