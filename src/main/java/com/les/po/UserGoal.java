package com.les.po;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Administrator on 2016/9/5.
 */
public class UserGoal implements Serializable{
    private static final long serialVersionUID = 991645840664690855L;
    private int goalId;
    private String totalGoal;
    private int userId;
    private Date startTime;
    private Date endTime;
    private int examFlag;
    private int reexamFlag;

    public int getGoalId() { return goalId; }
    public void setGoalId(int goalId) { this.goalId = goalId; }

    public String getTotalGoal() { return totalGoal; }
    public void setTotalGoal(String totalGoal) { this.totalGoal = totalGoal; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public Date getStartTime() { return startTime; }
    public void setStartTime(Date startTime) { this.startTime = startTime; }

    public Date getEndTime() { return endTime; }
    public void setEndTime(Date endTime) { this.endTime = endTime; }

    public int getExamFlag() { return examFlag; }
    public void setExamFlag(int examFlag) { this.examFlag = examFlag; }

    public int getReexamFlag() { return reexamFlag; }
    public void setReexamFlag(int reexamFlag) { this.reexamFlag = reexamFlag; }
}
