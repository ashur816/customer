package com.les.service.impl;

import com.les.dao.mapper.ExaminationMapper;
import com.les.dto.UserAnswer;
import com.les.po.Examination;
import com.les.service.IExaminationService;
import com.les.utils.RandomUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

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
    public void insertExam(String examQuestion, String examScore, String referenceAnswer, int examLevel) {
        int examId = Integer.parseInt(RandomUtils.generateNumString(8));
        Set set = examinationMapper.allExamId();
        if(set.contains(examId)){
            examId = Integer.parseInt(RandomUtils.generateNumString(8));
        }
        examinationMapper.insertExam(examId,examQuestion,examScore,referenceAnswer,examLevel);
    }

    @Override
    public UserAnswer getExamAndAnswer(int userId, int examId) {
        return examinationMapper.getExamAndAnswer(userId, examId);
    }

    @Override
    public void updateExam(int examId, String examQuestion, String examScore, String referenceAnswer, int examLevel) {
        examinationMapper.updateExam(examId,examQuestion,examScore,referenceAnswer,examLevel);
    }

    @Override
    public void deleteExam(int examId) {
        examinationMapper.deleteExam(examId);
    }

    @Override
    public List<Integer> getExamIdList(int userId) {
        return examinationMapper.getExamIdList(userId);
    }

    @Override
    public List<Examination> getExamList(int examLevel) {
        return examinationMapper.getExamList(examLevel);
    }

    @Override
    public Examination getExamInfo(int examId) {
        return examinationMapper.getExamInfo(examId);
    }

}
