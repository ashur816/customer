package com.les.dao.mapper;

import com.les.dto.UserAnswer;
import com.les.po.Examination;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @author Lydia
 * @ClassName: ExaminationMapper
 * @Description:
 * @date 2016/8/30
 */
@Component
public interface ExaminationMapper {
    void insertExam(@Param("examId") int examId,@Param("examQuestion") String examQuestion,@Param("examScore") String examScore, @Param("referenceAnswer") String referenceAnswer, @Param("examLevel") int examLevel,@Param("examOrientation")int examOrientation);

    UserAnswer getExamAndAnswer(@Param("userId") int userId, @Param("examId") int examId);

    void updateExam(@Param("examId") int examId,@Param("examQuestion") String examQuestion,@Param("examScore") String examScore, @Param("referenceAnswer") String referenceAnswer, @Param("examLevel") int examLevel,@Param("examOrientation")int examOrientation);

    void deleteExam(int examId);

    Set<Integer> allExamId();

    List<Integer> getExamByScore(@Param("userId") int userId,@Param("examinationScore")int examinationScore);

    List<Examination> getExamList(@Param("examLevel") int examLevel);

    Examination getExamInfo(int examId);
}
