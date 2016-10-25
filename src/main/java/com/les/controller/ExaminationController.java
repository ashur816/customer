package com.les.controller;


import com.les.dto.UserAnswer;
import com.les.po.Examination;
import com.les.service.IAnswerService;
import com.les.service.IExaminationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;

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
    Map getExamIdList(HttpServletRequest request) {
        logger.info("考生获取试卷中的题号");
        String userId = request.getAttribute("loginUserId").toString();
        return examinationService.startExam(Integer.parseInt(userId));
    }

    /**
     * @param request
     * @return UserAnswer
     * @Description: 根据题目id + 用户id 查询题目及已填答案
     */
    @RequestMapping(value = "/getExamAndAnswer", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    UserAnswer getExamAndAnswer(HttpServletRequest request) throws IOException {
        logger.info("考生获取试卷");
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
        logger.info("新增题目");
        String result = "{\"message\":\"新增成功\"}";
        String examQuestion = request.getParameter("examinationQuestion");
        String examScore = request.getParameter("examinationScore");
        String referenceAnswer = request.getParameter("referenceAnswer");
        int examLevel = Integer.parseInt(request.getParameter("examinationLevel"));
        int examOrientation = Integer.parseInt(request.getParameter("examOrientation"));

        examinationService.insertExam(examQuestion, examScore, referenceAnswer, examLevel,examOrientation);
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
        logger.info("修改题目信息");
        String result = "{\"message\":\"修改成功\"}";
        int examId = Integer.parseInt(request.getParameter("examinationId"));
        String examQuestion = request.getParameter("examinationQuestion");
        String examScore = request.getParameter("examinationScore");
        String referenceAnswer = request.getParameter("referenceAnswer");
        int examLevel = Integer.parseInt(request.getParameter("examinationLevel"));
        int examOrientation = Integer.parseInt(request.getParameter("examOrientation"));
        examinationService.updateExam(examId, examQuestion, examScore, referenceAnswer, examLevel,examOrientation);
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
        logger.info("删除题目");
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
        logger.info("根据题目级别筛选");
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
        logger.info("根据题号获取题目信息");
        Examination examInfo = examinationService.getExamInfo(examId);
        return examInfo;
    }
}
