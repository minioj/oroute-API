package com.example.oroute.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Api(value="首页controller",tags={"首页展示接口"})
@Controller
@RequestMapping("/yxj")
public class IndexController {

    /**
    * @Author mini_oj
    * @category 
    * @Description //TODO
    * @Date 2020-03-28 0:00
    * @Param 
    * @return 
    **/
    @ApiOperation(value="获取用户信息",tags={"获取用户信息copy"},notes="注意问题点")
    @GetMapping(value = "/index")
    public ModelAndView index() {
        return new ModelAndView("index");
    }
}
