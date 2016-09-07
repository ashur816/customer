package com.les.service.impl;

import com.les.dao.mapper.UserMapper;
import com.les.dto.UserRegister;
import com.les.dto.UserResult;
import com.les.po.User;
import com.les.po.UserGoal;
import com.les.service.IUserService;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author Lydia
 * @ClassName: UserServiceImpl
 * @Description:
 * @date 2016/8/24
 */
@Service
public class UserServiceImpl implements IUserService {
    /*@Autowired
    private JdbcTemplate jdbcTemplate;*/

    @Resource
    private UserMapper userMapper;

    @Override
    public User login(String userName, String password) {
        User user = userMapper.getUser(userName, password);
        if (user != null) {
            int userId = user.getUserId();
            //根据userId查询用户最近一次考试结果
            UserGoal userGoal = userMapper.getLatestUserGoal(userId);
            Date now = new Date();
            //不能重考的，校验时间，能重考的，插入新的考试记录
            if (userGoal != null && userGoal.getReexamFlag() == 0) {
                Date startDate = userGoal.getStartTime();
                //理论结束时间
                Date needEndDate = DateUtils.addMinutes(startDate, 40);
                //needEndDate小于now 返回-1，大于返回1，相等返回0
                if(needEndDate.compareTo(now) < 0){//理论结束时间<当前时间 报错
                    System.out.println("已经超过考试时间，不能登陆");
                }
            }
            else {//插入新记录
                userMapper.insertUserGoal(userId, now);
            }
        }
        return user;

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
    public List<User> getUserList() {
        return userMapper.getUserList();
    }

    @Override
    public void register(UserRegister userRegister) {
        User user = singleUser(userRegister.getUserName());
        UserResult userResult = new UserResult();
        if (user == null) {
            userMapper.register(userRegister);
            userResult.setUserName(userRegister.getUserName());
            userResult.setPassword(userRegister.getPassword());
            userResult.setFullname(userRegister.getFullname());
            userResult.setGraduateInstitution(userRegister.getGraduateInstitution());
            userResult.setAge(userRegister.getAge());
            userResult.setSex(userRegister.getSex());
            userResult.setMajor(userRegister.getMajor());
            userResult.setWorkingLife(userRegister.getWorkingLife());
            userResult.setMessage("{\"id\": \"u\", \"umessage\":\"注册成功\"}");
        else{
            userResult.setMessage("{\"id\": \"n\", \"nmessage\":\"用户名已存在\"}");
        }
        return userResult;

    }

    @Override
    public User singleUser(String username) {
        return userMapper.singleUser(username);
    }

    @Override
    public void insertGoal(int userId, String totalGoal) {
        userMapper.insertGoal(userId, totalGoal);
    }

    @Override
    public void commit(Date endDate) {
        userMapper.commit(endDate);
    }

}
