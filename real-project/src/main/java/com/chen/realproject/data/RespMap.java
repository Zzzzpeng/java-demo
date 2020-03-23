package com.chen.realproject.data;

public class RespMap{
    private String code;
    private String msg;
    private Object data;

    public RespMap() {
        this.code = "200";
        this.msg = "ok";
    }
    public RespMap(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}