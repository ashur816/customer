package com.les.dto;

/**
 * Created by Administrator on 2016/9/6.
 */
public class UserRegister {

    private String userName;
    private String password;
    private String fullname;
    private String graduateInstitution;
    private String major;
    private String workingLife;

    public UserRegister(String userName, String password, String fullname, String graduateInstitution, String major, String workingLife) {
        this.userName = userName;
        this.password = password;
        this.fullname = fullname;
        this.graduateInstitution = graduateInstitution;
        this.major = major;
        this.workingLife = workingLife;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getGraduateInstitution() {
        return graduateInstitution;
    }

    public void setGraduateInstitution(String graduateInstitution) {
        this.graduateInstitution = graduateInstitution;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getWorkingLife() {
        return workingLife;
    }

    public void setWorkingLife(String workingLife) {
        this.workingLife = workingLife;
    }
}
