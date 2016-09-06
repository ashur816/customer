package com.les.po;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Administrator on 2016/9/5.
 */
public class UserGoal implements Serializable{
    private static final long serialVersionUID = 991645840664690855L;
    private int goal_id;
    private String total_goal;
    private int user_id;
    private Date start_time;
    private Date end_time;
    private int exam_flag;
    private int reexam_flag;

    public int getGoal_id() { return goal_id; }
    public void setGoal_id(int goal_id) { this.goal_id = goal_id; }

    public String getTotal_goal() { return total_goal; }
    public void setTotal_goal(String total_goal) { this.total_goal = total_goal; }

    public int getUser_id() { return user_id; }
    public void setUser_id(int user_id) { this.user_id = user_id; }

    public Date getStart_time() { return start_time; }
    public void setStart_time(Date start_time) { this.start_time = start_time; }

    public Date getEnd_time() { return end_time; }
    public void setEnd_time(Date end_time) { this.end_time = end_time; }

    public int getExam_flag() { return exam_flag; }
    public void setExam_flag(int exam_flag) { this.exam_flag = exam_flag; }

    public int getReexam_flag() { return reexam_flag; }
    public void setReexam_flag(int reexam_flag) { this.reexam_flag = reexam_flag; }
}
