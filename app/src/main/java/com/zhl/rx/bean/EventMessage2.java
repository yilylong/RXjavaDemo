package com.zhl.rx.bean;

/**
 * 描述：
 * Created by zhaohl on 2019-1-10.
 */
public class EventMessage2 {
    private String message;
    private int code;
    public EventMessage2(String message,int code){
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
