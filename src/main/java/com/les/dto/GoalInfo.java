package com.les.dto;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/9/11.
 */
public class GoalInfo implements Serializable {

    private static final long serialVersionUID = 4479041037533337410L;
    private int userId;
    private List<UserGoal> goalList;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<UserGoal> getGoalList() {
        return goalList;
    }

    public void setGoalList(List<UserGoal> goalList) {
        this.goalList = goalList;
    }
}
