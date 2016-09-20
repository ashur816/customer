package com.les.common;

/**
 * @ClassName: StaticConst
 * @Description: 静态常量
 * @author lydia
 * @date 2016/9/7 17:12
 */
public class StaticConst {

    //考试时长 单位分钟
    public static int examTime = 40;

    //用户类型 userType
    public static int USER_TYPE_ADMIN = 1;
    public static int USER_TYPE_EMPLOYEE = 2;

    //页面地址
    //阅卷页面
    public static String PAGE_ADMIN_MANAGE = "http://localhost:63342/customer/web/WEB-INF/project3/member-list.html";
    //考试页面
    public static String PAGE_EXAM = "http://localhost:63342/customer/web/WEB-INF/project3/exam.html";
}
