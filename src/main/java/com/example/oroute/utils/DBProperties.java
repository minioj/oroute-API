package com.example.oroute.utils;

import java.io.InputStream;
import java.util.Properties;

public class DBProperties {

    private static Properties config = new Properties(); // 单例

    // 类加载时通过类加载器读取类路径下的配置文件
    static {
        try {
            InputStream in = DBProperties.class.getClassLoader().getResourceAsStream("application.properties");// 通过类加载器获得类路径下该属性文件的一个输入流
            config.load(in);// 从输入流中读取属性列表
            in.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getString(String key) {
        return config.getProperty(key);
    }


    public static String getString(String key, String defaultValue) {
        return config.getProperty(key, defaultValue);
    }

    public static Properties getInstance() {
        return config;
    }
}