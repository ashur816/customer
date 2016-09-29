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
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

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
     * @param request
     * @return List<Integer>
     * @Description: 获取题目id List
     */
    @RequestMapping(value = "/getExamIdList", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    List<Integer> getExamIdList(HttpServletRequest request) {
        String userId = request.getAttribute("loginUserId").toString();
        return examinationService.getExamIdList(Integer.parseInt(userId));
    }

    /**
     * @param request
     * @param body json体
     * @return UserAnswer
     * @Description: 根据题目id + 用户id 查询题目及已填答案
     */
    @RequestMapping(value = "/getExamAndAnswer", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    UserAnswer getExamAndAnswer(HttpServletRequest request) throws IOException {
        String examId = request.getParameter("examId");
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
    public String insertExam(HttpServletRequest request) {
        String result = "{\"message\":\"新增成功\"}";
        String examQuestion = request.getParameter("examinationQuestion");
        String examScore = request.getParameter("examinationScore");
        String referenceAnswer = request.getParameter("referenceAnswer");
        int examLevel = Integer.parseInt(request.getParameter("examinationLevel"));

        examinationService.insertExam(examQuestion, examScore, referenceAnswer, examLevel);
        return result;
    }

    /**
     * @param request
     * @return void
     * @Description: 修改题目
     */
    @RequestMapping(value = "/updateExam", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String updateExam(HttpServletRequest request) {
        String result = "{\"message\":\"修改成功\"}";
        int examId = Integer.parseInt(request.getParameter("examinationId"));
        String examQuestion = request.getParameter("examinationQuestion");
        String examScore = request.getParameter("examinationScore");
        String referenceAnswer = request.getParameter("referenceAnswer");
        int examLevel = Integer.parseInt(request.getParameter("examinationLevel"));
        examinationService.updateExam(examId, examQuestion, examScore, referenceAnswer, examLevel);
        return result;
    }

    /**
     * @param request
     * @return void
     * @Description: 删除题目
     */
    @RequestMapping(value = "/deleteExam", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String deleteExam(HttpServletRequest request) {
        String result = "{\"message\":\"删除成功\"}";
        int examId = Integer.parseInt(request.getParameter("examinationId"));
        examinationService.deleteExam(examId);
        return result;
    }

    /**
     * @param examLevel
     * @return List<Examination>
     * @Description: 根据条件查看题目
     */
    @RequestMapping(value = "/getExamList/{examLevel}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public List<Examination> getExamList(@PathVariable int examLevel) {
        List examList = examinationService.getExamList(examLevel);
        return examList;
    }

    /**
     * @param examId
     * @return List<Examination>
     * @Description: 根据主键获取题目
     */
    @RequestMapping(value = "/getExamInfo/{examId}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Examination getExamInfo(@PathVariable int examId) {
        Examination examInfo = examinationService.getExamInfo(examId);
        return examInfo;
    }
}
