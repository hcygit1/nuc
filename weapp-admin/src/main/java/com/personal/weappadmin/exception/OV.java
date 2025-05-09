package com.personal.weappadmin.exception;

public class OV {

    private int code;
    private String message;
    private Object data;

    public OV success() {
        this.code = 200;
        this.message = "success";
        return this;
    }

    public OV success(Object data) {
        this.code = 200;
        this.message = "success";
        this.data = data;
        return this;
    }

    public OV unLoginLw(Object data){
        this.code = 6001;
        this.message = "未登录路网中心";
        this.data = data;
        return this;
    }

    public OV failure(int code, String message) {
        this.code = code;
        this.message = message;
        return this;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "OV{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
