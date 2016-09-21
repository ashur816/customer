package com.les.service;

import com.les.dto.UserRegister;
import com.les.dto.UserResult;
import com.les.po.User;

import java.util.Date;
import java.util.List;

/**
 * @author Lydia
 * @ClassName: IUserService
 * @Description:
 * @date 2016/8/25
 */
public interface IUserService {

    public UserResult login(String userName,String password);
    public List<UserResult> getUserList(int userId);
    public UserResult register(UserRegister userRegister);
    public void updateUser(int userId,String userName,int userType,String password, String fullname,int age, String sex,  String graduateInstitution, String major,String workingLife,int userLevel);
    public void deleteUser(int userId);
    public User singleUser(String username);
    public void commit(int userId,Date endDate);
    public UserResult getUserById(int userId);
}
