package com.zyh.controller;

import com.github.pagehelper.PageInfo;
import com.zyh.entity.User;
import com.zyh.framework.role.RequiresRoles;
import com.zyh.framework.role.Role;
import com.zyh.service.UserService;
import com.zyh.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("create")
    @RequiresRoles(type = Role.ADMIN)
    public Result create(@RequestBody User user){
        int flag = userService.create(user);
        if(flag>0){
            return Result.ok();
        }else{
            return Result.fail();
        }
    }

    @GetMapping("delete")
    public Result delete(String ids){
        int flag = userService.delete(ids);
        if(flag>0){
            return Result.ok();
        }else{
            return Result.fail();
        }
    }

    @PostMapping("update")
    public Result update(@RequestBody User user){
        int flag = userService.update(user);
        if(flag>0){
            return Result.ok();
        }else{
            return Result.fail();
        }
    }

    @GetMapping("detail")
    public Result detail(Integer id){
        return Result.ok(userService.detail(id));
    }

    @PostMapping("query")
    @RequiresRoles(type = Role.ADMIN)
    public Map<String,Object> query(@RequestBody  User user){
        PageInfo<User> pageInfo = userService.query(user);
        return Result.ok(pageInfo);
    }

}
