package com.les.dto;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/9/6.
 */
public class UserResult implements Serializable{
    private static final long serialVersionUID = -503720886795820883L;

    private String user_name;
    private String password;
    private String fullname;
    private String graduate_institution;
    private String major;
    private String working_life;

    public String getUser_name() { return user_name; }
    public void setUser_name(String user_name) { this.user_name = user_name; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getFullname() { return fullname; }
    public void setFullname(String fullname) { this.fullname = fullname; }

    public String getGraduate_institution() { return graduate_institution; }
    public void setGraduate_institution(String graduate_institution) { this.graduate_institution = graduate_institution; }

    public String getMajor() { return major; }
    public void setMajor(String major) { this.major = major; }

    public String getWorking_life() { return working_life; }
    public void setWorking_life(String working_life) { this.working_life = working_life; }
}
