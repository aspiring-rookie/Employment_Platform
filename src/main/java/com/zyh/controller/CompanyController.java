package com.zyh.controller;

import com.github.pagehelper.PageInfo;
import com.zyh.entity.Company;
import com.zyh.framework.redis.RedisUtils;
import com.zyh.framework.role.RequiresRoles;
import com.zyh.framework.role.Role;
import com.zyh.service.CompanyService;
import com.zyh.utils.Result;
import com.zyh.utils.UserThreadLocal;
import com.zyh.vo.UserData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private CompanyService companyService;

    @PostMapping("create")
    public Result create(@RequestBody Company company){
        int flag = companyService.create(company);
        if(flag>0){
            return Result.ok();
        }else{
            return Result.fail();
        }
    }

    @GetMapping("delete")
    public Result delete(String ids){
        int flag = companyService.delete(ids);
        if(flag>0){
            return Result.ok();
        }else{
            return Result.fail();
        }
    }

    @PostMapping("update")
    public Result update(@RequestBody Company company){
        int flag = companyService.update(company);
        if(flag>0){
            return Result.ok();
        }else{
            return Result.fail();
        }
    }

    @GetMapping("detail")
    public Result detail(Integer id){
        return Result.ok(companyService.detail(id));
    }

    @PostMapping("query")
    public Map<String,Object> query(@RequestBody  Company company){
        PageInfo<Company> pageInfo = companyService.query(company);
        return Result.ok(pageInfo);
    }

    @PostMapping("companyInfo")
    @RequiresRoles(type = Role.COMPANY)
    public Result companyInfo(){
        String token = UserThreadLocal.get();
        UserData userData = (UserData) redisUtils.get(token);
        return Result.ok(companyService.detail(userData.getId()));
    }

}
