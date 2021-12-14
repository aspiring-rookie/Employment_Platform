package com.zyh.controller;

import com.github.pagehelper.PageInfo;
import com.sun.org.apache.xpath.internal.operations.Bool;
import com.zyh.entity.Channel;
import com.zyh.framework.redis.RedisUtils;
import com.zyh.service.ChannelService;
import com.zyh.utils.Result;
import com.zyh.utils.UserThreadLocal;
import com.zyh.vo.UserData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/channel")
public class ChannelController {

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private ChannelService channelService;

    @PostMapping("create")
    public Result create(@RequestBody Channel channel){
        String token = UserThreadLocal.get();
        UserData userData = (UserData) redisUtils.get(token);
        channel.setCreateDate(new Date());
        channel.setCreateUser(userData.getId());
        int flag = channelService.create(channel);
        if(flag>0){
            return Result.ok();
        }else{
            return Result.fail();
        }
    }

    @GetMapping("delete")
    public Result delete(String ids){
        int flag = channelService.delete(ids);
        if(flag>0){
            return Result.ok();
        }else{
            return Result.fail();
        }
    }

    @PostMapping("update")
    public Result update(@RequestBody Channel channel){
        int flag = channelService.update(channel);
        if(flag>0){
            return Result.ok();
        }else{
            return Result.fail();
        }
    }

    @GetMapping("detail")
    public Result detail(Integer id){
        return Result.ok(channelService.detail(id));
    }

    @PostMapping("query")
    public Map<String,Object> query(@RequestBody  Channel channel){
        PageInfo<Channel> pageInfo = channelService.query(channel);
        return Result.ok(pageInfo);
    }

    @GetMapping("tree")
    public Result tree(){
        List<Channel> list = channelService.tree(null);
        List<Map<String,Object>> mapList = new ArrayList<>();
        list.forEach(channel -> {
            if (channel.getParentId() == 0){
                Map<String,Object> map = new HashMap<>();
                map.put("id",channel.getId());
                map.put("label",channel.getName());
                if (isChildren(channel.getId(),list)){
                    map.put("children",children(channel.getId(),list));
                }
                mapList.add(map);
            }
        });
        return Result.ok(mapList);
    }

    //支持多级，递归【自己调用自己、出口】
    public List<Map<String,Object>> children(int id,List<Channel> list){
        List<Map<String,Object>> children = new ArrayList<>();
        for (Channel channel : list) {
            if (channel.getParentId() == id){
                Map<String,Object> map = new HashMap<>();
                map.put("id",channel.getId());
                map.put("label",channel.getName());
                if (isChildren(channel.getId(),list)){
                    map.put("children",children(channel.getId(),list));
                }
                children.add(map);
            }
        }
        return children;
    }

    public boolean isChildren(int id,List<Channel> list){
        boolean flag = false;
        for (Channel channel : list) {
            if(channel.getParentId() == id){
                flag = true;
            }
        }
        return flag;
    }

}
