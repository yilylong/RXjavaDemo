package com.zhl.rx.bean;

import javax.inject.Inject;

/**
 * 描述：
 * Created by zhaohl on 2018-5-29.
 */
public class ZhaiNan {
    @Inject
    String restaurant;
    @Inject
    Baozi baozi;
    @Inject
    Noodle noodle;
    @Inject
    ThirdSdk thirdSdk;
    @Inject
    public ZhaiNan() {

    }

    public String eat() {
        StringBuilder sb = new StringBuilder();
        sb.append("我吃的是 ");
        if(restaurant!=null){
            sb.append(restaurant+"的");
        }
        if ( baozi != null ) {
            sb.append(baozi.toString());
        }

        if (noodle != null) {
            sb.append("  ");
            sb.append(noodle.toString());
        }

        if(thirdSdk!=null){
            sb.append("  我还能注入第三方：");
            sb.append(thirdSdk.toString());
        }
        return sb.toString();
    }
}
