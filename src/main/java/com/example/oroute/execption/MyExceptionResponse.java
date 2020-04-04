package com.example.oroute.execption;

/**
 * @author yangxiaojun2017
 * @功能描述: 自定义响应类
 * @date: created in 18:02 2019/6/5
 * @Version $version$
 */
public class MyExceptionResponse{
    private Integer code;
    private String msg;

    public MyExceptionResponse(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
