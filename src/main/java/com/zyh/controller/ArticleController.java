package com.zyh.controller;

import com.github.pagehelper.PageInfo;
import com.zyh.entity.Article;
import com.zyh.framework.redis.RedisUtils;
import com.zyh.service.ArticleService;
import com.zyh.utils.Result;
import com.zyh.utils.UserThreadLocal;
import com.zyh.vo.UserData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;
    @Autowired
    private RedisUtils redisUtils;

    @PostMapping("create")
    public Result create(@RequestBody Article article){
        String token = UserThreadLocal.get();
        UserData userData = (UserData) redisUtils.get(token);
        article.setCreateDate(new Date());
        article.setCreateUser(userData.getId());
        int flag = articleService.create(article);
        if(flag>0){
            return Result.ok();
        }else{
            return Result.fail();
        }
    }

    @GetMapping("delete")
    public Result delete(String ids){
        int flag = articleService.delete(ids);
        if(flag>0){
            return Result.ok();
        }else{
            return Result.fail();
        }
    }

    @PostMapping("update")
    public Result update(@RequestBody Article article){
        article.setUpdateDate(new Date());
        int flag = articleService.update(article);
        if(flag>0){
            return Result.ok();
        }else{
            return Result.fail();
        }
    }

    @GetMapping("detail")
    public Result detail(Integer id){

        return Result.ok(articleService.detail(id));
    }

    @PostMapping("query")
    public Map<String,Object> query(@RequestBody  Article article){
        PageInfo<Article> pageInfo = articleService.query(article);
        return Result.ok(pageInfo);
    }

}
