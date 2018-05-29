package com.zhl.rx.bean;

import javax.inject.Inject;

/**
 * 描述：
 * Created by zhaohl on 2018-5-29.
 */
public class Baozi {
    String name;
    @Inject
    public Baozi() {
    }
    public Baozi(String name) {
        this.name = name;
    }
    @Override
    public String toString() {
        return name;
    }
}
