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

    private int examinationId;
    private String examinationQuestion;
    private String examinationScore;
    private String referenceAnswer;

    public void setExaminationId(int examinationId) {
        this.examinationId = examinationId;
    }
    public void setExaminationQuestion(String examinationQuestion) {
        this.examinationQuestion = examinationQuestion;
    }
    public void setExaminationScore(String examinationScore) {
        this.examinationScore = examinationScore;
    }
    public void setReferenceAnswer(String referenceAnswer) {
        this.referenceAnswer = referenceAnswer;
    }

    public int getExaminationId() {
        return examinationId;
    }
    public String getExaminationQuestion() {
        return examinationQuestion;
    }
    public String getExaminationScore() { return examinationScore; }
    public String getReferenceAnswer() { return referenceAnswer; }
}
