package com.les.dao.mapper;

import com.les.dto.UserAnswer;
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
    void insertExam(String examinationQuestion);

    UserAnswer getExamAndAnswer(@Param("userId") int userId, @Param("examId") int examId);
}
