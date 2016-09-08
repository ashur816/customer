package com.les.controller;

import com.les.dto.UserAnswer;
import com.les.po.Answer;
import com.les.service.IAnswerService;
import com.les.service.IUserService;
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
import java.util.LinkedHashMap;
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
    public String insertAnswer(@RequestBody String body) {
        String result = "保存成功";

        Answer answer = (Answer) JSONObject.toBean(JSONObject.fromObject(body), Answer.class);
        String answerContent = answer.getAnswerContent();
        int examinationId = answer.getExaminationId();
        int userId = answer.getUserId();
        answerService.insertAnswer(answerContent, examinationId, userId);
        return result;
    }

    /**
     * 查询
     * @param body
     * @return List<UserAnswer>
     */
    @RequestMapping(value = "/getAnswerList", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public List<UserAnswer> getAnswerList(@RequestBody String body) throws Exception {
        String userId = JsonUtils.readValueByName(body, "userId");
        return answerService.getAnswerList(Integer.parseInt(userId));
    }

    /**
     * 更新评分
     * @param body json体 list样式：["user_id":1,{"answer_id":1,"goal":2.0},{"answer_id":2,"goal":3.0}]
     * @return void
     * @Description: 打分
     */
    @RequestMapping(value = "/grade", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String grade(@RequestBody String body) throws Exception {
        List<LinkedHashMap> list = JsonUtils.readValue(body, List.class);
        String totalGoal = answerService.updateAnswerBatch(list);
        int userId = Integer.parseInt(JsonUtils.readValueByName(body, "user_id"));
        userService.insertGoal(userId,totalGoal);
        return totalGoal;
    }
}
