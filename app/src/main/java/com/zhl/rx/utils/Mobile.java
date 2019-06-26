package com.zhl.rx.utils;

/**
 * 描述：
 * Created by zhaohl on 2019-4-3.
 */
public class Mobile<T> {
    T type;
    public Mobile(T type){
        this.type = type;
    }

    public T getType() {
        return type;
    }

    public void setType(T type) {
        this.type = type;
    }
}
