package com.example.oroute.controller;

import com.example.oroute.model.user.User;
import com.example.oroute.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(value="用户controller",tags={"用户管理接口"})
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    /**
    * @Author mini_oj
    * @category
    * @Description 用户信息获取
    * @Date 2020-03-28 23:27
    * @Param
    **/
    @GetMapping(value = "/getUser")
    @ApiOperation(value="用户信息获取",tags={"用户管理接口"},notes="用户信息获取")
    @ApiImplicitParam(name = "userId",value = "用户ID",dataType = "Long",paramType = "body")
    public String getUser(@RequestBody String params) {
        String res = userService.getUser(params);
        return res;
    }

    /**
    * @Author mini_oj
    * @category
    * @Description 用户注册接口
    * @Date 2020-03-28 21:36
    * @Param
    * @return
    **/
    @PostMapping(value = "/register")
    @ApiOperation(value="用户注册",tags={"用户管理接口"},notes="用户信息插入")
    public String addUser(@RequestBody User params) {
        String res = userService.insertUser(params);
        return res;
    }

    /**
    * @Author mini_oj
    * @category
    * @Description 修改用户信息接口
    * @Date 2020-03-28 23:17
    * @Param
    **/
    @PutMapping(value = "/modify")
    @ApiOperation(value="修改用户信息",tags={"用户管理接口"},notes="用户信息修改")
    public String modifyUser(@RequestBody User params) {
        String res = userService.updateUser(params);
        return res;
    }

    /**
    * @Author mini_oj
    * @category
    * @Description 用户登录接口
    * @Date 2020-03-28 23:18
    * @Param
    **/
    @PostMapping(value = "/login")
    @ApiOperation(value="用户登录",tags={"用户管理接口"},notes="用户登录")
    @ApiImplicitParam(name = "params",value = "{'userId':'用户名','password':'密码'}",dataType = "JSONObject",paramType = "body")
    public String loginUser(@RequestBody String params) {
        String res = userService.login(params);
        return res;
    }

    /**
    * @Author mini_oj
    * @category
    * @Description 用户密码重置
    * @Date 2020-03-28 23:29
    * @Param
    **/
    @PostMapping(value = "/resetPassword")
    @ApiOperation(value="用户登录密码重置",tags={"用户管理接口"},notes="用户密码修改")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "用户ID",dataType = "Long"),
            @ApiImplicitParam(name = "passwordOld",value = "旧密码",dataType = "string"),
            @ApiImplicitParam(name = "passwordNew",value = "新密码",dataType = "string")
    })
    public String resetPassword(@RequestBody String params) {
        String res = userService.resetPassword(params);
        return res;
    }
}
