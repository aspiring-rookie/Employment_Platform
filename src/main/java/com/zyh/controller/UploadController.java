package com.zyh.controller;

import com.zyh.utils.Result;
import com.zyh.utils.UploadConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @author
 * @date 2021/5/3 11:31
 */
@RestController
public class UploadController {

    @Autowired
    private UploadConfig config;

    @PostMapping("/upload")
    public Result upload(@RequestParam("file") MultipartFile multipartFile) throws IOException {

        //1.获取原文件名
        String originalFilename = multipartFile.getOriginalFilename();
        //2.获取后缀
        String ext = originalFilename.substring(originalFilename.lastIndexOf(".")+1, originalFilename.length());
        //3.生成新的文件名
        String newFileName = UUID.randomUUID().toString()+"."+ext;
        //4.执行文件上传
        multipartFile.transferTo(new File(config.getUploadFolder(),newFileName));
        //5.返回upload结果
        return Result.ok(config.getBaseUrl()+newFileName);

    }
}
