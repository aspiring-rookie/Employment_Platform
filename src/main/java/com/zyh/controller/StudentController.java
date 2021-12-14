package com.zyh.controller;

import com.github.pagehelper.PageInfo;
import com.zyh.entity.Student;
import com.zyh.framework.redis.RedisUtils;
import com.zyh.framework.role.RequiresRoles;
import com.zyh.framework.role.Role;
import com.zyh.service.StudentService;
import com.zyh.utils.Result;
import com.zyh.utils.UserThreadLocal;
import com.zyh.vo.UserData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;
    @Autowired
    private RedisUtils redisUtils;

    @PostMapping("create")
    public Result create(@RequestBody Student student){
        int flag = studentService.create(student);
        if(flag>0){
            return Result.ok();
        }else{
            return Result.fail();
        }
    }

    @GetMapping("delete")
    public Result delete(String ids){
        int flag = studentService.delete(ids);
        if(flag>0){
            return Result.ok();
        }else{
            return Result.fail();
        }
    }

    @PostMapping("update")
    public Result update(@RequestBody Student student){
        int flag = studentService.update(student);
        if(flag>0){
            return Result.ok();
        }else{
            return Result.fail();
        }
    }

    @GetMapping("detail")
    public Result detail(Integer id){
        return Result.ok(studentService.detail(id));
    }

    @PostMapping("query")
    public Map<String,Object> query(@RequestBody  Student student){
        PageInfo<Student> pageInfo = studentService.query(student);
        return Result.ok(pageInfo);
    }

    @PostMapping("info")
    @RequiresRoles(type = Role.STUDENT)
    public Result info(){
        String token = UserThreadLocal.get();
        UserData userData = (UserData) redisUtils.get(token);
        return Result.ok(studentService.detail(userData.getId()));
    }

}
