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
    private String sex;
    private String graduateInstitution;
    private String major;
    private String workingLife;

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

    public String getSex() { return sex; }
    public void setSex(String sex) { this.sex = sex; }

    public String getGraduateInstitution() { return graduateInstitution; }
    public void setGraduateInstitution(String graduateInstitution) { this.graduateInstitution = graduateInstitution; }

    public String getMajor() { return major; }
    public void setMajor(String major) { this.major = major; }

    public String getWorkingLife() { return workingLife; }
    public void setWorkingLife(String workingLife) { this.workingLife = workingLife; }
}
