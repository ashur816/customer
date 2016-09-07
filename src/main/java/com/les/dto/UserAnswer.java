package com.les.dto;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/9/1.
 */
public class UserAnswer implements Serializable{

    private static final long serialVersionUID = -3589441755411346330L;

    private int examinationId;
    private String examinationQuestion;
    private String examinationScore;
    private int answerId;
    private int userId;
    private String answerContent;
    private String goal;
    private String referenceAnswer;

    public String getReferenceAnswer() { return referenceAnswer; }
    public void setReferenceAnswer(String referenceAnswer) {
        this.referenceAnswer = referenceAnswer;
    }

    public int getExaminationId() {
        return examinationId;
    }
    public void setExaminationId(int examinationId) {
        this.examinationId = examinationId;
    }

    public String getExaminationQuestion() {
        return examinationQuestion;
    }
    public void setExaminationQuestion(String examinationQuestion) {
        this.examinationQuestion = examinationQuestion;
    }

    public String getExaminationScore() {
        return examinationScore;
    }
    public void setExaminationScore(String examinationScore) {
        this.examinationScore = examinationScore;
    }

    public int getAnswerId() {
        return answerId;
    }
    public int getUserId() { return userId;}

    public void setUserId(int userId) { this.userId = userId; }
    public void setAnswerId(int answerId) {
        this.answerId = answerId;
    }

    public String getAnswerContent() {
        return answerContent;
    }
    public void setAnswerContent(String answerContent) {
        this.answerContent = answerContent;
    }

    public String getGoal() {
        return goal;
    }
    public void setGoal(String goal) {
        this.goal = goal;
    }


}

