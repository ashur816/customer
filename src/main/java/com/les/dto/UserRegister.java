package com.les.dto;

/**
 * Created by Administrator on 2016/9/6.
 */
public class UserRegister {

    private String userName;
    private int userType;
    private String password;
    private String fullname;
    private String graduateInstitution;
    private int age;
    private int sex;
    private String major;
    private String workingLife;
    private int userLevel;
    private String workingExp;
    private int orientation;
    private String teleNum;
    private String email;


    public UserRegister() {
    }

    public UserRegister(String userName, int userType,String password, String fullname, int age, int sex, String graduateInstitution,
                        String major, String workingLife, int userLevel,String workingExp,int orientation,String teleNum,String email) {
        this.userName = userName;
        this.userType = userType;
        this.password = password;
        this.fullname = fullname;
        this.graduateInstitution = graduateInstitution;
        this.age = age;
        this.sex = sex;
        this.major = major;
        this.workingLife = workingLife;
        this.userLevel = userLevel;
        this.workingExp = workingExp;
        this.orientation = orientation;
        this.teleNum = teleNum;
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUserType() { return userType; }

    public void setUserType(int userType) { this.userType = userType; }

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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
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

    public int getUserLevel() { return userLevel; }

    public void setUserLevel(int userLevel) { this.userLevel = userLevel; }

    public String getWorkingExp() { return workingExp; }
    public void setWorkingExp(String workingExp) { this.workingExp = workingExp; }

    public int getOrientation() { return orientation; }
    public void setOrientation(int orientation) { this.orientation = orientation; }

    public String getTeleNum() { return teleNum; }
    public void setTeleNum(String teleNum) { this.teleNum = teleNum; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}
