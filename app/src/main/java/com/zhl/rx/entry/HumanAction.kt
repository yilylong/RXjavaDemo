package com.zhl.rx.entry

import android.util.Log

/**
 * 描述：
 * Created by zhaohl on 2019-5-24.
 */
interface HumanAction {
    var hobby:String;
    var smarter:Boolean;
    fun walk(){
        Log.d("mytag","我是接口的walk");
    };
}