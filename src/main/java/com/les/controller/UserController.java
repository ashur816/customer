package com.les.controller;

import com.les.common.StaticConst;
import com.les.dto.UserRegister;
import com.les.dto.UserResult;
import com.les.po.User;
import com.les.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

/**
 * @author Lydia
 * @ClassName: UserController
 * @Description:
 * @date 2016/8/24
 */
@Controller
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Resource
    private IUserService userService;

    @RequestMapping(value = "/getUserList", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public List<User> getUserList() {
        logger.info("从数据库读取User集合");
        List<User> userList = userService.getUserList();
        return userList;
    }

    //把下面method改成get请求，可以直接访问http://localhost:8080/login.jsp?userName=lsm&password=1212
//    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
//    @ResponseBody
//    public List<User> login(HttpServletRequest request) {
//        String userName = request.getParameter("userName");
//        String password = request.getParameter("password");
//        User user = userService.login(userName, password);
//        List<User> userList = new ArrayList<>();
//        userList.add(user);
//        return userList;

//        String result = "{\"id\": \"f\", \"fmessage\":\"登陆成功\"}";
//        if (StringUtils.isBlank(userName)) {
//            result = "{\"id\": \"n\", \"nmessage\":\"用户名不能为空\"}";
//        } else if (StringUtils.isBlank(password)) {
//            result = "{\"id\": \"p\", \"pmessage\":\"密码不能为空\"}";
//        } else {
//            try {
//                User user = userService.Login(userName, password);
//                List<User> userList = new ArrayList<User>();
//                userList.add(user);
//                return userList;
//            } catch (Exception e) {
//                result = "{\"id\": \"p\", \"pmessage\":" + e.getMessage() + "\"}";
//            }
//        }
//        return result;
//    }

    @RequestMapping(value = "/login", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public void login(HttpServletRequest request, HttpServletResponse response) {
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        UserResult userResult = userService.login(userName, password);
        if(userResult != null){
            int userType = userResult.getUserType();
            try {
                if (StaticConst.USER_TYPE_ADMIN == userType) {
                    //重定向管理页
                    response.sendRedirect("http://192.168.30.245/project3/member-list.html");
                } else {
                    //重定向考试页
                    response.sendRedirect("https://www.baidu.com/");
                }
            }catch (Exception e){

            }
        }
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public UserResult register(HttpServletRequest request) {
//        String result = "{\"id\": \"u\", \"umessage\":\"注册成功\"}";
        String userName = request.getParameter("userName");
        int userType = Integer.parseInt(request.getParameter("userType"));
        String password = request.getParameter("password");
        String fullname = request.getParameter("fullname");
        int age = Integer.parseInt(request.getParameter("age"));
        String sex = request.getParameter("sex");
        String graduateInstitution = request.getParameter("school");
        String major = request.getParameter("major");
        String workingLife = request.getParameter("year");

        UserRegister userRegister = new UserRegister(userName,userType,password,fullname,age,sex,graduateInstitution,major,workingLife);
        UserResult userResult = userService.register(userRegister);
        return userResult;
    }

    @RequestMapping(value = "/updateUser", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public void updateUser(HttpServletRequest request){
        int userId = Integer.parseInt(request.getParameter("userId"));
        String userName = request.getParameter("userName");
        int userType = Integer.parseInt(request.getParameter("userType"));
        String password = request.getParameter("password");
        String fullname = request.getParameter("fullname");
        int age = Integer.parseInt(request.getParameter("age"));
        String sex = request.getParameter("sex");
        String graduateInstitution = request.getParameter("school");
        String major = request.getParameter("major");
        String workingLife = request.getParameter("year");

        userService.updateUser(userId,userName,userType,password,fullname,age,sex,graduateInstitution,major,workingLife);
    }

    @RequestMapping(value = "/deleteUser", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public void deleteUser(HttpServletRequest request){
        int userId = Integer.parseInt(request.getParameter("userId"));
        userService.deleteUser(userId);

    }


    @RequestMapping(value = "/commit", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public void commit(){
        Date now = new Date();
        userService.commit(now);
    }
}

