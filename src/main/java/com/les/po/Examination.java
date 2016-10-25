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
    private int examinationLevel;
    private int examOrientation;

    public void setExaminationId(int examinationId) {
        this.examinationId = examinationId;
    }
    public int getExaminationId() {
        return examinationId;
    }

    public void setExaminationQuestion(String examinationQuestion) {
        this.examinationQuestion = examinationQuestion;
    }
    public String getExaminationQuestion() {
        return examinationQuestion;
    }

    public void setExaminationScore(String examinationScore) {
        this.examinationScore = examinationScore;
    }
    public String getExaminationScore() { return examinationScore; }

    public void setReferenceAnswer(String referenceAnswer) {
        this.referenceAnswer = referenceAnswer;
    }
    public String getReferenceAnswer() { return referenceAnswer; }

    public int getExaminationLevel() {
        return examinationLevel;
    }
    public void setExaminationLevel(int examinationLevel) {
        this.examinationLevel = examinationLevel;
    }

    public int getExamOrientation() { return examOrientation; }
    public void setExamOrientation(int examOrientation) { this.examOrientation = examOrientation; }
}
