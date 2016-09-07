package com.les.dto;

/**
 * Created by Administrator on 2016/9/6.
 */
public class UserRegister {

    private String userName;
    private String password;
    private String fullname;
    private String graduateInstitution;
    private int age;
    private String sex;
    private String major;
    private String workingLife;

    public UserRegister(String userName, String password, String fullname, String graduateInstitution, String major, String workingLife) {
        this.userName = userName;

    public UserRegister() {
    }

    public UserRegister(String user_name, String password, String fullname, int age, String sex, String graduate_institution, String major, String working_life) {
        this.user_name = user_name;
        this.password = password;
        this.fullname = fullname;
        this.graduateInstitution = graduateInstitution;
        this.age = age;
        this.sex = sex;
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
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
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
