package com.zhl.rx.bean;

import dagger.Module;
import dagger.Provides;

/**
 * 描述：
 * Created by zhaohl on 2018-5-29.
 */
@Module
public class ShangjiaAModule {
    String resturant;
    public  ShangjiaAModule(String resturant){
        this.resturant = resturant;
    }
    @Provides
    public Baozi provideBaozi() {
        return new Baozi("豆沙包");
    }
    @Provides
    public Noodle provideNoodle() {
        return new Noodle();
    }
    @Provides
    public ThirdSdk provideThirdSdk(){
        return new ThirdSdk();
    }
    @Provides
    public String provideRestaurant(){
        return resturant;
    }
}
