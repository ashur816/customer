package com.les.service;

import com.les.dto.UserRegister;
import com.les.po.User;

import java.util.List;

/**
 * @author Lydia
 * @ClassName: IUserService
 * @Description:
 * @date 2016/8/25
 */
public interface IUserService {

    public User login(String userName,String password);
    public List<User> getUserList();
    public void register(UserRegister userRegister);
    public User singleUser(String username);
    public void insertGoal(int userId,String totalGoal);
}
