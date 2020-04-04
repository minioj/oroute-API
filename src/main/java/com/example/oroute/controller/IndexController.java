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
     * @return
     * @Author mini_oj
     * @category
     * @Description
     * @Date 2020-03-28 0:00
     * @Param
     **/
    @ApiOperation(value = "首页", tags = {"首页展示接口"}, notes = "服务启动是否成功检测")
    @GetMapping(value = "/index")
    public ModelAndView index() {
        return new ModelAndView("index");
    }
}
