package com.zyh.controller;

import com.github.pagehelper.PageInfo;
import com.zyh.entity.Dict;
import com.zyh.service.DictService;
import com.zyh.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/dict")
public class DictController {

    @Autowired
    private DictService dictService;

    @PostMapping("create")
    public Result create(@RequestBody Dict dict){
        int flag = dictService.create(dict);
        if(flag>0){
            return Result.ok();
        }else{
            return Result.fail();
        }
    }

    @GetMapping("delete")
    public Result delete(String ids){
        int flag = dictService.delete(ids);
        if(flag>0){
            return Result.ok();
        }else{
            return Result.fail();
        }
    }

    @PostMapping("update")
    public Result update(@RequestBody Dict dict){
        int flag = dictService.update(dict);
        if(flag>0){
            return Result.ok();
        }else{
            return Result.fail();
        }
    }

    @GetMapping("detail")
    public Result detail(Integer id){
        return Result.ok(dictService.detail(id));
    }

    @PostMapping("query")
    public Map<String,Object> query(@RequestBody  Dict dict){
        System.out.println("==================="+dict);
        System.out.println("==================="+dict.getId()+"++++++++"+dict.getTypeId());
        PageInfo<Dict> pageInfo = dictService.query(dict);
        return Result.ok(pageInfo);
    }

}
