package com.les.dto;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/9/11.
 */
public class UserGoal implements Serializable {
    private static final long serialVersionUID = 497054671098662010L;

    private int answerId;

    private double goal;

    public int getAnswerId() {
        return answerId;
    }

    public void setAnswerId(int answerId) {
        this.answerId = answerId;
    }

    public double getGoal() {
        return goal;
    }

    public void setGoal(double goal) {
        this.goal = goal;
    }
}
