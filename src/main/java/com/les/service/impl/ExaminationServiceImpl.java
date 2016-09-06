package com.les.service.impl;

import com.les.dao.mapper.ExaminationMapper;
import com.les.dto.UserAnswer;
import com.les.service.IExaminationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Lydia
 * @ClassName: ExaminationServiceImpl
 * @Description:
 * @date 2016/8/30
 */
@Service
public class ExaminationServiceImpl implements IExaminationService {

    @Resource
    private ExaminationMapper examinationMapper;

    public void insertExam(String examinationQuestion) {
        examinationMapper.insertExam(examinationQuestion);
    }

    @Override
    public UserAnswer getExamAndAnswer(int userId, int examId) {
        return examinationMapper.getExamAndAnswer(userId, examId);
    }

//    @Override
//    public void grade(int answer) {
//
//    }
}
