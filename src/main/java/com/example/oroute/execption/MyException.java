package com.example.oroute.execption;

/**
 * @author yangxiaojun2017
 * @功能描述:
 * @date: created in 11:43 2019/6/6
 * @Version $version$
 */
public class MyException extends RuntimeException {

        private static final long serialVersionUID = 1L;

        private Integer code;

        public MyException(Integer code, String msg) {
            super(msg);
            this.code = code;
        }

        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }
}
