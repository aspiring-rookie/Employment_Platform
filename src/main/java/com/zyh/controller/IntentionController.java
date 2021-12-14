package com.zyh.controller;

import com.github.pagehelper.PageInfo;
import com.zyh.entity.Intention;
import com.zyh.entity.Resume;
import com.zyh.service.IntentionService;
import com.zyh.service.ResumeService;
import com.zyh.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/intention")
public class IntentionController {

    @Autowired
    private IntentionService intentionService;
    @Autowired
    private ResumeService resumeService;

    @PostMapping("create")
    public Result create(@RequestBody Intention intention){
        int flag = intentionService.create(intention);
        if(flag>0){
            return Result.ok();
        }else{
            return Result.fail();
        }
    }

    @GetMapping("delete")
    public Result delete(String ids){
        int flag = intentionService.delete(ids);
        if(flag>0){
            return Result.ok();
        }else{
            return Result.fail();
        }
    }

    @PostMapping("update")
    public Result update(@RequestBody Intention intention){
        int flag = intentionService.update(intention);
        if(flag>0){
            return Result.ok();
        }else{
            return Result.fail();
        }
    }

    @GetMapping("detail")
    public Result detail(Integer id){
        return Result.ok(intentionService.detail(id));
    }

    @PostMapping("query")
    public Map<String,Object> query(@RequestBody  Intention intention){
        PageInfo<Intention> pageInfo = intentionService.query(intention);
        pageInfo.getList().forEach(item->{
            Resume detail = resumeService.detail(item.getResumeId());
            item.setResume(detail);
        });
        return Result.ok(pageInfo);
    }

}
