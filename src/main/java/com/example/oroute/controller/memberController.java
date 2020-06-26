package com.example.oroute.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Api(value="会员controller",tags={"会员管理接口"})
@Controller
@RequestMapping("/yxj")
public class memberController {

    @ApiOperation(value = "会员登录页", tags = {"会员管理接口"}, notes = "服务启动是否成功检测")
    @GetMapping(value = "/memberLogin")
    public ModelAndView Login() {
        return new ModelAndView("member/memberLogin");
    }

    @ApiOperation(value = "会员登录校验", tags = {"会员管理接口"}, notes = "服务启动是否成功检测")
    @PostMapping(value = "/memberLoginCheck")
    @ResponseBody
    public String LoginCheck(String useId, String password) {
        return "true";
    }

    @ApiOperation(value = "会员首页", tags = {"会员管理接口"}, notes = "服务启动是否成功检测")
    @GetMapping(value = "/memberIndex")
    public ModelAndView index() {
        return new ModelAndView("member/memberIndex");
    }
}
