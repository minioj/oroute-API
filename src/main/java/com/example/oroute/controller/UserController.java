package com.example.oroute.controller;

import com.example.oroute.model.User;
import com.example.oroute.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(value="用户controller",tags={"用户管理接口"})
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @ApiOperation(value="插入用户信息",tags={"插入用户信息"},notes="用户信息插入")
    @PostMapping(value = "/add")
    public String addUser(@RequestBody User params) {
        String res = userService.insertUser(params);
        return res;
    }
}
