package com.les.dto;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/9/1.
 */
public class UserAnswer implements Serializable{

    private static final long serialVersionUID = -3589441755411346330L;

    private int examination_id;
    private String examination_question;
    private String examination_score;
    private int answer_id;
    private int user_id;
    private String answer_content;
    private String goal;
    private String reference_answer;

    public String getReference_answer() { return reference_answer; }
    public void setReference_answer(String reference_answer) {
        this.reference_answer = reference_answer;
    }

    public int getExamination_id() {
        return examination_id;
    }
    public void setExamination_id(int examination_id) {
        this.examination_id = examination_id;
    }

    public String getExamination_question() {
        return examination_question;
    }
    public void setExamination_question(String examination_question) {
        this.examination_question = examination_question;
    }

    public String getExamination_score() {
        return examination_score;
    }
    public void setExamination_score(String examination_score) {
        this.examination_score = examination_score;
    }

    public int getAnswer_id() {
        return answer_id;
    }
    public int getUser_id() { return user_id;}

    public void setUser_id(int user_id) { this.user_id = user_id; }
    public void setAnswer_id(int answer_id) {
        this.answer_id = answer_id;
    }

    public String getAnswer_content() {
        return answer_content;
    }
    public void setAnswer_content(String answer_content) {
        this.answer_content = answer_content;
    }

    public String getGoal() {
        return goal;
    }
    public void setGoal(String goal) {
        this.goal = goal;
    }


}

