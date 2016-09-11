package com.les.dao.mapper;

import com.les.dto.UserAnswer;
import com.les.po.Answer;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Lydia
 * @ClassName: AnswerMapper
 * @Description:
 * @date 2016/8/30
 */
@Component
public interface AnswerMapper {

    void insertAnswer(@Param("answerContent") String answerContent, @Param("examinationId") int examinationId, @Param("userId") int userId);

    void updateAnswer(@Param("answerId") int answerId, @Param("answerContent") String answerContent);

    Answer getUserAnswer(@Param("userId") int userId, @Param("examId") int examId);

    List<UserAnswer> getAnswerList(int userId);

    void updateAnswerGoal(@Param("answerId") int answerId, @Param("goal") double goal);

}
