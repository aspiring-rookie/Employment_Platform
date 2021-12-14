package com.zyh.utils;

import com.zyh.vo.UserData;

/**
 * @author
 * @date 2021/3/26 15:43
 */
public class UserThreadLocal {

    //存放本地线程变量
    private static ThreadLocal<String> userThreadLocal = new ThreadLocal<>();

    public static  void set(String token){
        userThreadLocal.set(token);
    }

    public static String  get(){
        String token = userThreadLocal.get();
        return token;
    }

    public static void remove(){
        userThreadLocal.remove();
    }
}
