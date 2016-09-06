package com.les.controller;

import com.les.dto.UserRegister;
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
import java.util.ArrayList;
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

    @RequestMapping("/getUserList")
    @ResponseBody
    public List<User> getUserList() {
        logger.info("从数据库读取User集合");
        //ss
        List<User> userList = userService.getUserList();
        return userList;
    }

    //把下面method改成get请求，可以直接访问http://localhost:8080/login.jsp?userName=lsm&password=1212
    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public List<User> login(HttpServletRequest request) {
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        User user = userService.login(userName, password);
        List<User> userList = new ArrayList<>();
        userList.add(user);
        return userList;

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
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String register(HttpServletRequest request) {
        String result = "{\"id\": \"u\", \"umessage\":\"注册成功\"}";
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        String fullname = request.getParameter("fullname");
        String graduateInstitution = request.getParameter("graduateInstitution");
        String major = request.getParameter("major");
        String workingLife = request.getParameter("workingLife");
        UserRegister userRegister = new UserRegister(userName, password, fullname, graduateInstitution, major, workingLife);
        userService.register(userRegister);
        return result;
    }
}

