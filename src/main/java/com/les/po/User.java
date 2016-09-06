package com.les.po;

import java.io.Serializable;

/**
 * @author Lydia
 * @ClassName: User
 * @Description:
 * @date 2016/8/24
 */
public class User implements Serializable{
    private static final long serialVersionUID = 2120869894112984147L;

    private int user_Id;
    private String user_Name;
    private String password;
    private String fullname;
    private String graduate_institution;
    private String major;
    private String working_life;

    public int getUser_Id() { return user_Id; }
    public void setUser_Id(int user_Id) { this.user_Id = user_Id; }

    public String getUser_Name() { return user_Name; }
    public void setUser_Name(String user_Name) { this.user_Name = user_Name; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getFullname() { return fullname; }
    public void setFullname(String name) { this.fullname = fullname; }

    public String getGraduate_institution() { return graduate_institution; }
    public void setGraduate_institution(String graduate_institution) { this.graduate_institution = graduate_institution; }

    public String getMajor() { return major; }
    public void setMajor(String address) { this.major = major; }

    public String getWorking_life() { return working_life; }
    public void setWorking_life(String working_life) { this.working_life = working_life; }
}
