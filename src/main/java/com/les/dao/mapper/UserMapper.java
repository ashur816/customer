package com.les.dao.mapper;

import com.les.dto.UserRegister;
import com.les.po.User;
import com.les.po.UserGoal;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * @author Lydia
 * @ClassName: UserMapper
 * @Description:
 * @date 2016/8/24
 */
@Component
public interface UserMapper {

    User getUser(@Param("userName") String userName, @Param("password") String password);

    List<User> getUserList();

    void register(UserRegister userRegister);

    User singleUser(String name);

    void insertGoal(@Param("userId") int userId, @Param("totalGoal") String totalGoal);

    void insertUserGoal(@Param("userId") int userId, @Param("startDate") Date startDate);

    UserGoal getLatestUserGoal(int userId);

    void commit(Date endDate);
}
