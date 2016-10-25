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

    private int userId;
    private String userName;
    private int userType;
    private String password;
    private String fullname;
    private int age;
    private int sex;
    private String graduateInstitution;
    private String major;
    private String workingLife;
    private int userLevel;
    private String workingExp;
    private int orientation;
    private String teleNum;
    private String email;

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }


    public int getUserType() {
        return userType;
    }
    public void setUserType(int userType) {
        this.userType = userType;
    }

    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getFullname() { return fullname; }
    public void setFullname(String fullname) { this.fullname = fullname; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public int getSex() { return sex; }
    public void setSex(int sex) { this.sex = sex; }

    public String getGraduateInstitution() { return graduateInstitution; }
    public void setGraduateInstitution(String graduateInstitution) { this.graduateInstitution = graduateInstitution; }

    public String getMajor() { return major; }
    public void setMajor(String major) { this.major = major; }

    public String getWorkingLife() { return workingLife; }
    public void setWorkingLife(String workingLife) { this.workingLife = workingLife; }

    public int getUserLevel() {
        return userLevel;
    }
    public void setUserLevel(int userLevel) {
        this.userLevel = userLevel;
    }

    public String getWorkingExp() { return workingExp; }
    public void setWorkingExp(String workingExp) { this.workingExp = workingExp; }

    public int getOrientation() { return orientation; }
    public void setOrientation(int orientation) { this.orientation = orientation; }

    public String getTeleNum() { return teleNum; }
    public void setTeleNum(String teleNum) { this.teleNum = teleNum; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}
