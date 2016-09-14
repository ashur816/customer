package com.les.service.impl;

import com.les.dao.mapper.ExaminationMapper;
import com.les.dto.UserAnswer;
import com.les.po.Examination;
import com.les.service.IExaminationService;
import com.les.utils.RandomUtils;
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

    @Override
    public void insertExam(String examQuestion, String examScore, String referenceAnswer, String examLevel) {
        int examId = Integer.parseInt(RandomUtils.generateNumString(8));
        if(examinationMapper.allExamId().contains(examId)){
            examId = Integer.parseInt(RandomUtils.generateNumString(8));
        }
        examinationMapper.insertExam(examId,examQuestion,examScore,referenceAnswer,examLevel);
    }

    @Override
    public UserAnswer getExamAndAnswer(int userId, int examId) {
        return examinationMapper.getExamAndAnswer(userId, examId);
    }

    @Override
    public Examination updateExam(int examId, String examQuestion, String examScore, String referenceAnswer, String examLevel) {
        return examinationMapper.updateExam(examId,examQuestion,examScore,referenceAnswer,examLevel);
    }

    @Override
    public void deleteExam(int examId) {
        examinationMapper.deleteExam(examId);
    }

//    @Override
//    public void grade(int answer) {
//
//    }
}
