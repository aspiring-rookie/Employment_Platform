package com.zyh.controller;

import com.github.pagehelper.PageInfo;
import com.zyh.entity.Certificate;
import com.zyh.entity.Resume;
import com.zyh.service.CertificateService;
import com.zyh.service.ResumeService;
import com.zyh.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/certificate")
public class CertificateController {

    @Autowired
    private ResumeService resumeService;

    @Autowired
    private CertificateService certificateService;

    @PostMapping("create")
    public Result create(@RequestBody Certificate certificate){
        int flag = certificateService.create(certificate);
        if(flag>0){
            return Result.ok();
        }else{
            return Result.fail();
        }
    }

    @GetMapping("delete")
    public Result delete(String ids){
        int flag = certificateService.delete(ids);
        if(flag>0){
            return Result.ok();
        }else{
            return Result.fail();
        }
    }

    @PostMapping("update")
    public Result update(@RequestBody Certificate certificate){
        int flag = certificateService.update(certificate);
        if(flag>0){
            return Result.ok();
        }else{
            return Result.fail();
        }
    }

    @GetMapping("detail")
    public Result detail(Integer id){
        return Result.ok(certificateService.detail(id));
    }

    @PostMapping("query")
    public Map<String,Object> query(@RequestBody  Certificate certificate){
        PageInfo<Certificate> pageInfo = certificateService.query(certificate);
        pageInfo.getList().forEach(item->{
            Resume detail = resumeService.detail(item.getResumeId());
            item.setResume(detail);
        });
        return Result.ok(pageInfo);
    }

}
