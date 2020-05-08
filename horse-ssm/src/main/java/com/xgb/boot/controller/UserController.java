package com.xgb.boot.controller;

import com.alibaba.fastjson.JSON;
import com.xgb.boot.entity.UserInfoEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api("用户管理")
@RequestMapping("/user")
public class UserController {

    @Value("${env.username}")
    private String username;

    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    @ApiOperation(value = "hello")
    public String hello(){
        return username;
    }

}
