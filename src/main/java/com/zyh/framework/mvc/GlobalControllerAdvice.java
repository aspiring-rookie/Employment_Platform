package com.zyh.framework.mvc;

import com.zyh.framework.exception.AuthException;
import com.zyh.framework.exception.TokenException;
import com.zyh.utils.Result;
import com.zyh.utils.Status;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author
 * @date 2021/3/26 10:12
 * 异常通知
 */
@ControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler(TokenException.class)
    @ResponseBody
    public Result handle(TokenException exception){
        return Result.fail(Status.TOKEN_ERROR.getCode(),Status.TOKEN_ERROR.getMsg());
    }

    @ExceptionHandler(AuthException.class)
    @ResponseBody
    public Result handle(AuthException exception){
        return Result.fail(Status.NO_AUTH.getCode(),Status.NO_AUTH.getMsg());
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public Result handle(RuntimeException exception){
        exception.printStackTrace();
        return Result.fail(exception.getMessage());
    }
}
