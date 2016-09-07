package com.les.dto;

/**
 * Created by Administrator on 2016/9/6.
 */
public class UserRegister {

    private String user_name;
    private String password;
    private String fullname;
    private int age;
    private String sex;
    private String graduate_institution;
    private String major;
    private String working_life;


    public UserRegister() {
    }

    public UserRegister(String user_name, String password, String fullname, int age, String sex, String graduate_institution, String major, String working_life) {
        this.user_name = user_name;
        this.password = password;
        this.fullname = fullname;
        this.age = age;
        this.sex = sex;
        this.graduate_institution = graduate_institution;
        this.major = major;
        this.working_life = working_life;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
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

    public String getGraduate_institution() {
        return graduate_institution;
    }

    public void setGraduate_institution(String graduate_institution) {
        this.graduate_institution = graduate_institution;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getWorking_life() {
        return working_life;
    }

    public void setWorking_life(String working_life) {
        this.working_life = working_life;
    }
}
