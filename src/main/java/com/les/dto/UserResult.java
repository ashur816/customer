package com.les.dto;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/9/6.
 */
public class UserResult implements Serializable{
    private static final long serialVersionUID = -503720886795820883L;

    private String userName;
    private String password;
    private String fullname;
    private String graduateInstitution;
    private int age;
    private String sex;
    private String major;
    private String workingLife;
    private String message;

    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getFullname() { return fullname; }
    public void setFullname(String fullname) { this.fullname = fullname; }

    public String getGraduateInstitution() { return graduateInstitution; }
    public void setGraduateInstitution(String graduateInstitution) { this.graduateInstitution = graduateInstitution; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public String getSex() { return sex; }
    public void setSex(String sex) { this.sex = sex; }

    public String getMajor() { return major; }
    public void setMajor(String major) { this.major = major; }

    public String getWorkingLife() { return workingLife; }
    public void setWorkingLife(String workingLife) { this.workingLife = workingLife; }
}
