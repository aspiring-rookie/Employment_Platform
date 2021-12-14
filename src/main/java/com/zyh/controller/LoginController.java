package com.zyh.controller;

import com.zyh.entity.Company;
import com.zyh.entity.Student;
import com.zyh.entity.User;
import com.zyh.framework.redis.RedisUtils;
import com.zyh.framework.role.Role;
import com.zyh.service.CompanyService;
import com.zyh.service.StudentService;
import com.zyh.service.UserService;
import com.zyh.utils.Result;
import com.zyh.vo.UserData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.UUID;

/**
 * @author
 * @date 2021/3/25 11:23
 */
@RestController
public class LoginController {

    @Autowired
    private UserService userService;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private StudentService studentService;

    @Autowired
    private RedisUtils redisUtils;

    @PostMapping("/login")
    public Result login(@RequestBody Map<String,String> map){

        String account = map.get("account");
        String password = map.get("password");
        String type = map.get("type");

        Boolean flag = false;
        UserData userData = new UserData();
        if(String.valueOf(Role.ADMIN.getCode()).equals(type)){//管理员登录
            User login = userService.login(account,password);
            if (login!=null){
                userData.setId(login.getId());
                userData.setAccount(login.getUserName());
                userData.setName(login.getName());
                userData.setType(Role.ADMIN.getCode());
                flag =true;
            }
        }
        if(String.valueOf(Role.COMPANY.getCode()).equals(type)){//企业登录
            Company login = companyService.login(account,password);
            if (login!=null){
                userData.setId(login.getId());
                userData.setAccount(login.getAccount());
                userData.setName(login.getName());
                userData.setType(Role.COMPANY.getCode());
                flag =true;
            }
        }
        if(String.valueOf(Role.STUDENT.getCode()).equals(type)){//学生登录
            Student login = studentService.login(account,password);
            if (login!=null){
                userData.setId(login.getId());
                userData.setAccount(login.getAccount());
                userData.setName(login.getName());
                userData.setType(Role.STUDENT.getCode());
                flag =true;
            }
        }

        if (flag){
            //登录成功的情况
            String token = UUID.randomUUID().toString();
            userData.setToken(token);
            redisUtils.set(token,userData,redisUtils.EXPR);
            return Result.ok(userData);
        }else{
            return Result.fail("用户名或密码错误！");
        }
    }

}
