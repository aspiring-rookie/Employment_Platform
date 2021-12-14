package com.zyh.framework.exception;

/**
 * @author
 * @date 2021/3/25 18:00
 */

//没有权限的exception
public class AuthException extends RuntimeException{

    public AuthException(String message) {

        super(message);
    }
}
