package com.les.po;

import java.io.Serializable;

/**
 * @author Lydia
 * @ClassName: Examination
 * @Description:
 * @date 2016/8/30
 */
public class Examination implements Serializable {
    private static final long serialVersionUID = 2120869894112984147L;

    private int examination_id;
    private String examination_question;
    private String examination_score;
    private String reference_answer;

    public void setExamination_id(int examination_id) {
        this.examination_id = examination_id;
    }
    public void setExamination_question(String examination_question) {
        this.examination_question = examination_question;
    }
    public void setExamination_score(String examination_score) {
        this.examination_score = examination_score;
    }
    public void setReference_answer(String reference_answer) {
        this.reference_answer = reference_answer;
    }

    public int getExamination_id() {
        return examination_id;
    }
    public String getExamination_question() {
        return examination_question;
    }
    public String getExamination_score() { return examination_score; }
    public String getReference_answer() { return reference_answer; }
}
