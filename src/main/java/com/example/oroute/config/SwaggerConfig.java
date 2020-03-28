package com.example.oroute.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration //必须存在
@EnableSwagger2 //必须存在
public class SwaggerConfig{
    @Bean
    public Docket customDocket() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo());
    }
/*  @Bean
    public Docket customDocket(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("学生管理")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.xiaomin.controller"))
                .paths(PathSelectors.any())
                .build();
    }*/

    /**
    * @Author moni_oj
    * @category 
    * @Description //TODO 
    * @Date  
    * @Param 
    * @return 
    **/
    private ApiInfo apiInfo() {
        Contact contact = new Contact("mini_oj", "", "yangxj95@qq.com");
        return new ApiInfoBuilder().title("小军测试API接口")//标题
        .description("API接口的描述")//文档接口的描述
        .contact(contact)
        .version("0.0.1")//版本号
        .build();
        }
    }