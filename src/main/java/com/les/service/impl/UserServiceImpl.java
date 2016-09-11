package com.les.service.impl;

import com.les.common.MapCacheManager;
import com.les.common.StaticConst;
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
import java.util.UUID;

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
    public UserResult login(String userName, String password) {
        User user = userMapper.getUser(userName, password);
        UserResult userResult = new UserResult();
        if (user != null) {
            int userId = user.getUserId();
            int userType = user.getUserType();
            if (StaticConst.USER_TYPE_ADMIN == userType) {
                //重定向管理页
                userResult.setRedirectUrl("http://192.168.30.214/project3/member-list.html");
            } else {
                //根据userId查询用户最近一次考试结果
                UserGoal userGoal = userMapper.getLatestUserGoal(userId);
                Date now = new Date();
                //不能重考的，校验时间，能重考的，插入新的考试记录
                if (userGoal != null && userGoal.getReexamFlag() == 0) {
                    Date startDate = userGoal.getStartTime();
                    //理论结束时间
                    Date needEndDate = DateUtils.addMinutes(startDate, StaticConst.examTime);
                    //needEndDate小于now 返回-1，大于返回1，相等返回0
                    if (needEndDate.compareTo(now) < 0) {//理论结束时间<当前时间 报错
                        userResult.setMessage("考试时间结束，不能重复登陆");
                        userResult.setToken("n");
                        return userResult;
                    }
                } else {//插入新记录
                    userMapper.insertUserGoal(userId, now);
                }
                //重定向考试页
                userResult.setRedirectUrl("http://192.168.30.218/Exam/exam.html");
            }

            //生成uuid，记录到map缓存
            String token = UUID.randomUUID().toString().replace("-", "");
            MapCacheManager cacheManager = MapCacheManager.getInstance();
            cacheManager.updateCache(token, String.valueOf(userId));

            userResult.setToken(token);
        }
        else {
            userResult.setMessage("用户名或密码错误");
            userResult.setToken("p");
        }
        return userResult;
    }

    @Override
    public List<UserResult> getUserList(int userId) {
        return userMapper.getUserList(userId);
    }

    @Override
    public UserResult register(UserRegister userRegister) {
        User user = singleUser(userRegister.getUserName());
        UserResult userResult = new UserResult();
        if (user == null) {
            userMapper.register(userRegister);
            userResult.setUserName(userRegister.getUserName());
            userResult.setUserType(userRegister.getUserType());
            userResult.setPassword(userRegister.getPassword());
            userResult.setFullname(userRegister.getFullname());
            userResult.setGraduateInstitution(userRegister.getGraduateInstitution());
            userResult.setAge(userRegister.getAge());
            userResult.setSex(userRegister.getSex());
            userResult.setMajor(userRegister.getMajor());
            userResult.setWorkingLife(userRegister.getWorkingLife());
            userResult.setMessage("{\"id\": \"u\", \"umessage\":\"注册成功\"}");
        } else {
            userResult.setMessage("{\"id\": \"n\", \"nmessage\":\"用户名已存在\"}");
        }
        return userResult;

    }

    @Override
    public void updateUser(int userId, String userName, int userType, String password, String fullname, int age, String sex, String graduateInstitution, String major, String workingLife) {
        userMapper.updateUser(userId,userName,userType,password,fullname,age,sex,graduateInstitution,major,workingLife);
    }


    @Override
    public void deleteUser(int userId) {
        userMapper.deleteUser(userId);
    }

    @Override
    public User singleUser(String username) {
        return userMapper.singleUser(username);
    }

    @Override
    public void commit(int userId,Date endDate) {
        userMapper.commit(userId,endDate);
    }

}
