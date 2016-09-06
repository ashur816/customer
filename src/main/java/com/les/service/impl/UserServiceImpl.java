package com.les.service.impl;

import com.les.dao.mapper.UserMapper;
import com.les.dto.UserRegister;
import com.les.dto.UserResult;
import com.les.po.User;
import com.les.service.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Lydia
 * @ClassName: UserServiceImpl
 * @Description:
 * @date 2016/8/24
 */
@Service
public class UserServiceImpl implements IUserService
{
    /*@Autowired
    private JdbcTemplate jdbcTemplate;*/

    @Resource
    private UserMapper userMapper;

    @Override
    public User Login(String userName, String password) {
        return userMapper.getUser(userName,password);

//        if (StringUtils.isBlank(userName)) {
//            throw new Exception("用户名不能为空");
//        }
//        else if(StringUtils.isBlank(password)){
//            throw new Exception("密码不能为空");
//        }
//        User user = userMapper.getUserByName(userName);
//        if (password.equals(user.getPassword())) {
//            //登陆成功 可以存到缓存里面
//        }
//        else {
//            throw new Exception("用户名或密码不正确");
//        }
//        return user;
    }

    @Override
    public List<User> getUserList(){ return userMapper.getUserList(); }

    @Override
    public void register(UserRegister userRegister) {
        User user = singleUser(userRegister.getUser_name());
        UserResult userResult = new UserResult();
        if(user == null){
            userMapper.register(userRegister);
            userResult.setFullname(userRegister.getFullname());
            userResult.setGraduate_institution(userRegister.getGraduate_institution());
            userResult.setMajor(userRegister.getMajor());
            userResult.setWorking_life(userRegister.getWorking_life());
        }
        else{

        }


    }

    @Override
    public User singleUser(String username) {
        return userMapper.singleUser(username);
    }

    @Override
    public void insertGoal(int userId,String totalGoal) {
        userMapper.insertGoal(userId,totalGoal);
    }

}
