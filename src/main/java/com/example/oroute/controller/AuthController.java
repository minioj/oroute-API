package com.example.oroute.controller;

import com.example.oroute.model.auth.Auth;
import com.example.oroute.service.AuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value="授权controller",tags={"配置管理接口"})
@RestController
@RequestMapping("/yxj")
public class AuthController {

    @Autowired
    AuthService authService;

    /**
     * @Author mini_oj
     * @category 授权插入
     * @Description 授权信息插入接口
     * @Date 2020-04-04 19:14
     * @Param
     **/
    @PostMapping(value = "/authAdd")
    @ApiOperation(value = "请求授权", tags = {"配置管理接口"}, notes = "授权信息插入")
    public String addAuth(@RequestBody Auth params) {
        String res = authService.insertAuth(params);
        return res;
    }
}
