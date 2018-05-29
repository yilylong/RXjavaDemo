package com.zhl.rx.entry;

import com.zhl.rx.activity.Dagger2Activity;
import com.zhl.rx.bean.ActivityModule;

import dagger.Component;

/**
 * 描述：
 * Created by zhaohl on 2018-5-29.
 */
@Component(modules = ActivityModule.class)
public interface ActivityComponent {
    void zhuru(Dagger2Activity dagger2Activity);
}
