package com.zyh.framework.role;

import com.zyh.framework.exception.AuthException;
import com.zyh.framework.redis.RedisUtils;
import com.zyh.utils.Status;
import com.zyh.utils.UserThreadLocal;
import com.zyh.vo.UserData;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author
 * @date 2021/3/26 15:23
 */
@Component
@Aspect
public class RoleAspect {

    @Autowired
    private RedisUtils redisUtils;

    @Pointcut
    public void pointCut(){

    }

    @Before("execution(* com.zyh.controller.*Controller.*(..))")
    public void before(JoinPoint joinPoint){
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        //通过方法获取注解
        RequiresRoles annotation = method.getAnnotation(RequiresRoles.class);
        if (annotation != null){
            String token = UserThreadLocal.get();
            UserData userData = (UserData) redisUtils.get(token);
            if (userData!=null){
                if (!String.valueOf(userData.getType()).equals(String.valueOf(annotation.type().getCode()))){
                    throw new AuthException(Status.NO_AUTH.getMsg());
                }
            }else {
                throw new AuthException(Status.NO_AUTH.getMsg());
            }
        }
    }
}
