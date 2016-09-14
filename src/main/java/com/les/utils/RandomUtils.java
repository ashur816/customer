package com.les.utils;

import java.util.Random;

/**
 * 生成随机数
 * @author lydia
 *
 */
public class RandomUtils {

    public static final String numberChar = "123456789";

    public static String generateNumString(int length) {
        StringBuffer sb = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(numberChar.charAt(random.nextInt(numberChar.length())));
        }
        return sb.toString();
    }

    /**
     * 小于number 的一个随机数
     * @param number
     * @return
     */
    public static String generateLimitNumString(Integer number) {
        Random random = new Random();
        Integer irandom = random.nextInt(number);
        return irandom.toString();
    }

    public static String generateZeroString(int length) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            sb.append('0');
        }
        return sb.toString();
    }

}
