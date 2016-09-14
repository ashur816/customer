package com.les.controller;


import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.les.dto.UserAnswer;
import com.les.po.Examination;
import com.les.service.IAnswerService;
import com.les.service.IExaminationService;
import com.les.utils.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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
     * @return UserAnswer
     * @Description: 根据题目id + 用户id 查询题目及已填答案
     */
    @RequestMapping(value = "/getExamAndAnswer", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    UserAnswer getExamAndAnswer(HttpServletRequest request,@RequestBody String body) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        String examId = JsonUtils.readValueByName(body, "examId");
        String userId = request.getAttribute("loginUserId").toString();
        return examinationService.getExamAndAnswer(Integer.parseInt(userId), Integer.parseInt(examId));

    }

    /**
     * @param request
     * @return void
     * @Description: 新增题目
     */
    @RequestMapping(value = "/insertExam", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public void insertExam(HttpServletRequest request) {
        String examQusetion = request.getParameter("examQusetion");
        String examScore = request.getParameter("examScore");
        String referenceAnswer = request.getParameter("referenceAnswer");
        String examLevel = request.getParameter("examLevel");

        examinationService.insertExam(examQusetion,examScore,referenceAnswer,examLevel);
    }

    /**
     * @param request
     * @return void
     * @Description: 修改题目
     */
    @RequestMapping(value = "/updateExam", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Examination updateExam(HttpServletRequest request){
        int examId =Integer.parseInt(request.getParameter("examId"));
        String examQusetion = request.getParameter("examQusetion");
        String examScore = request.getParameter("examScore");
        String referenceAnswer = request.getParameter("referenceAnswer");
        String examLevel = request.getParameter("examLevel");
        return examinationService.updateExam(examId,examQusetion,examScore,referenceAnswer,examLevel);
    }

    /**
     * @param request
     * @return void
     * @Description: 删除题目
     */
    @RequestMapping(value = "/deleteExam", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public void deleteExam(HttpServletRequest request){
        int examId =Integer.parseInt(request.getParameter("examId"));
        examinationService.deleteExam(examId);
    }
}
