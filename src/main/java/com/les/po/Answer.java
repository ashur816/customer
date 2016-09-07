package com.les.po;

import java.io.Serializable;

/**
 * @author Lydia
 * @ClassName: Answer
 * @Description:
 * @date 2016/8/30
 */
public class Answer implements Serializable {
    private static final long serialVersionUID = 2120869894112984147L;

    private int answerId;
    private String answerContent;
    private int examinationId;
    private int userId;
    private String goal;

    public void setAnswerId(int answerId) { this.answerId = answerId; }
    public void setAnswerContent(String answerContent) { this.answerContent = answerContent; }
    public void setExaminationId(int examinationId) { this.examinationId = examinationId; }
    public void setUserId(int userId) { this.userId = userId; }

    public int getAnswerId() { return answerId; }
    public String getAnswerContent() { return answerContent; }
    public int getExaminationId() { return examinationId; }
    public int getUserId() { return userId; }

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }
}
