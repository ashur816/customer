package com.les.service.impl;

import com.les.common.StaticConst;
import com.les.dao.mapper.AnswerMapper;
import com.les.dao.mapper.ExaminationMapper;
import com.les.dao.mapper.UserMapper;
import com.les.dto.UserAnswer;
import com.les.dto.UserResult;
import com.les.po.Examination;
import com.les.po.UserGoal;
import com.les.service.IExaminationService;
import com.les.utils.RandomUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

import static com.les.common.StaticConst.EXAMINATION_SCORE;

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

    @Resource
    private AnswerMapper answerMapper;

    @Resource
    private UserMapper userMapper;

    @Override
    public void insertExam(String examQuestion, String examScore, String referenceAnswer, int examLevel,int examOrientation) {
        int examId = Integer.parseInt(RandomUtils.generateNumString(8));//随机生成题目号
        Set set = examinationMapper.allExamId();
        if (set.contains(examId)) {//去重
            examId = Integer.parseInt(RandomUtils.generateNumString(8));
        }
        examinationMapper.insertExam(examId, examQuestion, examScore, referenceAnswer, examLevel,examOrientation);
    }

    @Override
    public UserAnswer getExamAndAnswer(int userId, int examId) {
        return examinationMapper.getExamAndAnswer(userId, examId);
    }

    @Override
    public void updateExam(int examId, String examQuestion, String examScore, String referenceAnswer, int examLevel,int examOrientation) {
        examinationMapper.updateExam(examId, examQuestion, examScore, referenceAnswer, examLevel,examOrientation);
    }

    @Override
    public void deleteExam(int examId) {
        examinationMapper.deleteExam(examId);
    }

    @Override
    public List<Integer> getExamIdList(int userId) {
        UserResult userResult = userMapper.getUserById(userId);
        int userLevel = userResult.getUserLevel();
        List<Integer> examIdList = new ArrayList<>();
        if(userLevel == 1){
            for(int j=0;j<EXAMINATION_SCORE.length;j++){
                int examinationScore = EXAMINATION_SCORE[j];
                List<Integer> examList = examinationMapper.getExamByScore(userId,examinationScore);
                if(examList == null ||examList.size() == 0){
                    continue;
                }
               for(int i=0;i<StaticConst.EXAMINATION_NUM1[j];i++){
                    int index=(int)(Math.random()*examList.size());
                    if(examIdList.contains(examList.get(index))){
                        i--;
                        continue;
                    }
                    examIdList.add(examList.get(index));
                }
            }
        }else if(userLevel == 2){
            for(int j=0;j<EXAMINATION_SCORE.length;j++){
                int examinationScore = EXAMINATION_SCORE[j];
                List<Integer> examList = examinationMapper.getExamByScore(userId,examinationScore);
                if(examList == null ||examList.size() == 0){
                    break;
                }
                for(int i=0;i<StaticConst.EXAMINATION_NUM2[j];i++){
                    int index=(int)(Math.random()*examList.size());
                    if(examIdList.contains(examList.get(index))){
                        i--;
                        break;
                    }
                    examIdList.add(examList.get(index));
                }
            }
        }else{
            for(int j=0;j<EXAMINATION_SCORE.length;j++){
                int examinationScore = EXAMINATION_SCORE[j];
                List<Integer> examList = examinationMapper.getExamByScore(userId,examinationScore);
                if(examList == null ||examList.size() == 0){
                    break;
                }
                for(int i=0;i<StaticConst.EXAMINATION_NUM3[j];i++){
                    int index=(int)(Math.random()*examList.size());
                    if(examIdList.contains(examList.get(index))){
                        i--;
                        break;
                    }
                    examIdList.add(examList.get(index));
                }
            }
        }
        Iterator it = examIdList.iterator();
        while (it.hasNext()) {
            Object examinationId = it.next();
            //删除原有的答题记录
            answerMapper.deleteAnswer(userId, Integer.parseInt(examinationId.toString()));
            //增加新的答题记录
            answerMapper.insertAnswer("", Integer.parseInt(String.valueOf(examinationId)), userId);
        }
        return examIdList;
    }

    @Override
    public List<Examination> getExamList(int examLevel) {
        return examinationMapper.getExamList(examLevel);
    }

    @Override
    public Examination getExamInfo(int examId) {
        return examinationMapper.getExamInfo(examId);
    }

    @Override
    /*
    * 考生开始作答后，记录开始时间，返回开始时间和题号列表
    * */
    public Map startExam(int userId) {
        List<Integer> list  = this.getExamIdList(userId);
        UserGoal userGoal = userMapper.getLatestUserGoal(userId);
        Date startTime = userGoal.getStartTime();
        Map map = new HashMap();
        map.put("startTime",startTime);
        map.put("examList",list);
        return map;
    }


}
