package com.zyh.utils;

import com.github.pagehelper.PageInfo;

import java.util.HashMap;
import java.util.Map;

/**
 * @author
 * @date 2021/3/20 11:02
 */
public class Result {

    private Integer code;
    private String msg;
    private Object data;

    public static Result ok(){
        return new Result(Status.SUCCESS.getCode(),Status.SUCCESS.getMsg(),null);
    }
    public static Result ok(Object data){
        return new Result(Status.SUCCESS.getCode(),Status.SUCCESS.getMsg(),data);
    }

    public static Map<String,Object> ok(PageInfo pageInfo){
        Map<String,Object> map = new HashMap<>();
        map.put("code",Status.SUCCESS.getCode());
        map.put("msg",Status.SUCCESS.getMsg());
        map.put("total",pageInfo.getTotal());
        map.put("data",pageInfo.getList());
        return map;
    }

    public static Result fail(){
        return new Result(Status.ERROR.getCode(),Status.ERROR.getMsg(),null);
    }

    public static Result fail(String msg){
        return new Result(Status.ERROR.getCode(),msg,null);
    }

    public static Result fail(Integer code,String msg){return new Result(code,msg,null);}

    public Result(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
