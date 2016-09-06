package com.les.utils;

import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;

/**
 * @author Lydia
 * @ClassName: MD5Utils
 * @Description:
 * @date 2016/9/2
 */
public class MD5Utils {
//    passwrod = MD5II(Base64.encodeBase64String("XXX"));

    public static String MD5II(String plain) {
        return Hashing.md5().newHasher().putString(plain, Charsets.UTF_8).hash().toString();
    }

}
