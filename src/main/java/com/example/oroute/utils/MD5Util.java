package com.example.oroute.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
* @Author mini_oj
* @category MD5
* @Description MD5加密工具类
* @Date 2020-04-04 19:00
* @Param
**/
public class MD5Util {
    public static String encode(String password) {

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());

            byte[] byteDigest = md.digest();

            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < byteDigest.length; offset++) {
                i = byteDigest[offset];

                if (i < 0) {
                    i += 256;
                }
                if (i < 16) {
                    buf.append("0");
                }

                buf.append(Integer.toHexString(i));
            } // for

            // 32位加密
            return buf.toString();
            // 16位加密
            // return buf.toString().substring(8, 24);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    // 测试main方法
    public static void main(String[] args) {
        System.out.println(new String("yangxj"));
    }
}