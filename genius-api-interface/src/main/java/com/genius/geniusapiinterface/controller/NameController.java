package com.genius.geniusapiinterface.controller;


import com.genius.geniusapiclientsdk.client.GeniusApiClient;
import com.genius.geniusapiclientsdk.model.User;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author dzl
 * @date 2023/7/29 13:30
 */
@RestController
@RequestMapping("/")
public class NameController {

    @Resource
    private GeniusApiClient geniusApiClient;

//    @GetMapping("/geniusApi")
//    public String getNameByApi(String name){
//        String res = geniusApiClient.getNameByGet(name);
//        return "调用接口结果："+res;
//    }
//
//    @GetMapping("/name")
//    public String getNameByGet(String name){
//        return "名字是"+name;
//    }
//
//    @PostMapping("/name")
//    public String getNameByPost(@RequestParam String name){
//        String res = geniusApiClient.getNameByPost(name);
//        return "Post接口调用结果:"+res;
//    }

    @PostMapping("/name/user")
    public String getUserByPost(@RequestBody User user){
//        String res = geniusApiClient.getUsernameByPost(user);
        String username = user.getUsername();
        return "Post接口调用结果:"+username;
    }
}
