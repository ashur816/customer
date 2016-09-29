package com.les.controller;

import com.les.dto.GoalInfo;
import com.les.dto.UserAnswer;
import com.les.po.Answer;
import com.les.service.IAnswerService;
import com.les.service.IUserService;
import com.les.utils.JsonUtils;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author Lydia
 * @ClassName: AnswerController
 * @Description:
 * @date 2016/8/30
 */
@Controller
public class AnswerController {
    private static final Logger logger = LoggerFactory.getLogger(AnswerController.class);

    @Resource
    private IAnswerService answerService;
    @Resource
    private IUserService userService;

    /**
     * 提交答案
     *
     * @param body
     * @return
     */
    @RequestMapping(value = "/insertAnswer", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String insertAnswer(HttpServletRequest request, @RequestBody String body) {
        String result = "保存成功";
//        String result = "{\"message\":\"保存成功\"}";

        Answer answer = (Answer) JSONObject.toBean(JSONObject.fromObject(body), Answer.class);
        String answerContent = answer.getAnswerContent();
        int examinationId = answer.getExaminationId();
        int userId = Integer.parseInt(request.getAttribute("loginUserId").toString());
        answerService.insertAnswer(answerContent, examinationId, userId);
        return result;
    }

    /**
     * 查询
     *
     * @param request
     * @return List<UserAnswer>
     */
    @RequestMapping(value = "/getAnswerList", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public List<UserAnswer> getAnswerList(HttpServletRequest request) throws Exception {
        String userId = request.getParameter("userId");
        return answerService.getAnswerList(Integer.parseInt(userId));
    }

    /**
     * 批量更新评分
     *
     * @param body json体 list样式：{"userId":4,"goalList":[{"answerId":22,"goal":3.0},{"answerId":25,"goal":3.0}]}
     * @return GoalInfo
     * @Description: 打分
     */
    @RequestMapping(value = "/grade", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public GoalInfo gradeBatch(HttpServletRequest request, @RequestBody String body) throws Exception {
        Object loginUserId = request.getAttribute("loginUserId");
        GoalInfo goalInfo = JsonUtils.readValue(body, GoalInfo.class);
        goalInfo = answerService.updateAnswerBatch(Integer.parseInt(loginUserId.toString()), goalInfo);
        return goalInfo;
    }

    /**
     * 单个更新评分
     *
     * @return GoalInfo
     * @Description: 打分
     */
    @RequestMapping(value = "/gradeSingle", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String gradeSingle(HttpServletRequest request) throws Exception {
        String answerId = request.getParameter("answerId");
        String goal = request.getParameter("goal");
        String retMsg = "{\"message\":\"保存成功\"}";
        if (StringUtils.isBlank(answerId) || answerId.equals("null")) {
            retMsg = "{\"message\":\"答案ID不能为空\"}";
        } else if (StringUtils.isBlank(goal) || goal.equals("null")) {
            retMsg = "{\"message\":\"得分不能为空\"}";
        } else {
            answerService.gradeAnswer(Integer.parseInt(answerId), Integer.parseInt(goal));
        }
        return retMsg;
    }

    /**
     * @return List<Examination>
     * @Description: 根据主键获取题目及用户答案
     */
    @RequestMapping(value = "/getUserAnswerInfo", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public UserAnswer getExamInfo(HttpServletRequest request) {
        String userId = request.getParameter("userId");
        String examId = request.getParameter("examId");
        UserAnswer userAnswer = answerService.getUserExamAnswer(Integer.parseInt(examId), Integer.parseInt(userId));
        return userAnswer;
    }

    /**
     * @return String
     * @Description: 计算考生总成绩
    */
    @RequestMapping(value = "/totalGoal", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public GoalInfo totalGoal(HttpServletRequest request){
        String userId = request.getParameter("userId");
        int loginUserId = Integer.parseInt(request.getAttribute("loginUserId").toString());
        return answerService.totalGoal(Integer.parseInt(userId),loginUserId);
    }
}
