package com.wjm.springmvc.bean;

public class BaseResponse {
    Integer code = 0;
    String msg = "";

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

    public BaseResponse() {
    }
}
