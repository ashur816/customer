package com.les.controller;


import com.les.dto.UserAnswer;
import com.les.po.Examination;
import com.les.service.IAnswerService;
import com.les.service.IExaminationService;
import com.les.utils.JsonUtils;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @author Lydia
 * @ClassName: ExaminationController
 * @Description:
 * @date 2016/8/30
 */
@Controller
public class ExaminationController {
    private static final Logger logger = LoggerFactory.getLogger(ExaminationController.class);

    @Resource
    private IExaminationService examinationService;
    @Resource
    private IAnswerService answerService;

    /**
     * @param body json体
     * @return Examination
     * @Description: 根据题目id + 用户id 查询题目及已填答案
     */
    @RequestMapping(value = "/getExamAndAnswer", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    UserAnswer getExamAndAnswer(@RequestBody String body) throws IOException {
        String examId = JsonUtils.readValueByName(body, "examId");
        String userId = JsonUtils.readValueByName(body, "userId");
        return examinationService.getExamAndAnswer(Integer.parseInt(userId), Integer.parseInt(examId));

    }

    /**
     * @param body json体
     * @return void
     * @Description: 新增题目
     */
    @RequestMapping(value = "/insertExam", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public void insertExam(@RequestBody String body) {
        Examination examination = (Examination) JSONObject.toBean(JSONObject.fromObject(body), Examination.class);
        String examinationQusetion = examination.getExaminationQuestion();
        examinationService.insertExam(examinationQusetion);
    }
}
