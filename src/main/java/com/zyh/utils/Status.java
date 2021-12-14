package com.zyh.utils;

/**
 * @author
 * @date 2021/3/20 11:03
 */
public enum Status {

    SUCCESS(200,"操作成功"),
    ERROR(500,"操作失败"),
    TOKEN_ERROR(402,"超时或者不合法的token"),
    NO_AUTH(401,"没有权限");

    private Integer code;
    private String msg;

    Status(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
