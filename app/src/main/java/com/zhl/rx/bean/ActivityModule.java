package com.zhl.rx.bean;

import com.zhl.rx.annotation.Computer;
import com.zhl.rx.annotation.Phone;

import dagger.Module;
import dagger.Provides;

/**
 * 描述：
 * Created by zhaohl on 2018-5-29.
 */
@Module
public class ActivityModule {
    @Provides
    @Phone
    public String provideTestValue(){
        return "12342345";
    }
    @Provides
    @Computer
    public String provideTestValue2(){
        return "srgqer";
    }
}
