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

    //题目分数
    public static int EXAMINATION_SCORE[] = {2,5,10};
    //题目数量
    public static int EXAMINATION_NUM1[] = {25,6,2};
    public static int EXAMINATION_NUM2[] = {10,10,3};
    public static int EXAMINATION_NUM3[] = {10,6,5};

    //页面地址
    //阅卷页面
    public static String PAGE_ADMIN_MANAGE = "/member-list.html";

    //考试页面
    public static String PAGE_EXAM = "/exam.html";

    //试题维护界面
    public static String PAGE_EXAM_MANAGE = "/exam-manage.html";
}
