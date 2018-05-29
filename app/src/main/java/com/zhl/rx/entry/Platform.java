package com.zhl.rx.entry;

import com.zhl.rx.bean.ShangjiaAModule;
import com.zhl.rx.bean.ZhaiNan;

import dagger.Component;

/**
 * 描述：
 * Created by zhaohl on 2018-5-29.
 */
@Component(modules = ShangjiaAModule.class)
public interface Platform {
    ZhaiNan waimai();
}
