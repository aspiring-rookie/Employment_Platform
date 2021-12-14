package com.zyh.service;

import com.zyh.framework.exception.TokenException;
import com.zyh.framework.redis.RedisUtils;
import com.zyh.utils.Status;
import com.zyh.utils.UserThreadLocal;
import com.zyh.vo.UserData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author
 * @date 2021/4/9 12:00
 */
@Service
public class UserDataService {

    @Autowired
    private RedisUtils redisUtil;

    public UserData getUserData(){
        String token = UserThreadLocal.get();
        UserData userData = (UserData) redisUtil.get(token);
        if (userData != null){
            return userData;
        }else{
            throw new TokenException(Status.TOKEN_ERROR.getMsg());
        }
    }
}
