package com.les.dao.mapper;

import com.les.dto.UserRegister;
import com.les.dto.UserResult;
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

    UserResult getUserById(int userId);

    User getUser(@Param("userName")String userName, @Param("password")String password);

    List<UserResult> getUserList(@Param("userId") int userId);

    void register(UserRegister userRegister);

    void updateUser(@Param("userId")int userId,@Param("userName")String userName, @Param("password")String password,
                    @Param("fullname")String fullname,@Param("age") int age, @Param("sex") int sex, @Param("graduateInstitution")String graduateInstitution,@Param("major")String major,
                    @Param("workingLife")String workingLife,@Param("userLevel")int userLevel,@Param("workingExp")String workingExp,@Param("orientation")int orientation,@Param("teleNum")String teleNum,@Param("email")String email);

    void deleteUser(int userId);

    User singleUser(String name);

    void updateUserGoal(@Param("examMaker") String examMaker, @Param("userId") int userId, @Param("totalGoal") String totalGoal);

    void insertUserGoal(@Param("userId") int userId, @Param("startDate") Date startDate);

    UserGoal getLatestUserGoal(int userId);

    void commit(@Param("userId")int userId,@Param("endDate")Date endDate);
}
