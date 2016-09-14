package com.les.dao.mapper;

import com.les.dto.UserAnswer;
import com.les.po.Examination;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * @author Lydia
 * @ClassName: ExaminationMapper
 * @Description:
 * @date 2016/8/30
 */
@Component
public interface ExaminationMapper {
    void insertExam(@Param("examQuestion") String examQuestion,@Param("examScore") String examScore, @Param("referenceAnswer") String referenceAnswer, @Param("examLevel") String examLevel);

    UserAnswer getExamAndAnswer(@Param("userId") int userId, @Param("examId") int examId);

    Examination updateExam(@Param("examId") int examId,@Param("examQuestion") String examQuestion,@Param("examScore") String examScore, @Param("referenceAnswer") String referenceAnswer, @Param("examLevel") String examLevel);

    void deleteExam(int examId);
}
