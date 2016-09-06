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

    private int answer_id;
    private String answer_content;
    private int examination_id;
    private int user_id;
    private String goal;

    public void setAnswer_id(int answer_id) { this.answer_id = answer_id; }
    public void setAnswer_content(String answer_content) { this.answer_content = answer_content; }
    public void setExamination_id(int examination_id) { this.examination_id = examination_id; }
    public void setUser_id(int user_id) { this.user_id = user_id; }

    public int getAnswer_id() { return answer_id; }
    public String getAnswer_content() { return answer_content; }
    public int getExamination_id() { return examination_id; }
    public int getUser_id() { return user_id; }

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }
}
